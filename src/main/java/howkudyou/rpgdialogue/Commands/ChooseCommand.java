package howkudyou.rpgdialogue.Commands;

import howkudyou.rpgdialogue.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ChooseCommand implements CommandExecutor {

    private Main plugin;

    public ChooseCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if(commandSender instanceof Player){
            if(args.length == 1){
                String[] splt = args[0].split(";");
                UUID quest = UUID.fromString(splt[0]);
                int dialogue_id = Integer.parseInt(splt[1]);
                int answer_id = Integer.parseInt(splt[2]);
                String npc_name = splt[3];
                plugin.dialogues.get(npc_name).get(quest).getLine(dialogue_id).getAnswer(answer_id).exec((Player) commandSender);
            }
        }

        return true;
    }
}
