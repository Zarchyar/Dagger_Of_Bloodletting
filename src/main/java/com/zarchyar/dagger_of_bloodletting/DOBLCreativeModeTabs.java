package com.zarchyar.dagger_of_bloodletting;

import com.zarchyar.dagger_of_bloodletting.item.DOBLItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DOBLCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Dagger_Of_Bloodletting.MODID);
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> DAGGEROFBLOODLETTING = CREATIVE_MODE_TABS.register("daggerofbloodletting",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(DOBLItems.DAGGEROFBLOODLETTING.get()))
                    .title(Component.translatable("creativetab.daggerofbloodlettingtab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(DOBLItems.DAGGEROFBLOODLETTING.get());
                        output.accept(DOBLItems.SFBLDAGGER.get());
                        output.accept(DOBLItems.DAGGEROFORB.get());
                    }).build());
}
