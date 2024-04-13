package com.zarchyar.dagger_of_bloodletting.events;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.item.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import wayoftime.bloodmagic.impl.BloodMagicAPI;
import wayoftime.bloodmagic.util.helper.PlayerSacrificeHelper;

public class ModEvents {

    @Mod.EventBusSubscriber(modid = Dagger_Of_Bloodletting.MODID)
    public static class ForgeEvents {
        @SubscribeEvent
        public static void onLivingDamage(LivingDamageEvent event){
            if (event.getSource().getEntity() instanceof Player player) {
                LivingEntity pTarget = event.getEntity();
                if (player.getMainHandItem().is(ModItems.DAGGEROFBLOODLETTING.get())) {
                    ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(pTarget.getType());
                    int lifeEssenceRatio = (Integer) BloodMagicAPI.INSTANCE.getValueManager().getSacrificial().getOrDefault(id, 25);
                    if (!(lifeEssenceRatio <= 0)){
                        int lifeEssence = (int)((float)lifeEssenceRatio * event.getAmount());
                        if (event.getEntity().isBaby()) {
                            lifeEssence = (int)((float)lifeEssence * 0.5F);
                        }
                        if (PlayerSacrificeHelper.findAndFillAltar(player.getCommandSenderWorld(), pTarget, lifeEssence, true)) {
                            pTarget.getCommandSenderWorld().playSound((Player)null, pTarget.getX(), pTarget.getY(), pTarget.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (pTarget.getCommandSenderWorld().random.nextFloat() - pTarget.getCommandSenderWorld().random.nextFloat()) * 0.8F);
                        }
                    }

                }
            }
        }
    }
}
