package  com.soestrei.soestreithesis.client.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

    /*
        This class holds the data for the Wither Armor, which is only used for the Wither Helm item. When worn, it
        will give the player the 'Wither' effect for as long as it is worn.
    */

public class WitherArmor extends ItemArmor {

    private Potion effect;

    public WitherArmor(ItemArmor.ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
        super(materialIn, renderIndexIn, equipmentSlotIn);
        this.setRegistryName("wither_helmet");
        this.setUnlocalizedName("wither_helmet");
    }

    @Override
    public void onArmorTick(final World world, final EntityPlayer player, final ItemStack itemStack) {
        if (!player.isPotionActive(effect)) { // If the player is wearing the armor, and there is no effect active
            player.addPotionEffect(new PotionEffect(MobEffects.WITHER, 10, 3, false, false)); //Add the 'Wither' effect
        }
    }
}
