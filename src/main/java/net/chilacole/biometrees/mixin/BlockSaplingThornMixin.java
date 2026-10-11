
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

		// Caatinga: 10% shrub, 90% thorn tree.
		if (biome instanceof BiomeCaatinga) {
			WorldFeature tree;

			if (random.nextInt(4) == 0) {
				// Rare shrub: thorn leaves and oak logs.
				tree = new WorldFeatureTreeShrub(
					Blocks.LEAVES_THORN.id(),
					Blocks.LOG_OAK.id()
				);
			} else {
				// Standard thorn tree.
				tree = new WorldFeatureTreeThorn(
					4,
					Blocks.LOG_THORN.id(),
					0,
					Blocks.LEAVES_THORN.id(),
					0
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
					((BlockLogicSaplingThorn)(Object)this).block
				);
			}

			ci.cancel();
		}

		// Other biomes: preserve the original sapling behavior.
	}
}
