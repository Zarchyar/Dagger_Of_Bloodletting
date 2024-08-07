package com.zarchyar.dagger_of_bloodletting.events;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.DOBLItems;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.ItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraft.client.renderer.item.ItemProperties;
import org.jetbrains.annotations.Nullable;
import wayoftime.bloodmagic.BloodMagic;
import wayoftime.bloodmagic.common.item.soul.ItemSentientSword;

import static wayoftime.bloodmagic.client.ClientEvents.registerMultiWillTool;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Dagger_Of_Bloodletting.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    @SuppressWarnings("deprecation")
    @SubscribeEvent
    public static void initClientEvents(FMLClientSetupEvent event){
        event.enqueueWork(()->{
            registerMultiWillTool(DOBLItems.SFBLDAGGER.get());

            ItemProperties.register((Item) DOBLItems.SFBLDAGGER.get(), BloodMagic.rl("active"), new ItemPropertyFunction() {
                public float call(ItemStack stack, ClientLevel world, LivingEntity entity, int value) {
                    return ((ItemSentientSword)stack.getItem()).getActivated(stack) ? 1.0F : 0.0F;
                }
            });
            ItemProperties.register(DOBLItems.DAGGEROFORB.get(), new ResourceLocation(Dagger_Of_Bloodletting.MODID, "boundorb"), new ItemPropertyFunction() {
                @Override
                public float call(ItemStack pStack, @Nullable ClientLevel pLevel, @Nullable LivingEntity pEntity, int pSeed) {
                    try {
                        return Math.min(pStack.getTag().getInt("boundorb"), 0);
                    } catch (Exception e) {
                        return 0;
                    }
                }
            });
        });
    }
}
