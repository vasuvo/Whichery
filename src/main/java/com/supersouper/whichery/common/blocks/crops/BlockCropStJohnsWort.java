package com.supersouper.whichery.common.blocks.crops;

import net.minecraft.item.Item;

import com.supersouper.whichery.ModItems;

public class BlockCropStJohnsWort extends BlockWhicheryCrop {

    public BlockCropStJohnsWort(String id, int maxStage) {
        super(id, maxStage);
    }

    @Override
    protected Item getSeedItem() {
        return ModItems.ST_JOHNS_WORT_SEED.get();
    }
}
