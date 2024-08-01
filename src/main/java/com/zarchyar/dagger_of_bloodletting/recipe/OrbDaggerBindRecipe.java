package com.zarchyar.dagger_of_bloodletting.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.DOBLItems;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import wayoftime.bloodmagic.common.item.BloodMagicItems;
import wayoftime.bloodmagic.common.item.BloodOrb;
import wayoftime.bloodmagic.common.item.ItemBloodOrb;

import java.util.List;

public class OrbDaggerBindRecipe extends CustomRecipe {
    private Ingredient input = Ingredient.of(DOBLItems.DAGGEROFORB.get());
    private final Ingredient orbIngredient = Ingredient.of(BloodMagicItems.WEAK_BLOOD_ORB.get(), BloodMagicItems.APPRENTICE_BLOOD_ORB.get(), BloodMagicItems.MAGICIAN_BLOOD_ORB.get(), BloodMagicItems.MASTER_BLOOD_ORB.get(), BloodMagicItems.ARCHMAGE_BLOOD_ORB.get());

    public OrbDaggerBindRecipe(ResourceLocation pId, CraftingBookCategory pCategory) {
        super(pId, pCategory);
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide) {
            return false;
        }
        List<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < pContainer.getContainerSize(); i++) {
            ItemStack itemStack = pContainer.getItem(i);
            if (!itemStack.isEmpty()){
                list.add(itemStack);
            }
        }
        if (list.size() == 2){
            return (orbIngredient.test(list.get(0)) ^ orbIngredient.test(list.get(1))) && ((input.test(list.get(0))) ^ (input.test(list.get(1))));
        }
        return false;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack orbItem = new ItemStack(ItemStack.EMPTY.getItem());
        ItemStack swordItem = new ItemStack(DOBLItems.DAGGEROFORB.get());
        int i = 0;

        for (int j = 0; j < pContainer.getContainerSize(); j++) {
            ItemStack itemStack = pContainer.getItem(j);
            if (!itemStack.isEmpty()) {
                i++;
                if (orbIngredient.test(itemStack)){
                    orbItem = itemStack.copy();
                }
                if (input.test(itemStack)){
                    swordItem = itemStack.copy();
                }
            }
        }

        CompoundTag swordTags = swordItem.getTag() != null ? swordItem.getTag().copy(): new CompoundTag();
        if(i != 2) return swordItem;
        BloodOrb orb = ((ItemBloodOrb) orbItem.getItem()).getOrb(orbItem);
        int orbTier = orb == null ? 0 : orb.getTier();
        if (orbTier < 6 ){
            swordTags.putInt("boundorb", orbTier);
            swordItem.setTag(swordTags);
        }
        return swordItem;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return pWidth * pHeight >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    public static class Serializer implements RecipeSerializer<OrbDaggerBindRecipe>{
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(Dagger_Of_Bloodletting.MODID, "dagerorbbinding");

        @Override
        public OrbDaggerBindRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            CraftingBookCategory craftingbookcategory = CraftingBookCategory.CODEC.byName(GsonHelper.getAsString(pSerializedRecipe, "category", (String)null), CraftingBookCategory.MISC);
            return new OrbDaggerBindRecipe(pRecipeId, craftingbookcategory);
        }

        @Override
        public @Nullable OrbDaggerBindRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            CraftingBookCategory craftingbookcategory = pBuffer.readEnum(CraftingBookCategory.class);
            return new OrbDaggerBindRecipe(pRecipeId, craftingbookcategory);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, OrbDaggerBindRecipe pRecipe) {
            pBuffer.writeEnum(pRecipe.category());
        }
    }
}
