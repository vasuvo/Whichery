package com.supersouper.whichery.common.blocks.crops;

import net.minecraft.item.Item;

import com.supersouper.whichery.ModItems;

public class BlockCropLunaria extends BlockWhicheryCrop {

    public BlockCropLunaria(String id, int maxStage) {
        super(id, maxStage);
    }

    @Override
    protected Item getSeedItem() {
        return ModItems.LUNARIA_POD.get();
    }
}
