package com.supersouper.whichery.common.blocks.crops;

import net.minecraft.item.Item;

import com.supersouper.whichery.ModItems;

public class BlockCropMullein extends BlockWhicheryCrop {

    public BlockCropMullein(String id, int maxStage) {
        super(id, maxStage);
    }

    @Override
    protected Item getSeedItem() {
        return ModItems.MULLEIN_SEED.get();
    }
}
