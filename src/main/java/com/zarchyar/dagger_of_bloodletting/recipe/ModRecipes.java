package com.zarchyar.dagger_of_bloodletting.recipe;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Dagger_Of_Bloodletting.MODID);
    public static final RegistryObject<RecipeSerializer<OrbDaggerBindRecipe>> ORBDAGGERBINDINGSERIALIZER =
            SERIALIZERS.register("daggerorbbinding", ()-> OrbDaggerBindRecipe.Serializer.INSTANCE);
    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
