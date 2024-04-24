package com.zarchyar.dagger_of_bloodletting.item;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wayoftime.bloodmagic.common.item.soul.ItemSentientSword;

public class DOBLItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Dagger_Of_Bloodletting.MODID);
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }

    public static final RegistryObject<Item> DAGGEROFBLOODLETTING = ITEMS.register("daggerofbloodletting",
            ()-> new SwordItem(DOBLToolTiers.BLOODLETTING, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> SFBLDAGGER = ITEMS.register("sfbldagger",
            ()-> new ItemSentientSword());

    public static final RegistryObject<Item> DAGGEROFORB = ITEMS.register("daggeroforb",
            ()-> new DaggerOfOrbItem(DOBLToolTiers.BLOODLETTING, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> BLADEOFBLOODLETTING = ITEMS.register("bladeofbloodletting",
            ()-> new Item(new Item.Properties().stacksTo(1)));
}
