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
	private void onGrowTree(
		World world,
		TilePosc tilePos,
		Random random,
		CallbackInfo ci
	) {
		// Master toggle check
		if (!BiomeTreesConfig.isBiomeAwareGrowthEnabled()) {
			return;
		}
		Biome biome = world.getBlockBiome(tilePos);

		// Swamp: use the biome's original tree generator if enabled.
		if (biome instanceof BiomeSwamp && BiomeTreesConfig.isSwampOakGrowthEnabled()) {
			WorldFeature tree = biome.getTreeFeature(random);

			world.setBlockType(tilePos, Blocks.AIR);

			if (!tree.place(
				world,
				random,
				tilePos.x(),
				tilePos.y(),
				tilePos.z()
			)) {
				world.setBlockType(
					tilePos,
					((BlockLogicSaplingOak)(Object)this).block
				);
			}

			ci.cancel();
			return;
		}

		// Rainforest: select between giant, fancy, and regular oak trees if enabled.
		if (biome instanceof BiomeRainforest && BiomeTreesConfig.isRainforestOakGrowthEnabled()) {
			WorldFeature tree;

			// Extremely rare giant round oak: 1 in 1,000.
			if (random.nextInt(1000) == 0) {
				int treeHeight = 30 + random.nextInt(30);

				tree = new WorldFeatureSpoonerTreeRound(
					treeHeight,
					Blocks.LOG_OAK.id(),
					0,
					Blocks.LEAVES_OAK.id(),
					0
				);
			} else if (random.nextInt(3) == 0) {
				// Fancy rainforest oak, with a 10% mossy-log variant.
				tree = random.nextInt(10) == 0
					? new WorldFeatureTreeFancyRainforest(
					Blocks.LEAVES_OAK.id(),
					Blocks.LOG_OAK_MOSSY.id(),
					0
				)
					: new WorldFeatureTreeFancyRainforest(
					Blocks.LEAVES_OAK.id(),
					Blocks.LOG_OAK.id(),
					0
				);
			} else {
				// Regular rainforest oak.
				tree = new WorldFeatureTree(
					Blocks.LEAVES_OAK.id(),
					Blocks.LOG_OAK.id(),
					6
				);
			}

			world.setBlockType(tilePos, Blocks.AIR);

			if (!tree.place(
				world,
				random,
				tilePos.x(),
				tilePos.y(),
				tilePos.z()
			)) {
				world.setBlockType(
					tilePos,
					((BlockLogicSaplingOak)(Object)this).block
				);
			}

			ci.cancel();
		}

		// Other biomes: preserve the original oak sapling behavior.
	}
}
