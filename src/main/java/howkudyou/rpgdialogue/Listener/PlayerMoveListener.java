package howkudyou.rpgdialogue.Listener;

import howkudyou.rpgdialogue.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private Main plugin;

    public PlayerMoveListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        if(plugin.inDialogue.contains(e.getPlayer())){
            e.setCancelled(true);
        }
    }
}
