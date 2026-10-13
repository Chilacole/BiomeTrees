package net.chilacole.biometrees.mixin;

import java.util.Random;

import net.minecraft.core.block.BlockLogicSaplingBirch;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.overworld.BiomeBirchForest;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTree;
import net.minecraft.core.world.generate.feature.tree.WorldFeatureTreeTall;
import net.minecraft.core.world.pos.TilePosc;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.chilacole.biometrees.BiomeTreesConfig;
import net.chilacole.biometrees.BiomeTreesUtils;

@Mixin(value = BlockLogicSaplingBirch.class, remap = false)
public abstract class BlockSaplingBirchMixin {

	@Inject(
		method = "growTree",
		at = @At("HEAD"),
		cancellable = true
	)
	private void onGrowTree(World world, TilePosc tilePos, Random random, CallbackInfo ci) {
		// Master toggle check
		if (!BiomeTreesConfig.isBiomeAwareGrowthEnabled()) {
			return;
		}

		Biome biome = world.getBlockBiome(tilePos);

		// Birch Forest Logic
		if (biome instanceof BiomeBirchForest && BiomeTreesConfig.isBirchForestGrowthEnabled()) {
			boolean tall = BiomeTreesConfig.isBirchForestTallEnabled();
			boolean regular = BiomeTreesConfig.isBirchForestRegularEnabled();

			// If both variants are disabled, run the vanilla sapling logic
			if (!tall && !regular) {
				return;
			}

			ci.cancel();
			WorldFeature tree = null;

			if (tall && regular) {
				// Both enabled: 10% Tall, 90% Regular (height 8)
				tree = random.nextInt(10) == 0 ? new WorldFeatureTreeTall(Blocks.LEAVES_BIRCH.id(), Blocks.LOG_BIRCH.id()) : new WorldFeatureTree(Blocks.LEAVES_BIRCH.id(), Blocks.LOG_BIRCH.id(), 8);
			} else if (tall) {
				// Only tall enabled: 100% Tall
				tree = new WorldFeatureTreeTall(Blocks.LEAVES_BIRCH.id(), Blocks.LOG_BIRCH.id());
			} else if (regular) {
				// Only regular enabled: 100% Regular (height 8)
				tree = new WorldFeatureTree(Blocks.LEAVES_BIRCH.id(), Blocks.LOG_BIRCH.id(), 8);
			}

			// Place the selected tree using our utility
			BiomeTreesUtils.placeTree(world, tilePos, random, tree, ((BlockLogicSaplingBirch)(Object)this).block);
		}
	}
}
