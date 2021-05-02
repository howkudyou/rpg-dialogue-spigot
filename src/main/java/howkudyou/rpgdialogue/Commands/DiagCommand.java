package howkudyou.rpgdialogue.Commands;

import howkudyou.rpgdialogue.Dialogue.Dialogue;
import howkudyou.rpgdialogue.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class DiagCommand implements CommandExecutor {

    Main plugin;

    public DiagCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(!p.hasPermission("dialogue.commands.diag") && !p.isOp()){
                return false;
            }
        }

        Player target = Bukkit.getPlayer(args[0]);
        String questmaster = args[1];
        UUID uid = UUID.fromString(args[2]);

        Dialogue dialog = plugin.dialogues.get(questmaster).get(uid);

        if(dialog != null){
            plugin.getPlayerByUUID(target.getUniqueId()).addDialogue(questmaster, uid);
        }

        return true;
    }
}
