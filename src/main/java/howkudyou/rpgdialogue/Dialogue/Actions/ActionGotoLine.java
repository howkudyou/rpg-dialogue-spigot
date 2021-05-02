package howkudyou.rpgdialogue.Dialogue.Actions;

import howkudyou.rpgdialogue.Main;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ActionGotoLine extends DialogueAction {

    private Main plugin;
    private int num;
    private String npc_name;
    private UUID quest_uuid;

    public ActionGotoLine(Main plugin, String npc_name, UUID quest_uuid, int num){
        this.num = num;
        this.npc_name = npc_name;
        this.plugin = plugin;
        this.quest_uuid = quest_uuid;
    }

    @Override
    public void exec(Player player) {
        plugin.dialogues.get(npc_name).get(quest_uuid).start(num, player);
    }

}
