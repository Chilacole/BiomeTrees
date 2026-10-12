package net.chilacole.biometrees.mixin;

import java.util.Random;

import net.minecraft.core.block.BlockLogicSaplingThorn;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.overworld.BiomeCaatinga;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeShrub;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeThorn;
import net.minecraft.core.world.pos.TilePosc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.chilacole.biometrees.BiomeTreesConfig;

@Mixin(value = BlockLogicSaplingThorn.class, remap = false)
public abstract class BlockSaplingThornMixin {

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
		Biome biome = world.getBlockBiome(tilePos);
		WorldFeature tree;

		// Caatinga Plains: 25% shrub, 75% short thorn tree.
		if ("biome.overworld.caatinga.plains".equals(biome.translationKey)) {
			if (random.nextInt(4) == 0) {
				tree = new WorldFeatureTreeShrub(
					Blocks.LEAVES_THORN.id(),
					Blocks.LOG_OAK.id()
				);
			} else {
				tree = new WorldFeatureTreeThorn(
					1,
					Blocks.LOG_THORN.id(),
					0,
					Blocks.LEAVES_THORN.id(),
					0
				);
			}
		}
		// Caatinga: 25% shrub, 75% tall thorn tree.
		else if (biome instanceof BiomeCaatinga) {
			if (random.nextInt(4) == 0) {
				tree = new WorldFeatureTreeShrub(
					Blocks.LEAVES_THORN.id(),
					Blocks.LOG_OAK.id()
				);
			} else {
				tree = new WorldFeatureTreeThorn(
					4,
					Blocks.LOG_THORN.id(),
					0,
					Blocks.LEAVES_THORN.id(),
					0
				);
			}
		} else {
			// Other biomes use the original sapling implementation.
			return;
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
				((BlockLogicSaplingThorn)(Object)this).block
			);
		}

		ci.cancel();
	}
}

