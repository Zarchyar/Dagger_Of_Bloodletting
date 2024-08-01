package com.zarchyar.dagger_of_bloodletting;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class DOBLAttributes {
    public static final DeferredRegister<Attribute> ATTRIBUTES = DeferredRegister.create(ForgeRegistries.Keys.ATTRIBUTES, Dagger_Of_Bloodletting.MODID);
    public static final RegistryObject<Attribute> BLOODLETTING = ATTRIBUTES.register("bloodletting", () -> new RangedAttribute("attribute.dobl.bloodletting", 0, 0, Double.MAX_VALUE).setSyncable(true));
    public static final RegistryObject<Attribute> SOULFILLING = ATTRIBUTES.register("soulfilling", () -> new RangedAttribute("attribute.dobl.soulfilling", 0, 0, Double.MAX_VALUE).setSyncable(true));

    public static void register(IEventBus eventBus){
        ATTRIBUTES.register(eventBus);
    }
}
