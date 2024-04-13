package com.zarchyar.dagger_of_bloodletting.datagen.bloodmagic;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import wayoftime.bloodmagic.altar.AltarTier;
import wayoftime.bloodmagic.common.data.recipe.builder.BloodAltarRecipeBuilder;
import wayoftime.bloodmagic.common.item.BloodMagicItems;

import java.util.function.Consumer;

public class BloodAltarRecipeProvider extends RecipeProvider implements IConditionBuilder {
    String basePath = "altar/";

    public BloodAltarRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        BloodAltarRecipeBuilder.altar(Ingredient.of(BloodMagicItems.SACRIFICIAL_DAGGER.get()), new ItemStack(ModItems.DAGGEROFBLOODLETTING.get()), AltarTier.TWO.ordinal(), 3000, 5, 5).build(consumer, new ResourceLocation(Dagger_Of_Bloodletting.MODID, basePath + "dagger_of_bloodletting"));
    }
}
