package com.zarchyar.dagger_of_bloodletting.datagen;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import wayoftime.bloodmagic.altar.AltarTier;
import wayoftime.bloodmagic.common.data.recipe.builder.BloodAltarRecipeBuilder;
import wayoftime.bloodmagic.common.data.recipe.builder.TartaricForgeRecipeBuilder;
import wayoftime.bloodmagic.common.item.BloodMagicItems;

import java.util.function.Consumer;

public class ModRecipes extends RecipeProvider implements IConditionBuilder {
    public ModRecipes(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String forgePath = "soulforge/";
        String altarPath = "altar/";
        BloodAltarRecipeBuilder.altar(Ingredient.of(BloodMagicItems.SACRIFICIAL_DAGGER.get()), new ItemStack(ModItems.DAGGEROFBLOODLETTING.get()), AltarTier.TWO.ordinal(), 3000, 5, 5).build(consumer, new ResourceLocation(Dagger_Of_Bloodletting.MODID, altarPath + "dagger_of_bloodletting"));
        TartaricForgeRecipeBuilder.tartaricForge(new ItemStack(ModItems.SFBLDAGGER.get()), 1000, 250, Ingredient.of(ModItems.DAGGEROFBLOODLETTING.get()), Ingredient.of(Items.NETHERITE_SCRAP), Ingredient.of(BloodMagicItems.COMMON_GEM.get()), Ingredient.of(BloodMagicItems.SENTIENT_SWORD.get())).build(consumer, new ResourceLocation(Dagger_Of_Bloodletting.MODID, forgePath + "sfbldagger"));

    }
}
