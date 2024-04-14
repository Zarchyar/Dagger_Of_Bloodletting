package com.zarchyar.dagger_of_bloodletting.item;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wayoftime.bloodmagic.common.item.soul.ItemSentientSword;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dagger_Of_Bloodletting.MODID);
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> DAGGEROFBLOODLETTING = ITEMS.register("daggerofbloodletting",
            ()-> new SwordItem(ModToolTiers.BLOODLETTING, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> SFBLDAGGER = ITEMS.register("sfbldagger",
            ()-> new ItemSentientSword());
}
