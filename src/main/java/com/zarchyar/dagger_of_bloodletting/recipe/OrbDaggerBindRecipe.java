package com.zarchyar.dagger_of_bloodletting.recipe;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.ModItems;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import wayoftime.bloodmagic.common.item.BloodMagicItems;

import java.util.List;

public class OrbDaggerBindRecipe extends CustomRecipe {
    private ItemStack output = new ItemStack(ModItems.DAGGEROFORB.get());
    private final NonNullList<Ingredient> bloodOrbs = NonNullList.of(
            Ingredient.of(BloodMagicItems.WEAK_BLOOD_ORB.get()),
            Ingredient.of(BloodMagicItems.APPRENTICE_BLOOD_ORB.get()),
            Ingredient.of(BloodMagicItems.MAGICIAN_BLOOD_ORB.get()),
            Ingredient.of(BloodMagicItems.MASTER_BLOOD_ORB.get()),
            Ingredient.of(BloodMagicItems.ARCHMAGE_BLOOD_ORB.get())
    );

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
                if (list.size() > 2) {return false;}
                if (list.size() > 1){
                    if ((bloodOrbs.contains(Ingredient.of(list.get(0).getItem())) ^ bloodOrbs.contains(Ingredient.of(list.get(1).getItem()))) && (list.get(0).is(output.getItem()) ^ list.get(1).is(output.getItem()))){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer, RegistryAccess pRegistryAccess) {
        ItemStack orbItem = null;
        Integer i = 0;

        for (int j = 0; j < pContainer.getContainerSize(); j++) {
            ItemStack itemStack = pContainer.getItem(j);
            if (!itemStack.isEmpty()) {
                i++;
                if (bloodOrbs.contains(Ingredient.of(itemStack.getItem()))){
                    orbItem = itemStack;
                }
                if (itemStack.is(output.getItem())){
                    this.output = itemStack.copy();
                }
            }
            if (i == 2){
                if (bloodOrbs.get(0).test(orbItem)){
                    this.output.setTag((CompoundTag) this.output.getTag().copy().put("boundorb", IntTag.valueOf(0)));
                }
                if (bloodOrbs.get(1).test(orbItem)){
                    this.output.setTag((CompoundTag) this.output.getTag().copy().put("boundorb", IntTag.valueOf(1)));
                }
                if (bloodOrbs.get(2).test(orbItem)){
                    this.output.setTag((CompoundTag) this.output.getTag().copy().put("boundorb", IntTag.valueOf(2)));
                }
                if (bloodOrbs.get(3).test(orbItem)){
                    this.output.setTag((CompoundTag) this.output.getTag().copy().put("boundorb", IntTag.valueOf(3)));
                }
                if (bloodOrbs.get(4).test(orbItem)){
                    this.output.setTag((CompoundTag) this.output.getTag().copy().put("boundorb", IntTag.valueOf(4)));
                }
            }
        }
        return this.output;
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
