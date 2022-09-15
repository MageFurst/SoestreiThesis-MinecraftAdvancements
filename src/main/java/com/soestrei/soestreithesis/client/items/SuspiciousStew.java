package com.soestrei.soestreithesis.client.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

/*
    This class holds the data for the Suspicious Stew item. When eaten, it
    will inflict the player with the Blindness effect. It is crafted with
    Mushroom Stew and Azure Bluet.
*/

public class SuspiciousStew extends ItemFood {

    public SuspiciousStew(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setRegistryName("suspicious_stew");
        this.setUnlocalizedName("suspicious_stew");
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World world, EntityLivingBase entityLiving){ // If the player eats Suspicious Stew
        entityLiving.addPotionEffect(new PotionEffect(MobEffects.BLINDNESS, 500, 5, false, false));
        return super.onItemUseFinish(stack, world, entityLiving); //Add the 'Blindness' effect for 500 milliseconds
    }
}
