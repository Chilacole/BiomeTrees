package net.chilacole.biometrees.mixin;

import java.util.Random;

import net.minecraft.core.block.BlockLogicSaplingBirch;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.overworld.BiomeBirchForest;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.pos.TilePosc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.chilacole.biometrees.BiomeTreesConfig;

@Mixin(value = BlockLogicSaplingBirch.class, remap = false)
public abstract class BlockSaplingBirchMixin {

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

		// Only override birch saplings in birch forests.
		if (!(biome instanceof BiomeBirchForest)) {
			return;
		}

		// Use the exact tree-generation logic of the biome.
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
				((BlockLogicSaplingBirch)(Object)this).block
			);
		}

		ci.cancel();
	}
}
