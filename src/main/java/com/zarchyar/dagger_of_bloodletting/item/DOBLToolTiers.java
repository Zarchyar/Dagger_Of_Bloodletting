package com.zarchyar.dagger_of_bloodletting.item;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import wayoftime.bloodmagic.common.item.BloodMagicItems;

import java.util.List;

public class DOBLToolTiers {
    public static final Tier BLOODLETTING = TierSortingRegistry.registerTier(
            new ForgeTier(2, 500, 6.0F, 2.0F, 20,
                    BlockTags.NEEDS_IRON_TOOL, ()-> {return Ingredient.of(BloodMagicItems.IMBUED_SLATE.get());}),
            new ResourceLocation(Dagger_Of_Bloodletting.MODID, "bloodletting"), List.of(Tiers.STONE), List.of(Tiers.DIAMOND)
    );
}
