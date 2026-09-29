package com.supersouper.whichery.common.items.seeds;

import net.minecraft.block.Block;

import com.supersouper.whichery.ModBlocks;
import com.supersouper.whichery.common.blocks.crops.BlockCropDevilsTrumpet;

public class ItemSeedDevilsTrumpet extends ItemWhicherySeed {

    public ItemSeedDevilsTrumpet() {
        super("devils_trumpet");
    }

    @Override
    protected Block getCropBlock() {
        return ModBlocks.DEVILS_TRUMPET.get();
    }

    /**
     * Can only be planted near an active Nether portal.
     */
    @Override
    protected boolean canPlantAt(net.minecraft.world.World world, int x, int y, int z) {
        return BlockCropDevilsTrumpet.isNearPortal(world, x, y, z);
    }
}
