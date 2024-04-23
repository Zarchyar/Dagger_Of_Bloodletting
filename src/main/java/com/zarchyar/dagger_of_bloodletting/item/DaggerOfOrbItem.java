package com.zarchyar.dagger_of_bloodletting.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

public class DaggerOfOrbItem extends SwordItem implements IBindable {
    public DaggerOfOrbItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        Binding binding = this.getBinding(pStack);
        if (binding != null) {
            pTooltipComponents.add(Component.translatable("tooltip.bloodmagic.currentOwner", new Object[]{binding.getOwnerName()}).withStyle(ChatFormatting.GRAY));
        }
        Integer tagValue = pStack.getTag().getInt("boundorb");
        if (tagValue == 1) pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_1").withStyle(ChatFormatting.GRAY));
        if (tagValue == 2) pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_2").withStyle(ChatFormatting.GRAY));
        if (tagValue == 3) pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_3").withStyle(ChatFormatting.GRAY));
        if (tagValue == 4) pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_4").withStyle(ChatFormatting.GRAY));
        if (tagValue == 5) pTooltipComponents.add(Component.translatable("tooltip.dagger_of_bloodletting.boundorb_5").withStyle(ChatFormatting.GRAY));

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
