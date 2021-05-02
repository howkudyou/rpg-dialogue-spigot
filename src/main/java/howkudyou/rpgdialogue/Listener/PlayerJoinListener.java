package howkudyou.rpgdialogue.Listener;

import howkudyou.rpgdialogue.Main;
import howkudyou.rpgdialogue.Utils.RPGPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    Main plugin;

    public PlayerJoinListener(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        if(plugin.getPlayerByUUID(e.getPlayer().getUniqueId()) == null){
            plugin.players.add(new RPGPlayer(e.getPlayer().getUniqueId()));
        }
    }

}
