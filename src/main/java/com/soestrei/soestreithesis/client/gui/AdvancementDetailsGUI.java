package com.soestrei.soestreithesis.client.gui;

import com.soestrei.soestreithesis.SoestreiThesis;
import com.soestrei.soestreithesis.client.listeners.AdvancementProgressListener;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import scala.util.parsing.json.JSON;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;

    /*
        This class is the 'Advancement Details' window. It contains five buttons, the first four displaying the amount
        you have left for each of the four contained checklists. When clicked, each will take you to the 'Advancement Checklist'
        window, showing their respective checklist. The last button returns you to the previous window.
    */

@SideOnly(Side.CLIENT)
public class AdvancementDetailsGUI extends GuiScreen {

    public AdvancementDetailsGUI(){

    }

    /*ResourceLocation bg = new ResourceLocation(SoestreiThesis.MODID,"textures/gui/statsmenu.png");
    int guiWidth = 256;
    int guiHeight = 140;*/

    JSONArray monsters, foods, animals, biomes;

    static String progressfolder = DimensionManager.getCurrentSaveRootDirectory() + "/progress/";
    static JSONObject current_player_json = new JSONObject();

    final int BUTTON1 = 1;
    final int BUTTON2 = 2;
    final int BUTTON3 = 3;
    final int BUTTON4 = 4;
    final int BUTTON5 = 5;


    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        drawDefaultBackground();
        /*Minecraft.getMinecraft().renderEngine.bindTexture(bg);
        drawDefaultBackground();
        int xSize = (int)(width * .2);
        int ySize = (int)(height * .2);
        drawTexturedModalRect(xSize,ySize, 0,0,guiWidth,guiHeight);*/
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void initGui() {
        super.initGui();
        int yPos = (int)(height * .8);

        int xHalf = (int)(width * .5);
        int yHalf = (int)(height * .5);

        File f = new File((progressfolder + "/" + Minecraft.getMinecraft().player.getUniqueID()));

        if(f.exists()){
            try{
                JSONParser p = new JSONParser();
                current_player_json = (JSONObject)p.parse(new FileReader(f));
            }
            catch (IOException | ParseException e){
                e.printStackTrace();
            }
        }

        monsters = (JSONArray)current_player_json.get("monsters_remaining");
        foods = (JSONArray)current_player_json.get("foods_remaining");
        animals = (JSONArray)current_player_json.get("animals_remaining");
        biomes = (JSONArray)current_player_json.get("biomes_remaining");

        buttonList.add(new GuiButton(BUTTON1, xHalf - 50, yPos, 100, 20, "Advancement Menu"));
        buttonList.add(new GuiButton(BUTTON2, xHalf - 75, yHalf - 40, 150, 20, monsters.size() + " monsters left to kill!"));
        buttonList.add(new GuiButton(BUTTON3, xHalf - 75, yHalf - 20, 150, 20, foods.size() + " foods left to eat!"));
        buttonList.add(new GuiButton(BUTTON4, xHalf - 75, yHalf, 150, 20, animals.size() + " animals left to breed!"));
        buttonList.add(new GuiButton(BUTTON5, xHalf - 75, yHalf + 20, 150, 20, biomes.size() + " biomes left to find!"));

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch(button.id){
            case BUTTON1:
                ImprovedGuiScreenAdvancements iguisa = new ImprovedGuiScreenAdvancements(Minecraft.getMinecraft().player.connection.getAdvancementManager());
                Minecraft.getMinecraft().displayGuiScreen(iguisa);
                break;
            case BUTTON2:
                AdvancementChecklistGUI checklistGUIMonsters = new AdvancementChecklistGUI(monsters);
                Minecraft.getMinecraft().displayGuiScreen(checklistGUIMonsters);
                break;
            case BUTTON3:
                AdvancementChecklistGUI checklistGUIFoods = new AdvancementChecklistGUI(foods);
                Minecraft.getMinecraft().displayGuiScreen(checklistGUIFoods);
                break;
            case BUTTON4:
                AdvancementChecklistGUI checklistGUIAnimals = new AdvancementChecklistGUI(animals);
                Minecraft.getMinecraft().displayGuiScreen(checklistGUIAnimals);
                break;
            case BUTTON5:
                AdvancementChecklistGUI checklistGUIBiomes = new AdvancementChecklistGUI(biomes);
                Minecraft.getMinecraft().displayGuiScreen(checklistGUIBiomes);
                break;
        }
        super.actionPerformed(button);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return true;
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
    }

}
