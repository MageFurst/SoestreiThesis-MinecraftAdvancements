package com.soestrei.soestreithesis.client.init;

import com.soestrei.soestreithesis.SoestreiThesis;
import com.soestrei.soestreithesis.client.items.WitherArmor;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import static com.soestrei.soestreithesis.SoestreiThesis.MODID;

    /*
        This class initializes the Wither Armor and registers it to the Bus.
        Wither Helm's crafting recipe can be found in resources/recipes/.
    */

@GameRegistry.ObjectHolder(MODID)
public class ArmorInit {

    static ItemArmor.ArmorMaterial ARMOR_WITHER = EnumHelper.addArmorMaterial("armor_wither", SoestreiThesis.MODID + ":wither", 1500, new int[] {0,0,0,1}, 0, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f);

    static Item witherHelmet = new Item();

    public static void init() {
        witherHelmet = new WitherArmor(ARMOR_WITHER, -1, EntityEquipmentSlot.HEAD);
    }

    public static void registerArmors(){
        registerArmorData(witherHelmet);
    }

    public static void registerArmorRenders(){
        registerItemRender(witherHelmet);
    }

    public static void registerArmorData(Item item){
        ForgeRegistries.ITEMS.register(item);
    }

    public static void registerItemRender(Item item){
        ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
    }

}
