package com.supersouper.whichery.common.blocks.crops;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockCropGreenteeth extends BlockBush {

    // Below are two limits that define how greenteeth spreads. The per-block values are stored in the metadata.
    // The limits must be set such that (MAX_DISTANCE * (MAX_SPREADS + 1) + MAX_SPREADS ) < 16.

    // How many blocks a greenteeth bloom can grow away from its "seed" point. Greenteeth starts at 0 distance,
    // and every growth outward gains 1 point; at MAX_DISTANCE, it is starved and cannot grow further.
    // Distance can be reset to 0 by stealing nutrients from nearby crops.
    public static final int MAX_DISTANCE = 2;
    // How many times a single greenteeth block can spread to a neighbor. This is designed so that you cannot
    // infinitely remove the algae around a single seed point and have it spread outwards forever.
    public static final int MAX_SPREADS = 4;
    private static final int[][] OFFSETS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

    public BlockCropGreenteeth() {
        setBlockName("crop_greenteeth");
        setBlockTextureName("whichery:crops/greenteeth/living");
        setStepSound(soundTypeGrass);
        setBlockBounds(0, 0, 0, 1, 0.015625f, 1);
    }

    // Only stays on still source water.
    @Override
    public boolean canBlockStay(World world, int x, int y, int z) {
        Block below = world.getBlock(x, y - 1, z);
        int meta = world.getBlockMetadata(x, y - 1, z);
        return below == Blocks.water && meta == 0;
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.water;
    }

    // TEMP DEV FUNCTION: Remove before PR
    // Right click on greenteeth mimics random ticks.
    @Override
    public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int side, float subX,
        float subY, float subZ) {
        if (!worldIn.isRemote) {
            updateTick(worldIn, x, y, z, worldIn.rand);
        }
        return true;
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        super.updateTick(world, x, y, z, random);
        if (world.getBlock(x, y, z) != this) {
            return;
        }

        int meta = world.getBlockMetadata(x, y, z);
        // If the greenteeth can't grow, stop
        if (!canGrow(meta)) {
            return;
        }
        int distance = getDistance(meta);
        int spread = getSpread(meta);
        ArrayList<int[]> freeNeighbors = new ArrayList<int[]>();
        for (int[] offset : OFFSETS) {
            if (canBlockStay(world, x + offset[0], y, z + offset[1])
                && world.isAirBlock(x + offset[0], y, z + offset[1])) {
                freeNeighbors.add(offset);
            }
        }

        if (freeNeighbors.isEmpty()) {
            // If there are no free neighbors, the greenteeth "dies"
            world.setBlockMetadataWithNotify(x, y, z, toMeta(MAX_SPREADS, distance), 2);
        } else {
            // Spread to a random neighbor
            int[] offset = freeNeighbors.get(random.nextInt(freeNeighbors.size()));
            world.setBlock(x + offset[0], y, z + offset[1], this, toMeta(0, distance + 1), 3);
            // Update this block's spread count
            world.setBlockMetadataWithNotify(x, y, z, toMeta(spread + 1, distance), 2);
        }
    }

    @Override
    public int getRenderType() {
        return 23;
    }

    // Get distance level
    public static int getDistance(int metadata) {
        return metadata / (MAX_SPREADS + 1);
    }

    // Get spread level
    public static int getSpread(int metadata) {
        return metadata % (MAX_SPREADS + 1);
    }

    // Helper function; determines if greenteeth can keep growing, or
    // if it is starved (either via distance to nutrients, or having
    // already grown too many neighbors).
    public static boolean canGrow(int metadata) {
        return getDistance(metadata) < MAX_DISTANCE && getSpread(metadata) < MAX_SPREADS;
    }

    // Converts spread and distance levels to metadata.
    public static int toMeta(int spread, int distance) {
        return distance * (MAX_SPREADS + 1) + spread;
    }
}
