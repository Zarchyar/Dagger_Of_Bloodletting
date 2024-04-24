package com.zarchyar.dagger_of_bloodletting.datagen;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.DOBLItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import wayoftime.bloodmagic.altar.AltarTier;
import wayoftime.bloodmagic.common.data.recipe.builder.AlchemyTableRecipeBuilder;
import wayoftime.bloodmagic.common.data.recipe.builder.BloodAltarRecipeBuilder;
import wayoftime.bloodmagic.common.data.recipe.builder.TartaricForgeRecipeBuilder;
import wayoftime.bloodmagic.common.item.BloodMagicItems;
import wayoftime.bloodmagic.common.item.BloodOrb;
import wayoftime.bloodmagic.common.registration.impl.BloodOrbDeferredRegister;
import wayoftime.bloodmagic.common.registration.impl.BloodOrbRegistryObject;

import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class DOBLRecipes extends RecipeProvider implements IConditionBuilder {
    public DOBLRecipes(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        String forgePath = "soulforge/";
        String altarPath = "altar/";
        BloodAltarRecipeBuilder.altar(Ingredient.of(BloodMagicItems.SACRIFICIAL_DAGGER.get()), new ItemStack(DOBLItems.DAGGEROFBLOODLETTING.get()), AltarTier.TWO.ordinal(), 3000, 5, 5).build(consumer, new ResourceLocation(Dagger_Of_Bloodletting.MODID, altarPath + "dagger_of_bloodletting"));
        TartaricForgeRecipeBuilder.tartaricForge(new ItemStack(DOBLItems.SFBLDAGGER.get()), 1000, 250, Ingredient.of(DOBLItems.DAGGEROFBLOODLETTING.get()), Ingredient.of(Items.NETHERITE_SCRAP), Ingredient.of(BloodMagicItems.COMMON_GEM.get()), Ingredient.of(BloodMagicItems.SENTIENT_SWORD.get())).build(consumer, new ResourceLocation(Dagger_Of_Bloodletting.MODID, forgePath + "sfbldagger"));
        AlchemyTableRecipeBuilder.alchemyTable(new ItemStack(DOBLItems.DAGGEROFORB.get()), 4000, 200, 4).addIngredient(Ingredient.of(DOBLItems.BLADEOFBLOODLETTING.get())).addIngredient(Ingredient.of(Items.GLASS_PANE)).addIngredient(Ingredient.of(Items.NETHERITE_SCRAP)).addIngredient(Ingredient.of(BloodMagicItems.WEAK_BLOOD_SHARD.get())).addIngredient(Ingredient.of(BloodMagicItems.TAU_OIL.get())).addIngredient(Ingredient.of(BloodMagicItems.HELLFORGED_INGOT.get())).build(consumer, new ResourceLocation(Dagger_Of_Bloodletting.MODID, "dagger_of_the_orb"));

        oreBlasting(consumer, List.of(DOBLItems.DAGGEROFBLOODLETTING.get()), RecipeCategory.MISC, DOBLItems.BLADEOFBLOODLETTING.get(), 0.25f, 100, "daggerblade");

    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        Iterator var9 = pIngredients.iterator();

        while(var9.hasNext()) {
            ItemLike itemlike = (ItemLike)var9.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(new ItemLike[]{itemlike}), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, Dagger_Of_Bloodletting.MODID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
