package com.zarchyar.dagger_of_bloodletting.events;

import com.zarchyar.dagger_of_bloodletting.Dagger_Of_Bloodletting;
import com.zarchyar.dagger_of_bloodletting.CommonConfig;
import com.zarchyar.dagger_of_bloodletting.item.DOBLItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import wayoftime.bloodmagic.core.data.Binding;
import wayoftime.bloodmagic.core.data.SoulNetwork;
import wayoftime.bloodmagic.core.data.SoulTicket;
import wayoftime.bloodmagic.impl.BloodMagicAPI;
import wayoftime.bloodmagic.util.helper.NetworkHelper;
import wayoftime.bloodmagic.util.helper.PlayerSacrificeHelper;

public class DOBLEvents {

    @Mod.EventBusSubscriber(modid = Dagger_Of_Bloodletting.MODID)
    public static class ForgeEvents {
        @SubscribeEvent(priority = EventPriority.LOWEST)
        public static void onLivingDamage(LivingDamageEvent event){
            if (event.getSource().getEntity() instanceof Player player) {
                LivingEntity pTarget = event.getEntity();
                ResourceLocation id = ForgeRegistries.ENTITY_TYPES.getKey(pTarget.getType());
                int lifeEssenceRatio = (Integer) BloodMagicAPI.INSTANCE.getValueManager().getSacrificial().getOrDefault(id, 25);
                if (!(lifeEssenceRatio <= 0)){
                    Boolean playsound = false;
                    int lifeEssence = (int)((float)lifeEssenceRatio * event.getAmount());
                    ItemStack mainHandItem = player.getMainHandItem();
                    if (event.getEntity().isBaby()) {
                        lifeEssence = (int)((float)lifeEssence * 0.5F);
                    }
                    if (mainHandItem.is(DOBLItems.DAGGEROFBLOODLETTING.get())){
                        lifeEssence = (int)((float)lifeEssence * CommonConfig.lpmulti);
                        playsound = PlayerSacrificeHelper.findAndFillAltar(player.getCommandSenderWorld(), pTarget, lifeEssence, true);
                    }
                    if (mainHandItem.is(DOBLItems.SFBLDAGGER.get())){
                        lifeEssence = (int)((float)lifeEssence * CommonConfig.slpmulti);
                        playsound = PlayerSacrificeHelper.findAndFillAltar(player.getCommandSenderWorld(), pTarget, lifeEssence, true);
                    }
                    if (mainHandItem.is(DOBLItems.DAGGEROFORB.get())) {
                        lifeEssence = (int)((float)lifeEssence * CommonConfig.doolpmulti);

                        Integer boundOrb = mainHandItem.getTag().getInt("boundorb");
                        Integer maxLP = switch (boundOrb) {
                            case 1 -> CommonConfig.weakbocapacity;
                            case 2 -> CommonConfig.apprenticebocapacity;
                            case 3 -> CommonConfig.magicianbocapacity;
                            case 4 -> CommonConfig.masterbocapacity;
                            case 5 -> CommonConfig.archmagebocapacity;
                            default -> 0;
                        };

                        Binding binding = Binding.fromStack(mainHandItem);
                        if (binding != null){
                            SoulNetwork ownerNetwork = NetworkHelper.getSoulNetwork(binding);
                            playsound = 0 != ownerNetwork.add(SoulTicket.item(mainHandItem, player.getCommandSenderWorld(), player, lifeEssence), maxLP);
                        }
                    }
                    if (playsound) pTarget.getCommandSenderWorld().playSound((Player)null, pTarget.getX(), pTarget.getY(), pTarget.getZ(), SoundEvents.FIRE_EXTINGUISH, SoundSource.BLOCKS, 0.5F, 2.6F + (pTarget.getCommandSenderWorld().random.nextFloat() - pTarget.getCommandSenderWorld().random.nextFloat()) * 0.8F);
                }
            }
        }
    }
}
