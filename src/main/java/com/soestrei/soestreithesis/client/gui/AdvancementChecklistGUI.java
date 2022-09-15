package com.soestrei.soestreithesis.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.*;
import org.json.simple.JSONArray;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

    /*
        This class is the 'Advancement Checklist' window. It takes an input of a JSONArray, and outputs a scrollable list
        of the elements in said array. As the player crosses elements off of the checklist, they disappear.
    */

public class AdvancementChecklistGUI extends GuiScreen {

    JSONArray current_json_arr;

    float currentScroll;

    ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
    
    int backX = sr.getScaledWidth();
    int backY = sr.getScaledHeight();

    private final int x = 150, y = 5, w = backX - 150, h = backY - 5;

    public AdvancementChecklistGUI(JSONArray arr){
        current_json_arr = arr;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        drawDefaultBackground();

        if (currentScroll > 0){
            currentScroll = 0.0f;
        }
        Gui.drawRect(x, y, w, h, 0xff212121);
        int textHeight = 0;
        GL11.glPushMatrix();
        GL11.glEnable(GL11.GL_SCISSOR_TEST);
        GL11.glTranslatef(0, currentScroll, 0); // Scroll
        ScaledResolution r = new ScaledResolution(mc);
        int s = r.getScaleFactor();
        int translatedY = r.getScaledHeight() - y - h;
        GL11.glScissor(x * s, translatedY * s + 18, w * s, h * s - 18); // Scissor with scaleFactor

        for (Object m : current_json_arr) {
            drawString(m.toString(), x, y + textHeight + 2, -1);
            textHeight += fontRenderer.FONT_HEIGHT + 2;
        }

        if (currentScroll < -(textHeight / 2.67f)){
            currentScroll = -(textHeight / 2.67f);
        }
        GL11.glDisable(GL11.GL_SCISSOR_TEST);
        GL11.glPopMatrix();
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();

        int i = Mouse.getEventDWheel();

        i = Integer.compare(i, 0);

        currentScroll += i * 5f;
    }


    public void drawString(String str, int x, int y, int color){
        str = str.replace('_', ' ');
        if (str.contains("minecraft.entity.")) {
            str = str.substring(17,18).toUpperCase() + str.substring(18);
        }
        else if(str.contains("minecraft:")){
            if(str.contains("id")){
                if(str.contains("0")){
                    if(str.contains("gold")){
                        str = "Golden apple";
                    }
                    else if (str.contains("cook")){
                        str = "Cooked fish";
                    }
                    else{
                        str = "Fish";
                    }
                }
                if(str.contains("1")){
                    if(str.contains("gold")){
                        str = "Enchanted golden apple";
                    }
                    else if (str.contains("cook")){
                        str = "Cooked salmon";
                    }
                    else{
                        str = "Salmon";
                    }
                }
                if(str.contains("2")){
                    str = "Clownfish";
                }
                if(str.contains("3")){
                    str = "Pufferfish";
                }
            }
            else {
                str = str.substring(10, 11).toUpperCase() + str.substring(11);
            }
        }
        else if(str.contains("sus")){
            str = str.substring(15, 16).toUpperCase() + str.substring(16);
        }
        else{
            str = str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        drawCenteredString(fontRenderer, str, (x+w)/2, y, color);
    }

    @Override
    public void initGui(){
        super.initGui();
    }

}
