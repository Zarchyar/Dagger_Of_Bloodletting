package com.zarchyar.dagger_of_bloodletting;

import com.zarchyar.dagger_of_bloodletting.item.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Dagger_Of_Bloodletting.MODID);
    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> DAGGEROFBLOODLETTING = CREATIVE_MODE_TABS.register("daggerofbloodletting",
            ()-> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.DAGGEROFBLOODLETTING.get()))
                    .title(Component.translatable("creativetab.daggerofbloodlettingtab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.DAGGEROFBLOODLETTING.get());
                        output.accept(ModItems.SFBLDAGGER.get());
                    }).build());
}
