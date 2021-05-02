package howkudyou.rpgdialogue.Dialogue;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Dialogue {

    private Map<Integer, DialogueLine> lines;
    private UUID quest;
    private int dialogue_id;
    private String npc_name;

    public Dialogue(List<DialogueLine> lines, UUID quest, int dialogue_id, String npc_name) {
        this.lines = new HashMap<Integer, DialogueLine>(){{
            for(DialogueLine line : lines){
                put(line.getId(), line);
            }
        }};
        this.quest = quest;
        this.dialogue_id = dialogue_id;
        this.npc_name = npc_name;
    }

    public void start(int id, Player player){
        lines.get(id).send(player, quest, npc_name);
    }

    public String getNpc_name(){
        return npc_name;
    }

    public DialogueLine getLine(int id){
        return lines.get(id);
    }

}
