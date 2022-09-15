package  com.soestrei.soestreithesis.client.listeners;


import com.soestrei.soestreithesis.client.gui.ImprovedGuiScreenAdvancements;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.advancements.GuiScreenAdvancements;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

        /*
        This class checks to see if the Advancement window is open. If so, it will replace it with my own modified Advancement window.
        */

@Mod.EventBusSubscriber
public class AdvancementGuiListener {

    @SubscribeEvent
    public static void onEvent(GuiOpenEvent event){
        if(event.getGui() instanceof GuiScreenAdvancements){
            event.setGui(new ImprovedGuiScreenAdvancements(Minecraft.getMinecraft().player.connection.getAdvancementManager()));
        }
    }


}
