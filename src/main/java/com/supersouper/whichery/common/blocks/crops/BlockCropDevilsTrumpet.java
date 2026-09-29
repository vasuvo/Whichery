package com.supersouper.whichery.common.blocks.crops;

import net.minecraft.item.Item;

import com.supersouper.whichery.ModItems;

public class BlockCropDevilsTrumpet extends BlockWhicheryCrop {

    public BlockCropDevilsTrumpet(String id, int maxStage) {
        super(id, maxStage);
    }

    @Override
    protected Item getSeedItem() {
        return ModItems.DEVILS_TRUMPET_SEED.get();
    }
}
