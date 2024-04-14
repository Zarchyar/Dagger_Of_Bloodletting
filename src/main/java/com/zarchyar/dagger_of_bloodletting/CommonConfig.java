package com.zarchyar.dagger_of_bloodletting;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

@Mod.EventBusSubscriber(modid = Dagger_Of_Bloodletting.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonConfig {
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();

    private static final ForgeConfigSpec.DoubleValue LPMULTI = BUILDER
            .comment("Multiplier to the amount of lp created by the Dagger of Bloodletting")
            .comment("Values less then 1 will result in less LP, values greater then 1 result in more LP.")
            .defineInRange("LPMulti", 1.0D, 0.0D, Double.MAX_VALUE);

    private static final ForgeConfigSpec.DoubleValue SFLPMULTI = BUILDER
            .comment("Multiplier to the amount of lp created by the Sentient Dagger of Bloodletting")
            .comment("Values less then 1 will result in less LP, values greater then 1 result in more LP.")
            .defineInRange("sentientLPMulti", 0.8D, 0.0D, Double.MAX_VALUE);
    static final ForgeConfigSpec SPEC = BUILDER.build();
    public static Double lpmulti;
    public static Double slpmulti;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event){
        lpmulti = LPMULTI.get();
        slpmulti = SFLPMULTI.get();
    }
}
