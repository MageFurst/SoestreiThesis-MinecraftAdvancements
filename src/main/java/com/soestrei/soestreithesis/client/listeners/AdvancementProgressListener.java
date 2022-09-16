package com.soestrei.soestreithesis.client.listeners;

import com.soestrei.soestreithesis.client.init.AdvancementInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

    /*
        This class is the main 'brains' of the mod. It handles all four of the checklist elements, all complete with the ability to create, edit, and delete.
        There are four main handlers, for each type of checklist. Each one fires when an event occurs in game corresponding to the checklist's requirements.
        It will then delete an element from the checklist based on what was done.
    */

@Mod.EventBusSubscriber
public class AdvancementProgressListener {

    static String progressfolder = DimensionManager.getCurrentSaveRootDirectory().getAbsolutePath() + "/progress/";
    static String current_player_json_path = "";
    static JSONObject current_player_json;

    static int ticks = 0;
    static int playerticks = 0;

    static ArrayList<String> hostiles_arr = new ArrayList<String>(){
        {
			String prefix = "";
            add(prefix + "blaze");
            add(prefix + "cave spider");
            add(prefix + "creeper");
            add(prefix + "elder guardian");
            add(prefix + "ender dragon");
            add(prefix + "enderman");
            add(prefix + "endermite");
            add(prefix + "evoker");
            add(prefix + "ghast");
            add(prefix + "guardian");
            add(prefix + "husk");
            add(prefix + "magma cube");
            add(prefix + "polar bear");
            add(prefix + "shulker");
            add(prefix + "silverfish");
            add(prefix + "skeleton");
            add(prefix + "slime");
            add(prefix + "spider");
            add(prefix + "stray");
            add(prefix + "vex");
            add(prefix + "vindicator");
            add(prefix + "witch");
            add(prefix + "wither");
            add(prefix + "wither skeleton");
            add(prefix + "zombie");
            add(prefix + "zombie villager");
            add(prefix + "zombie pigman");
        }
    };

    static ArrayList<String> foods_arr = new ArrayList<String>(){
        {
            String prefix = "minecraft:";
            add(prefix + "apple");
            add(prefix + "baked_potato");
            add(prefix + "beef");
            add(prefix + "beetroot");
            add(prefix + "beetroot_soup");
            add(prefix + "bread");
            add(prefix + "carrot");
            add(prefix + "chicken");
            add(prefix + "chorus_fruit");
            add(prefix + "cooked_beef");
            add(prefix + "cooked_chicken");
            add(prefix + "cooked_fish" + ", id: 0");
            add(prefix + "cooked_fish" + ", id: 1");
            add(prefix + "cooked_mutton");
            add(prefix + "cooked_porkchop");
            add(prefix + "cooked_rabbit");
            add(prefix + "cookie");
            add(prefix + "golden_apple" + ", id: 0");
            add(prefix + "golden_apple" + ", id: 1");
            add(prefix + "golden_carrot");
            add(prefix + "fish" + ", id: 0");
            add(prefix + "fish" + ", id: 1");
            add(prefix + "fish" + ", id: 2");
            add(prefix + "fish" + ", id: 3");
            add(prefix + "melon");
            add(prefix + "mushroom_stew");
            add(prefix + "mutton");
            add(prefix + "poisonous_potato");
            add(prefix + "porkchop");
            add(prefix + "potato");
            add(prefix + "pumpkin_pie");
            add(prefix + "rabbit");
            add(prefix + "rabbit_stew");
            add(prefix + "rotten_flesh");
            add(prefix + "spider_eye");
            add("soestreithesis:" + "suspicious_stew");
        }
    };

    static ArrayList<String> biomes_arr = new ArrayList<String>(){
        {
            String prefix = "minecraft:";
            add(prefix + "birch_forest_hills");
            add(prefix + "river");
            add(prefix + "swampland");
            add(prefix + "desert");
            add(prefix + "forest_hills");
            add(prefix + "redwood_taiga_hills");
            add(prefix + "taiga_cold");
            add(prefix + "mesa");
            add(prefix + "forest");
            add(prefix + "stone_beach");
            add(prefix + "ice_flats");
            add(prefix + "taiga_hills");
            add(prefix + "ice_mountains");
            add(prefix + "mesa_rock");
            add(prefix + "savanna");
            add(prefix + "plains");
            add(prefix + "frozen_river");
            add(prefix + "redwood_taiga");
            add(prefix + "cold_beach");
            add(prefix + "deep_ocean");
            add(prefix + "jungle_hills");
            add(prefix + "jungle_edge");
            add(prefix + "ocean");
            add(prefix + "mushroom_island_shore");
            add(prefix + "extreme_hills");
            add(prefix + "desert_hills");
            add(prefix + "jungle");
            add(prefix + "beaches");
            add(prefix + "savanna_rock");
            add(prefix + "taiga_cold_hills");
            add(prefix + "mesa_clear_rock");
            add(prefix + "roofed_forest");
            add(prefix + "taiga");
            add(prefix + "birch_forest");
            add(prefix + "mushroom_island");
            add(prefix + "extreme_hills_with_trees");
        }
    };

    static ArrayList<String> animals_arr = new ArrayList<String>(){
        {
            String prefix = "";
            add(prefix + "cat");
            add(prefix + "cow");
            add(prefix + "chicken");
            add(prefix + "horse");
            add(prefix + "llama");
            add(prefix + "mooshroom");
            add(prefix + "pig");
            add(prefix + "rabbit");
            add(prefix + "sheep");
            add(prefix + "wolf");
        }
    };

        @SubscribeEvent
        public static void onWorldTick(TickEvent.WorldTickEvent event) {
            if(Minecraft.getMinecraft().world != null){
                if(ticks <= 50){
                    ticks++;
                }
                if(ticks == 50){
                    if(current_player_json == null){
                        createJSON();
                        Minecraft.getMinecraft().player.sendMessage(new TextComponentString("Thank you for testing my thesis! If this is the first time loading this world, press F3+T now, in order for the mod to be fully loaded."));
                    }
                }
            }
        }

        @SubscribeEvent
        public static void onWorldLoad(WorldEvent.Load event){
            ticks = 0;
            playerticks = 0;
            try{
                AdvancementInit.disableVanillaAdvancements();
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }

        @SubscribeEvent
        public static void onWorldUnload(WorldEvent.Unload event){
            ticks = 0;
            playerticks = 0;
            current_player_json = null;
        }

        @SubscribeEvent
        public static void onMonsterKilled(LivingDeathEvent event){
            if (event.getSource().getTrueSource() instanceof EntityPlayer) {
                if (event.getEntity() instanceof EntityCreature) {
                    removeFromJsonObject("monsters_remaining", event.getEntity().getName().toLowerCase());
                    System.out.println(event.getEntity().getName());
                }
            }
        }

        @SubscribeEvent
        public static void onFoodEaten(LivingEntityUseItemEvent.Finish event){
            if (event.getEntity() instanceof EntityPlayer) {
                if (event.getItem().getItem() instanceof ItemFood) {
                    switch (event.getItem().getMetadata()) {
                        case 0:
                            removeFromJsonObject("foods_remaining", event.getItem().getItem().getRegistryName().toString() + ", id: 0");
                            break;
                        case 1:
                            removeFromJsonObject("foods_remaining", event.getItem().getItem().getRegistryName().toString() + ", id: 1");
                            break;
                        case 2:
                            removeFromJsonObject("foods_remaining", event.getItem().getItem().getRegistryName().toString() + ", id: 2");
                            break;
                        case 3:
                            removeFromJsonObject("foods_remaining", event.getItem().getItem().getRegistryName().toString() + ", id: 3");
                            break;
                    }
                    removeFromJsonObject("foods_remaining", event.getItem().getItem().getRegistryName().toString());
                }
            }
        }

        @SubscribeEvent
        public static void onAnimalsBred(BabyEntitySpawnEvent event){
            if (event.getChild() instanceof EntityAnimal) {
                removeFromJsonObject("animals_remaining", event.getChild().getName().toLowerCase());
                System.out.println(event.getChild().getName().toLowerCase());
            }
        }

        @SubscribeEvent
        public static void onBiomeDiscovered(TickEvent.PlayerTickEvent event) {
            EntityPlayer player = event.player;
            if(player instanceof EntityPlayerMP){
                    BlockPos pos = new BlockPos(player.getPositionVector());
                    World world = player.getEntityWorld();
                    if (world.isAreaLoaded(pos,10) && current_player_json != null) {
                        removeFromJsonObject("biomes_remaining", world.getBiome(pos).getRegistryName().toString());
                    }
            }
        }

    public static void createJSON() {

        if(!new File(progressfolder).exists()){
            new File(progressfolder).mkdirs();
        }

        File f = new File((progressfolder + "/progress/" + Minecraft.getMinecraft().player.getUniqueID() + "_progress.json"));

        if (f.exists()) {
            try {
                JSONParser p = new JSONParser();
                current_player_json = (JSONObject) p.parse(new FileReader(f));
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
        else {

            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            JSONObject base = new JSONObject();

            JSONArray monsters = new JSONArray();
            addStringListToJSONArray(hostiles_arr, monsters);
            JSONArray foods = new JSONArray();
            addStringListToJSONArray(foods_arr, foods);
            JSONArray biomes = new JSONArray();
            addStringListToJSONArray(biomes_arr, biomes);
            JSONArray animals = new JSONArray();
            addStringListToJSONArray(animals_arr, animals);

            base.put("monsters_remaining", monsters);
            base.put("foods_remaining", foods);
            base.put("animals_remaining", animals);
            base.put("biomes_remaining", biomes);

            current_player_json = base;
            writeJSON(current_player_json);
        }
    }

        public static void removeFromJsonObject (String key, String value){
            JSONArray edited = (JSONArray) current_player_json.get(key);
            edited.remove(value);
            current_player_json.remove(key);
            current_player_json.put(key, edited);
            writeJSON(current_player_json);
        }

        public static void addStringListToJSONArray (ArrayList < String > input, JSONArray output){
            for (String x : input) {
                output.add(x);
            }
        }

        public static void writeJSON (JSONObject json){
            current_player_json_path = (progressfolder + Minecraft.getMinecraft().player.getUniqueID() + "_progress.json");
            try (PrintWriter pw = new PrintWriter(new FileWriter(current_player_json_path))) {
                pw.write(json.toJSONString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
