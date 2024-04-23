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
            .comment("Values less then 1 will result in less LP, values greater than 1 result in more LP.")
            .defineInRange("LPMulti", 1.0D, 0.0D, Double.MAX_VALUE);
    private static final ForgeConfigSpec.DoubleValue SFLPMULTI = BUILDER
            .comment("Multiplier to the amount of lp created by the Sentient Dagger of Bloodletting")
            .comment("Values less then 1 will result in less LP, values greater than 1 result in more LP.")
            .defineInRange("sentientLPMulti", 0.8D, 0.0D, Double.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue WEAKBOCAPACITY = BUILDER
            .comment("This value is the max LP a Weak Blood Orb attuned Dagger of the Orb can add to your network.")
            .defineInRange("weakBloodOrbDaggerCapacity", 5000, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue APPRENTICEBOCAPACITY = BUILDER
            .comment("This value is the max LP a Apprentice Blood Orb attuned Dagger of the Orb can add to your network.")
            .defineInRange("apprenticeBloodOrbDaggerCapacity", 25000, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue MAGICIANBOCAPACITY = BUILDER
            .comment("This value is the max LP a Magician Blood Orb attuned Dagger of the Orb can add to your network.")
            .defineInRange("magicianBloodOrbDaggerCapacity", 150000, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue MASTERBOCAPACITY = BUILDER
            .comment("This value is the max LP a Master Blood Orb attuned Dagger of the Orb can add to your network.")
            .defineInRange("masterBloodOrbDaggerCapacity", 1000000, 0, Integer.MAX_VALUE);
    private static final ForgeConfigSpec.IntValue ARCHMAGEBOCAPACITY = BUILDER
            .comment("This value is the max LP a Archmage Blood Orb attuned Dagger of the Orb can add to your network.")
            .defineInRange("archmageBloodOrbDaggerCapacity", 10000000, 0, Integer.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();
    public static Double lpmulti;
    public static Double slpmulti;
    public static int weakbocapacity;
    public static int apprenticebocapacity;
    public static int magicianbocapacity;
    public static int masterbocapacity;
    public static int archmagebocapacity;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event){
        lpmulti = LPMULTI.get();
        slpmulti = SFLPMULTI.get();
        weakbocapacity = WEAKBOCAPACITY.get();
        apprenticebocapacity = APPRENTICEBOCAPACITY.get();
        magicianbocapacity = MAGICIANBOCAPACITY.get();
        masterbocapacity = MASTERBOCAPACITY.get();
        archmagebocapacity = ARCHMAGEBOCAPACITY.get();
    }
}
