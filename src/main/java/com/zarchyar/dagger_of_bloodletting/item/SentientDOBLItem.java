package com.zarchyar.dagger_of_bloodletting.item;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.zarchyar.dagger_of_bloodletting.CommonConfig;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import wayoftime.bloodmagic.common.item.soul.ItemSentientSword;

import static com.zarchyar.dagger_of_bloodletting.DOBLAttributes.BLOODLETTING;

public class SentientDOBLItem extends ItemSentientSword {
    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
        Multimap<Attribute, AttributeModifier> modifiers = HashMultimap.create();
        modifiers.putAll(super.getAttributeModifiers(slot, stack));
        if (slot != EquipmentSlot.MAINHAND) return modifiers;
        modifiers.put(BLOODLETTING.get(), new AttributeModifier(AttributeUUIDs.SWORD.getBloodletting(), "bloodletting", CommonConfig.slpmulti, AttributeModifier.Operation.ADDITION));
        return modifiers;
    }
}
