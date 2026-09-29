package com.supersouper.whichery.common.items.seeds;

import net.minecraft.block.Block;

import com.supersouper.whichery.ModBlocks;

public class ItemSeedLunaria extends ItemWhicherySeed {

    public ItemSeedLunaria() {
        super("lunaria");
        // Lunaria's seed is its pod, which is also used as an ingredient
        setUnlocalizedName("lunaria_pod");
        setTextureName("whichery:lunaria_pod");
    }

    @Override
    protected Block getCropBlock() {
        return ModBlocks.LUNARIA.get();
    }
}
