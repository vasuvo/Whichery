package com.supersouper.whichery.common.items.seeds;

import net.minecraft.block.Block;

import com.supersouper.whichery.ModBlocks;

public class ItemSeedDevilsTrumpet extends ItemWhicherySeed {

    public ItemSeedDevilsTrumpet() {
        super("devils_trumpet");
    }

    @Override
    protected Block getCropBlock() {
        return ModBlocks.DEVILS_TRUMPET.get();
    }
}
