package net.chilacole.biometrees.mixin;

import java.util.Random;

import net.minecraft.core.block.BlockLogicSaplingOak;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.overworld.BiomeRainforest;
import net.minecraft.core.world.biome.overworld.BiomeSwamp;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.spooner.WorldFeatureSpoonerTreeRound;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeFancyRainforest;
import net.minecraft.core.world.pos.TilePosc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.chilacole.biometrees.BiomeTreesConfig;

@Mixin(value = BlockLogicSaplingOak.class, remap = false)
public abstract class BlockSaplingOakMixin {

	@Inject(
		method = "growTree",
		at = @At("HEAD"),
		cancellable = true
	)
	private void onGrowTree(World world, TilePosc tilePos, Random random, CallbackInfo ci) {
		if (!BiomeTreesConfig.isBiomeAwareGrowthEnabled()) {
			return;
		}
		Biome biome = world.getBlockBiome(tilePos);

		// Swamp Logic
		if (biome instanceof BiomeSwamp && BiomeTreesConfig.isSwampOakGrowthEnabled()) {
			WorldFeature tree = biome.getTreeFeature(random);

			world.setBlockType(tilePos, Blocks.AIR);

			if (!tree.place(world, random, tilePos.x(), tilePos.y(), tilePos.z())) {
				world.setBlockType(tilePos, ((BlockLogicSaplingOak)(Object)this).block);
			}

			ci.cancel();
			return;
		}

		// Rainforest Logic
		if (biome instanceof BiomeRainforest && BiomeTreesConfig.isRainforestOakGrowthEnabled()) {
			boolean giant = BiomeTreesConfig.isRainforestGiantOakEnabled();
			boolean mossy = BiomeTreesConfig.isRainforestFancyMossyOakEnabled();
			boolean fancy = BiomeTreesConfig.isRainforestFancyRegularOakEnabled();
			boolean regular = BiomeTreesConfig.isRainforestRegularOakEnabled();

			// If they are ALL turned off, run vanilla logic.
			if (!giant && !mossy && !fancy && !regular) {
				return;
			}

			ci.cancel();

			WorldFeature tree = null;
			boolean onlyGiant = giant && !mossy && !fancy && !regular;

			// 1. Attempt Giant Oak
			if (giant) {
				// If it's the ONLY tree enabled, 1-in-192 chance (~3 stacks avg).
				// Otherwise, normal 1-in-1000 chance.
				int chance = onlyGiant ? 250 : 1000;
				if (random.nextInt(chance) == 0) {
					tree = new WorldFeatureSpoonerTreeRound(30 + random.nextInt(30), Blocks.LOG_OAK.id(), 0, Blocks.LEAVES_OAK.id(), 0);
				}
			}

			// If it didn't roll a giant tree, and it's the ONLY option enabled, we silently return
			// to consume the bonemeal/tick without growing anything.
			if (tree == null && onlyGiant) {
				return;
			}

			// 2. Decide between Fancy and Regular based on what is toggled ON
			if (tree == null) {
				boolean tryFancy = (mossy || fancy) && random.nextInt(3) == 0;

				if (tryFancy || !regular) {
					if (mossy && fancy) {
						tree = new WorldFeatureTreeFancyRainforest(Blocks.LEAVES_OAK.id(), random.nextInt(10) == 0 ? Blocks.LOG_OAK_MOSSY.id() : Blocks.LOG_OAK.id(), 0);
					} else if (mossy) {
						tree = new WorldFeatureTreeFancyRainforest(Blocks.LEAVES_OAK.id(), Blocks.LOG_OAK_MOSSY.id(), 0);
					} else if (fancy) {
						tree = new WorldFeatureTreeFancyRainforest(Blocks.LEAVES_OAK.id(), Blocks.LOG_OAK.id(), 0);
					}
				}

				// 3. Attempt regular oak
				if (tree == null && regular) {
					tree = new WorldFeatureTree(Blocks.LEAVES_OAK.id(), Blocks.LOG_OAK.id(), 6);
				}
			}

			// 4. Place the selected tree
			if (tree != null) {
				world.setBlockType(tilePos, Blocks.AIR);
				if (!tree.place(world, random, tilePos.x(), tilePos.y(), tilePos.z())) {
					world.setBlockType(tilePos, ((BlockLogicSaplingOak)(Object)this).block);
				}
			}
		}
	}
}
