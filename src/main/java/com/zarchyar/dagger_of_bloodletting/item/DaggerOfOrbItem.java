package com.zarchyar.dagger_of_bloodletting.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.zarchyar.dagger_of_bloodletting.CommonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;
import wayoftime.bloodmagic.common.item.IBindable;
import wayoftime.bloodmagic.core.data.Binding;

import java.util.List;
import java.util.UUID;

import static com.zarchyar.dagger_of_bloodletting.DOBLAttributes.BLOODLETTING;
import static com.zarchyar.dagger_of_bloodletting.DOBLAttributes.SOULFILLING;

public class DaggerOfOrbItem extends SwordItem {
    public DaggerOfOrbItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Double soulfillingAmount = switch (stack.getTag() != null ? stack.getTag().getInt("boundorb") : 0) {
            case 1 -> CommonConfig.weakbocapacity;
            case 2 -> CommonConfig.apprenticebocapacity;
            case 3 -> CommonConfig.magicianbocapacity;
            case 4 -> CommonConfig.masterbocapacity;
            case 5 -> CommonConfig.archmagebocapacity;
            default -> 0.0D;
        };
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
        modifiers.putAll(super.getAttributeModifiers(slot, stack));
        if (slot != EquipmentSlot.MAINHAND) return modifiers;
        modifiers.put(SOULFILLING.get(), new AttributeModifier(AttributeUUIDs.SWORD.getSoulfilling(), "soulfilling", soulfillingAmount, AttributeModifier.Operation.ADDITION));
        modifiers.put(BLOODLETTING.get(), new AttributeModifier(AttributeUUIDs.SWORD.getBloodletting(), "bloodletting", CommonConfig.doolpmulti, AttributeModifier.Operation.ADDITION));
        return modifiers;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        int tagValue = pStack.getTag() != null ? pStack.getTag().getInt("boundorb") : 0;
        switch (tagValue) {
            case 1 -> pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_1").withStyle(ChatFormatting.GRAY));
            case 2 -> pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_2").withStyle(ChatFormatting.GRAY));
            case 3 -> pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_3").withStyle(ChatFormatting.GRAY));
            case 4 -> pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_4").withStyle(ChatFormatting.GRAY));
            case 5 -> pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_5").withStyle(ChatFormatting.GRAY));
            default -> pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_0").withStyle(ChatFormatting.GRAY));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
