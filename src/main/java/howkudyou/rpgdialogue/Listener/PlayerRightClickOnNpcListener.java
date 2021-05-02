package howkudyou.rpgdialogue.Listener;

import howkudyou.rpgdialogue.Main;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerRightClickOnNpcListener implements Listener {

    private Main plugin;

    public PlayerRightClickOnNpcListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onRightClick(NPCRightClickEvent e){
        Player p = e.getClicker();
        if(!plugin.inDialogue.contains(p)){
            if(plugin.dialogues.get(e.getNPC().getName()) != null){
                if(plugin.dialogues.get(e.getNPC().getName()).containsKey(plugin.getPlayerByUUID(p.getUniqueId()).getDialogue(e.getNPC().getName()))){
                    plugin.inDialogue.add(p);
                    plugin.dialogues.get(e.getNPC().getName()).get(plugin.getPlayerByUUID(p.getUniqueId()).getDialogue(e.getNPC().getName())).start(0, p);
                }
            }
        }
    }

}
