package com.soestrei.soestreithesis.client.init;

import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHelper;
import net.minecraft.potion.PotionType;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

    /*
        This class initializes the Potions of Levitation, sets their potion recipes, and registers them to the Bus.
        Potions of Levitation are created using a Shulker Shell with an Awkward Potion.
        Short potions last one minute, long potions last two minutes.
    */

public class PotionInit {

    public static PotionType POTION_OF_LEVITATION = new PotionType("levitation", new PotionEffect[] {new PotionEffect(MobEffects.LEVITATION, 1200)}).setRegistryName("your_potion");
    public static PotionType LONG_POTION_OF_LEVITATION = new PotionType("levitation", new PotionEffect[] {new PotionEffect(MobEffects.LEVITATION, 2400)}).setRegistryName("long_your_potion");

    public static void registerPotions(){

        registerEffectPotion(POTION_OF_LEVITATION, LONG_POTION_OF_LEVITATION);
        registerPotionMixes();

    }

    private static void registerEffectPotion(PotionType defaultPotion, PotionType longPotion){
        ForgeRegistries.POTION_TYPES.register(defaultPotion);
        ForgeRegistries.POTION_TYPES.register(longPotion);

    }

    private static void registerPotionMixes(){
        PotionHelper.addMix(POTION_OF_LEVITATION, Items.REDSTONE, LONG_POTION_OF_LEVITATION);
        PotionHelper.addMix(PotionTypes.AWKWARD, Items.SHULKER_SHELL, POTION_OF_LEVITATION);
    }

}
