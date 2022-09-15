package com.soestrei.soestreithesis.client.init;

import net.minecraftforge.common.DimensionManager;

import java.io.*;

    /*
        This class disables all vanilla advancements in order to ensure there are no cloned advancements.
        This is why F3+T is required on world startup - to load the changes from this class.
    */

public class AdvancementInit {

    static File vanilla_data = new File(DimensionManager.getCurrentSaveRootDirectory().getAbsolutePath() + "/data/advancements/minecraft/");

    static String vanilla_impossible =
            "{\n" +
            "\t\"parent\": \"totally not possible\",\n" +
            "\t\"criteria\": {\n" +
            "\t\t\"impossible\": {\n" +
            "\t\t\t\"trigger\": \"minecraft:impossible\"\n" +
            "\t\t}\n" +
            "\t}\n" +
            "}";

    public static String getRootFolder(int i){
        switch(i){
            case 0:
                return "/story";
            case 1:
                return "/adventure";
            case 2:
                return "/husbandry";
            case 3:
                return "/nether";
            case 4:
                return "/end";
        }
        return "/story";
    }

    public static void disableVanillaAdvancements() throws IOException {
        vanilla_data.mkdirs();
        vanilla_data.setWritable(true);
        for(int i = 0; i < 5; i++){
            File disabled_folder = new File(vanilla_data + getRootFolder(i) + "/");
            disabled_folder.setWritable(true);
            disabled_folder.mkdirs();
            File f = new File(disabled_folder + "/root.json");
            f.setWritable(true);
            f.createNewFile();
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(f, false)));
            out.println(vanilla_impossible);
            out.close();
        }
    }
}
