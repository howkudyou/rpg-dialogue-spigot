package howkudyou.rpgdialogue.Dialogue.Actions;

import howkudyou.rpgdialogue.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class ActionEndDialogue extends DialogueAction {

    private Main plugin;
    private List<String> msgs;
    private String npc_name;

    public ActionEndDialogue(Main plugin, List<String> msgs, String npc_name){
        this.plugin = plugin;
        this.msgs = msgs;
        this.npc_name = npc_name;
    }

    @Override
    public void exec(Player player) {
        plugin.inDialogue.remove(player);
        if(msgs.size() == 1){
            player.sendMessage(ChatColor.YELLOW + npc_name + ": " + msgs.get(0));
        }else if(msgs.size() > 1){
            new BukkitRunnable() {
                int count = 0;
                @Override
                public void run() {
                    player.sendMessage(ChatColor.YELLOW + npc_name + ": " + msgs.get(count));
                    count++;
                    if(count == msgs.size())
                        this.cancel();
                }
            }.runTaskTimer(plugin, 20, 30);
        }
    }

}
