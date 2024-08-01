package com.zarchyar.dagger_of_bloodletting;

import com.mojang.logging.LogUtils;
import com.zarchyar.dagger_of_bloodletting.item.DOBLItems;
import com.zarchyar.dagger_of_bloodletting.recipe.DOBLRecipes;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
@Mod(Dagger_Of_Bloodletting.MODID)
public class Dagger_Of_Bloodletting {
    public static final String MODID = "dagger_of_bloodletting";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Dagger_Of_Bloodletting() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        DOBLCreativeModeTabs.register(modEventBus);
        DOBLItems.register(modEventBus);

        DOBLRecipes.register(modEventBus);

        DOBLAttributes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, CommonConfig.SPEC);
    }
}
