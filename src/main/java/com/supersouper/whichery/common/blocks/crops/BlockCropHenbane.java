package com.supersouper.whichery.common.blocks.crops;

import net.minecraft.item.Item;

import com.supersouper.whichery.ModItems;

public class BlockCropHenbane extends BlockWhicheryCrop {

    public BlockCropHenbane(String id, int maxStage) {
        super(id, maxStage);
    }

    @Override
    protected Item getSeedItem() {
        return ModItems.HENBANE_SEED.get();
    }
}
