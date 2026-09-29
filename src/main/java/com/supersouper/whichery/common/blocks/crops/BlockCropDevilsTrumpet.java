package com.supersouper.whichery.common.blocks.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.supersouper.whichery.ModItems;

public class BlockCropDevilsTrumpet extends BlockWhicheryCrop {

    // How close an active Nether portal has to be for devil's trumpet to grow (it must "hear hell's passage").
    private static final int PORTAL_RANGE_HORIZONTAL = 4;
    private static final int PORTAL_RANGE_VERTICAL = 2;

    public BlockCropDevilsTrumpet(String id, int maxStage) {
        super(id, maxStage);
    }

    @Override
    protected Item getSeedItem() {
        return ModItems.DEVILS_TRUMPET_SEED.get();
    }

    /**
     * Grows at vanilla speed in the light, but only near a portal; without one it stops growing (but doesn't pop off).
     */
    @Override
    protected boolean shouldGrow(World world, int x, int y, int z, Random rand) {
        return isBright(world, x, y, z) && soilHasNutrients(world, x, y, z, rand) && isNearPortal(world, x, y, z);
    }

    /**
     * Can only bone meal if it isn't fully grown and there is a portal nearby.
     */
    @Override
    protected boolean canUseBonemeal(World world, int x, int y, int z) {
        return super.canUseBonemeal(world, x, y, z) && isNearPortal(world, x, y, z);
    }

    /**
     * Drops 1-2 flowers when grown.
     */
    @Override
    public void addDropsGrown(World world, int x, int y, int z, int metadata, int fortune, ArrayList<ItemStack> drops) {
        drops.add(ModItems.DEVILS_TRUMPET_FLOWER.newItemStack(world.rand.nextInt(2) + 1));
    }

    /**
     * Helper function; checks for an active Nether portal within range.
     */
    public static boolean isNearPortal(World world, int x, int y, int z) {
        for (int dx = -PORTAL_RANGE_HORIZONTAL; dx <= PORTAL_RANGE_HORIZONTAL; dx++) {
            for (int dy = -PORTAL_RANGE_VERTICAL; dy <= PORTAL_RANGE_VERTICAL; dy++) {
                for (int dz = -PORTAL_RANGE_HORIZONTAL; dz <= PORTAL_RANGE_HORIZONTAL; dz++) {
                    if (world.getBlock(x + dx, y + dy, z + dz) == Blocks.portal) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
