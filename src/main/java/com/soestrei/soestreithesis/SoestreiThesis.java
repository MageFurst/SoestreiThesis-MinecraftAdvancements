package com.soestrei.soestreithesis;

import  com.soestrei.soestreithesis.client.init.ArmorInit;
import  com.soestrei.soestreithesis.client.init.ItemInit;
import com.soestrei.soestreithesis.client.init.PotionInit;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

    /*
    This class registers the items, armors, and potions, along with their renders, as the game loads.
     */

@Mod(modid = SoestreiThesis.MODID, name = SoestreiThesis.NAME, version = SoestreiThesis.VERSION)
public class SoestreiThesis
{
    public static final String MODID = "soestreithesis";
    public static final String NAME = "Thesis";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ItemInit.init();
        ArmorInit.init();
        PotionInit.registerPotions();
        ItemInit.registerItems();
        ArmorInit.registerArmors();
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        ItemInit.registerItemRenders();
        ArmorInit.registerArmorRenders();
    }



}
