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
import net.chilacole.biometrees.BiomeTreesUtils;

@Mixin(value = BlockLogicSaplingThorn.class, remap = false)
public abstract class BlockSaplingThornMixin {

	// Extracted String Literal
	private static final String CAATINGA_PLAINS_KEY = "biome.overworld.caatinga.plains";

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
		WorldFeature tree = null;

		// 1. Caatinga Plains
		if (CAATINGA_PLAINS_KEY.equals(biome.translationKey) && BiomeTreesConfig.isCaatingaPlainsGrowthEnabled()) {
			boolean shortThorn = BiomeTreesConfig.isCaatingaPlainsShortThornEnabled();
			boolean shrub = BiomeTreesConfig.isCaatingaPlainsShrubEnabled();

			if (!shortThorn && !shrub) return; // Fallback to vanilla

			ci.cancel();

			if (shortThorn && shrub) {
				// User logic: 25% Shrub, 75% Short Thorn
				tree = random.nextInt(4) == 0 ? new WorldFeatureTreeShrub(Blocks.LEAVES_THORN.id(), Blocks.LOG_OAK.id()) : new WorldFeatureTreeThorn(1, Blocks.LOG_THORN.id(), 0, Blocks.LEAVES_THORN.id(), 0);
			} else if (shortThorn) {
				tree = new WorldFeatureTreeThorn(1, Blocks.LOG_THORN.id(), 0, Blocks.LEAVES_THORN.id(), 0);
			} else if (shrub) {
				tree = new WorldFeatureTreeShrub(Blocks.LEAVES_THORN.id(), Blocks.LOG_OAK.id());
			}
		}
		// 2. Regular Caatinga
		else if (biome instanceof BiomeCaatinga && BiomeTreesConfig.isCaatingaGrowthEnabled()) {
			boolean tallThorn = BiomeTreesConfig.isCaatingaTallThornEnabled();
			boolean shrub = BiomeTreesConfig.isCaatingaShrubEnabled();

			if (!tallThorn && !shrub) return; // Fallback to vanilla

			ci.cancel();

			if (tallThorn && shrub) {
				// User logic: 25% Shrub, 75% Tall Thorn
				tree = random.nextInt(4) == 0 ? new WorldFeatureTreeShrub(Blocks.LEAVES_THORN.id(), Blocks.LOG_OAK.id()) : new WorldFeatureTreeThorn(4, Blocks.LOG_THORN.id(), 0, Blocks.LEAVES_THORN.id(), 0);
			} else if (tallThorn) {
				tree = new WorldFeatureTreeThorn(4, Blocks.LOG_THORN.id(), 0, Blocks.LEAVES_THORN.id(), 0);
			} else if (shrub) {
				tree = new WorldFeatureTreeShrub(Blocks.LEAVES_THORN.id(), Blocks.LOG_OAK.id());
			}
		}

		// 3. Place Tree using our new utility
		BiomeTreesUtils.placeTree(world, tilePos, random, tree, ((BlockLogicSaplingThorn)(Object)this).block);
	}
}
