package  com.soestrei.soestreithesis.client.listeners;


import com.soestrei.soestreithesis.client.init.ArmorInit;
import com.soestrei.soestreithesis.client.init.ItemInit;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

    /*
    This class is responsible for registering the models/textures (stored in models/item and textures, respectively) of the items to the
    Event Bus. Without this, the item data would not exist.
     */

@Mod.EventBusSubscriber
public class ModelRegistryListener {

    @SubscribeEvent
    public static void registerItems(ModelRegistryEvent event) {
        ItemInit.registerItemRenders();
        ArmorInit.registerArmorRenders();
    }



}
