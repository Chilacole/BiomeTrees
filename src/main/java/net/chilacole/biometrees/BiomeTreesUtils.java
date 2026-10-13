package net.chilacole.biometrees;

import java.util.Random;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;
import net.minecraft.core.world.pos.TilePosc;

public class BiomeTreesUtils {

	/**
	 * Safely attempts to generate a tree. If it fails, it replaces the sapling.
	 */
	public static void placeTree(World world, TilePosc tilePos, Random random, WorldFeature tree, Block saplingBlock) {
		if (tree != null) {
			world.setBlockType(tilePos, Blocks.AIR); // Remove sapling temporarily

			if (!tree.place(world, random, tilePos.x(), tilePos.y(), tilePos.z())) {
				// If it failed to grow (e.g. hit a ceiling), put the sapling back
				world.setBlockType(tilePos, saplingBlock);
			}
		}
	}
}
