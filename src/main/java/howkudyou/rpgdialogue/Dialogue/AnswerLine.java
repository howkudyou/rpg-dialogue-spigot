package howkudyou.rpgdialogue.Dialogue;

import howkudyou.rpgdialogue.Dialogue.Actions.DialogueAction;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class AnswerLine {

    private String text;
    private List<DialogueAction> actions;
    private int id;

    public AnswerLine(String text, List<DialogueAction> actions) {
        this.text = text;
        this.actions = actions;
    }

    public void setId(int id){
        this.id = id;
    }

    public void send(Player player, UUID quest, int dialogue_id, String npc_name){
        TextComponent msg = new TextComponent("§a" + text);
        msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/choose " + quest.toString() + ";" + dialogue_id + ";" + id + ";" + npc_name));
        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder( "Choose!" ).create()));
        player.spigot().sendMessage(msg);
    }

    public void exec(Player player){
        for(DialogueAction action : actions){
            action.exec(player);
        }
    }
}
