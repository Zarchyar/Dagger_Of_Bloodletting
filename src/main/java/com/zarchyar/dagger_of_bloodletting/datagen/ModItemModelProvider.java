package com.zarchyar.dagger_of_bloodletting.datagen;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import wayoftime.bloodmagic.BloodMagic;
import wayoftime.bloodmagic.api.compat.EnumDemonWillType;

import java.util.Locale;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Dagger_Of_Bloodletting.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        handheldItem(ModItems.DAGGEROFBLOODLETTING);
        //handheldItem(ModItems.SOULFORGEDBLOODLETTINGDAGGER);
        registerDemonSword(ModItems.SFBLDAGGER);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Dagger_Of_Bloodletting.MODID,"item/" + item.getId().getPath()));
    }
    private void registerDemonSword(RegistryObject<Item> item) {
        String path = item.getId().getPath();
        ItemModelBuilder builder = getBuilder(path);

        for (int i = 0; i <= 1; i++) {
            for (EnumDemonWillType type : EnumDemonWillType.values()) {
                String name = i == 0 ? "_deactivated" : "_activated";
                if (type.ordinal() != 0) {
                    name = "_" + type.name().toLowerCase(Locale.ROOT) + name;
                }
                ModelFile willFile = singleTexture("item/variants/" + path + name, mcLoc("item/handheld"), "layer0", modLoc("item/" + path + name));
                builder = builder.override().predicate(BloodMagic.rl("type"), type.ordinal()).predicate(BloodMagic.rl("active"), i).model(willFile).end();
            }
        }
    }
}