package howkudyou.rpgdialogue.Dialogue.Actions;

import howkudyou.rpgdialogue.Main;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionNextDialogue extends DialogueAction {

    String questmaster;
    UUID uid;
    Main plugin;

    public ActionNextDialogue(Main plugin, String questmaster, UUID uid) {
        this.questmaster = questmaster;
        this.uid = uid;
        this.plugin = plugin;
    }

    @Override
    public void exec(Player player) {
        plugin.getPlayerByUUID(player.getUniqueId()).addDialogue(this.questmaster, this.uid);
    }

}
