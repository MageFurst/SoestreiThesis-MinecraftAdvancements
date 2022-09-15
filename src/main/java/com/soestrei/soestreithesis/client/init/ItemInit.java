package com.soestrei.soestreithesis.client.init;

import com.soestrei.soestreithesis.client.items.SuspiciousStew;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import static com.soestrei.soestreithesis.SoestreiThesis.MODID;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

    /*
        This class initializes the Suspicious Stew and registers it to the Bus.
        Suspicious Stew's crafting recipe can be found in resources/recipes/.
    */

@ObjectHolder(MODID)
public class ItemInit {

    static Item susStew;

    public static void init() {
        susStew = new SuspiciousStew(6, 1,false);
        susStew.setCreativeTab(CreativeTabs.FOOD);
    }

    public static void registerItems(){
        registerItemData(susStew);
    }

    public static void registerItemRenders(){
        registerItemRender(susStew);
    }

    public static void registerItemData(Item item){
        ForgeRegistries.ITEMS.register(item);
    }

    public static void registerItemRender(Item item){
        ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(item.getRegistryName(),"inventory"));
    }



}
