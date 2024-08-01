package com.zarchyar.dagger_of_bloodletting.events;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import wayoftime.bloodmagic.core.data.SoulNetwork;
import wayoftime.bloodmagic.core.data.SoulTicket;
import wayoftime.bloodmagic.impl.BloodMagicAPI;
import wayoftime.bloodmagic.util.helper.NetworkHelper;
import wayoftime.bloodmagic.util.helper.PlayerSacrificeHelper;

import static com.zarchyar.dagger_of_bloodletting.DOBLAttributes.BLOODLETTING;
import static com.zarchyar.dagger_of_bloodletting.DOBLAttributes.SOULFILLING;

public class DOBLEvents {
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Dagger_Of_Bloodletting.MODID)
    public static class ModEvents {
        @SubscribeEvent
        public static void entityAttributeModificationEvent (EntityAttributeModificationEvent event) {
            if (!event.has(EntityType.PLAYER, BLOODLETTING.get())) {
                event.add(EntityType.PLAYER, BLOODLETTING.get(), 0.0);
            }
            if (!event.has(EntityType.PLAYER, SOULFILLING.get())) {
                event.add(EntityType.PLAYER, SOULFILLING.get(), 0.0);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Dagger_Of_Bloodletting.MODID)
    public static class ForgeEvents {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onLivingDamage(LivingDamageEvent event){
            if (event.getSource().getEntity() instanceof Player player) {
                LivingEntity pTarget = event.getEntity();
                ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(pTarget.getType());
                int lifeEssenceRatio = (int) BloodMagicAPI.INSTANCE.getValueManager().getSacrificial().getOrDefault(id, 25);
                if (!(lifeEssenceRatio <= 0)){
                    boolean playSound;
                    int lifeEssence = (int)((float)lifeEssenceRatio * event.getAmount());
                    if (event.getEntity().isBaby()) {
                        lifeEssence = (int)((float)lifeEssence * 0.5F);
                    }
                    ItemStack mainHandItem = player.getMainHandItem();

                    AttributeInstance bloodlettingInstance = player.getAttribute(BLOODLETTING.get());
                    AttributeInstance soulfillingInstance = player.getAttribute(SOULFILLING.get());

                    Double bloodlettingMULTI = bloodlettingInstance == null ? 0 : Math.max(0, bloodlettingInstance.getValue());
                    int soulfillingCap = soulfillingInstance == null ? 0 : Math.max(0, (int) soulfillingInstance.getValue());
                    SoulNetwork ownerNetwork = NetworkHelper.getSoulNetwork(player);

                    lifeEssence = (int)((float)lifeEssence * bloodlettingMULTI);

                    int networkAdded = ownerNetwork.add(SoulTicket.item(mainHandItem, player.getCommandSenderWorld(), player, lifeEssence), soulfillingCap);
                    lifeEssence -= networkAdded;

                    playSound = ((lifeEssence > 0 && (PlayerSacrificeHelper.findAndFillAltar(player.getCommandSenderWorld(), pTarget, lifeEssence, true))) || networkAdded != 0);

                    if (playSound) pTarget.getCommandSenderWorld().playSound((Player)null, pTarget.getX(), pTarget.getY(), pTarget.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (pTarget.getCommandSenderWorld().random.nextFloat() - pTarget.getCommandSenderWorld().random.nextFloat()) * 0.8F);
                }
            }
        }
    }
}