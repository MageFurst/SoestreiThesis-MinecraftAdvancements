package  com.soestrei.soestreithesis.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraft.client.multiplayer.ClientAdvancementManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;

    /*
        This class is the replacement for the vanilla Advancements screen. It adds one button, which
        opens the 'Advancement Details' window.
    */

@SideOnly(Side.CLIENT)
public class ImprovedGuiScreenAdvancements extends GuiScreenAdvancements {
    public ImprovedGuiScreenAdvancements(ClientAdvancementManager p_i47383_1_) {
        super(p_i47383_1_);
    }

    final int BUTTON1 = 10;
    @Override
    public void initGui(){
        super.initGui();
        int xPos = (int)(width * .5) - 50;
        int yPos = (int)(height * .8);
        buttonList.add(new GuiButton(BUTTON1, xPos, yPos, 100, 20, "More Info"));
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        switch(button.id){
            case BUTTON1:
                AdvancementDetailsGUI agui = new AdvancementDetailsGUI();
                Minecraft.getMinecraft().displayGuiScreen(agui);
                break;
        }
        super.actionPerformed(button);
    }

}
