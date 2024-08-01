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
            .comment("Values less than 1 will result in less LP, values greater than 1 result in more LP.")
            .defineInRange("LPMulti", 1.0D, 0.0D, Double.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue SFLPMULTI = BUILDER
            .comment("Multiplier to the amount of lp created by the Sentient Dagger of Bloodletting")
            .comment("Values less than 1 will result in less LP, values greater than 1 result in more LP.")
            .defineInRange("sentientLPMulti", 0.8D, 0.0D, Double.MAX_VALUE);

    private static final ForgeConfigSpec.DoubleValue DOOMULTI = BUILDER
            .comment("Multiplier to the amount of lp created by the Dagger of the Orb")
            .comment("Values less than 1 will result in less LP, values greater than 1 result in more LP.")
            .defineInRange("orbDaggerLPMulti", 0.5D, 0.0D, Double.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue WEAKBOCAPACITY = BUILDER
            .comment("This value is the max LP a Weak Blood Orb bound Dagger of the Orb can add to your network.")
            .defineInRange("weakBloodOrbDaggerCapacity", 5000, 0, Double.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue APPRENTICEBOCAPACITY = BUILDER
            .comment("This value is the max LP an Apprentice Blood Orb bound Dagger of the Orb can add to your network.")
            .defineInRange("apprenticeBloodOrbDaggerCapacity", 25000, 0, Double.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue MAGICIANBOCAPACITY = BUILDER
            .comment("This value is the max LP a Magician Blood Orb bound Dagger of the Orb can add to your network.")
            .defineInRange("magicianBloodOrbDaggerCapacity", 150000, 0, Double.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue MASTERBOCAPACITY = BUILDER
            .comment("This value is the max LP a Master Blood Orb bound Dagger of the Orb can add to your network.")
            .defineInRange("masterBloodOrbDaggerCapacity", 1000000, 0, Double.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue ARCHMAGEBOCAPACITY = BUILDER
            .comment("This value is the max LP an Archmage Blood Orb bound Dagger of the Orb can add to your network.")
            .defineInRange("archmageBloodOrbDaggerCapacity", 10000000, 0, Double.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();
    public static Double lpmulti;
    public static Double slpmulti;
    public static Double doolpmulti;
    public static Double weakbocapacity;
    public static Double apprenticebocapacity;
    public static Double magicianbocapacity;
    public static Double masterbocapacity;
    public static Double archmagebocapacity;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event){
        lpmulti = LPMULTI.get();
        slpmulti = SFLPMULTI.get();
        doolpmulti = DOOMULTI.get();
        weakbocapacity = WEAKBOCAPACITY.get();
        apprenticebocapacity = APPRENTICEBOCAPACITY.get();
        magicianbocapacity = MAGICIANBOCAPACITY.get();
        masterbocapacity = MASTERBOCAPACITY.get();
        archmagebocapacity = ARCHMAGEBOCAPACITY.get();
    }
}
