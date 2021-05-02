package howkudyou.rpgdialogue.Dialogue;

import howkudyou.rpgdialogue.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DialogueLine {

    private List<String> lines;
    private Map<Integer, AnswerLine> answers;
    private Main plugin;
    private int id;

    public DialogueLine(Main plugin, int id, List<String> lines, List<AnswerLine> answers){
        this.lines = lines;
        this.plugin = plugin;
        this.answers = new HashMap<Integer, AnswerLine>(){{
            for(int i = 0; i < answers.size(); i++){
                answers.get(i).setId(i);
                put(i, answers.get(i));
            }
        }};
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void send(Player player, UUID quest, String npc_name){
        new BukkitRunnable() {
            int counter = 0;
            @Override
            public void run() {
                player.sendMessage(ChatColor.YELLOW + npc_name + ": "+ lines.get(counter));
                counter++;
                if(counter == lines.size())
                    this.cancel();
            }
        }.runTaskTimer(plugin, 20, 40);
        new BukkitRunnable() {
            @Override
            public void run() {
                for(AnswerLine answerLine : answers.values()){
                    answerLine.send(player, quest, id, npc_name);
                }
            }
        }.runTaskLater(plugin, 30+(40*lines.size()));
    }

    public AnswerLine getAnswer(int id){
        return answers.get(id);
    }

}
