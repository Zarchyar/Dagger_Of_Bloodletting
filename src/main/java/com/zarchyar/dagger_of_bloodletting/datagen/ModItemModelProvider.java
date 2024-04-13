package com.zarchyar.dagger_of_bloodletting.datagen;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Dagger_Of_Bloodletting.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.DAGGEROFBLOODLETTING);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Dagger_Of_Bloodletting.MODID,"item/" + item.getId().getPath()));
    }
}