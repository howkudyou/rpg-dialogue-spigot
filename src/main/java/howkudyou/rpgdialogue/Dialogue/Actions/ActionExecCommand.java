package howkudyou.rpgdialogue.Dialogue.Actions;

import org.bukkit.entity.Player;

public class ActionExecCommand extends DialogueAction{

    String command;

    public ActionExecCommand(String command) {
        this.command = command;
    }

    @Override
    public void exec(Player player) {
        player.performCommand(command);
    }
}
