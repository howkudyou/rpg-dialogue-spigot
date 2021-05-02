package howkudyou.rpgdialogue.Utils;

import howkudyou.rpgdialogue.Dialogue.Actions.*;
import howkudyou.rpgdialogue.Dialogue.AnswerLine;
import howkudyou.rpgdialogue.Dialogue.Dialogue;
import howkudyou.rpgdialogue.Dialogue.DialogueLine;
import howkudyou.rpgdialogue.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static howkudyou.rpgdialogue.Utils.Functions.base64StringToItemStack;

public class Reader {

    public static HashMap<String, HashMap<UUID, Dialogue>> readDialogue(String path, Main plugin) throws NullPointerException {
        File folder = new File(path);
        if(folder.isDirectory()){
            HashMap<String, HashMap<UUID, Dialogue>> ret = new HashMap<>();
            for(File f : folder.listFiles()){
                YamlConfiguration yaml = YamlConfiguration.loadConfiguration(f);
                List<DialogueLine> lines = new ArrayList<>();
                for(String lineKey : yaml.getConfigurationSection("lines").getKeys(false)){
                    List<AnswerLine> answers = new ArrayList<>();
                    for(Object o : (List)yaml.get("lines." + lineKey + ".answers")){
                        List<DialogueAction> actions = new ArrayList<>();
                        for(Object o1 : (List)((LinkedHashMap)o).get("actions")){
                            if(((String)o1).startsWith("jumpTo")) {
                                actions.add(new ActionGotoLine(plugin, yaml.getString("name"), UUID.fromString(yaml.getString("quest_uuid")), Integer.parseInt(((String)o1).replace("jumpTo ", ""))));
                            }else if((((String)o1).startsWith("receiveItem"))){
                                actions.add(new ActionGiveItem(plugin.custom_items.get(((String)o1).replace("receiveItem ", ""))));
                            }else if((((String)o1).startsWith("endDialogue"))){
                                if(((String)o1).equalsIgnoreCase("endDialogue")){
                                    actions.add(new ActionEndDialogue(plugin, new ArrayList<>(), yaml.getString("name")));
                                }else{
                                    String[] splt = ((String)o1).replace("endDialogue ", "").replace("endDialogue", "").split(";");
                                    actions.add(new ActionEndDialogue(plugin, new ArrayList<String>(){{
                                        this.addAll(Arrays.asList(splt));
                                    }}, yaml.getString("name")));
                                }
                            }else if((((String)o1).startsWith("nextDialogue"))){
                                String[] split = ((String)o1).split(" ");
                                actions.add(new ActionNextDialogue(plugin, split[1], UUID.fromString(split[2])));
                            }else if((((String)o1).startsWith("execCommand"))){
                                actions.add(new ActionExecCommand(((String)o1).replace("execCommand", "")));
                            }
                        }
                        answers.add(new AnswerLine((String)((LinkedHashMap)o).get("text"), actions));
                    }
                    lines.add(new DialogueLine(plugin, Integer.parseInt(lineKey), yaml.getStringList("lines." + lineKey + ".text"), answers));
                }
                Dialogue dialogue = new Dialogue(lines, UUID.fromString(yaml.getString("quest_uuid")), yaml.getInt("dialogue_id"), yaml.getString("name"));
                if(ret.containsKey(yaml.getString("name"))){
                    ret.get(yaml.getString("name")).put(UUID.fromString(yaml.getString("quest_uuid")), dialogue);
                }else{
                    ret.put(yaml.getString("name"), new HashMap<UUID, Dialogue>(){{
                        put(UUID.fromString(yaml.getString("quest_uuid")), dialogue);
                    }});
                }
            }
            return ret;
        }
        return null;
    }

    public static Map<String, ItemStack> readCustomItems(String path) throws IOException, ClassNotFoundException {
        Map<String, ItemStack> ret = new HashMap<>();
        File file = new File(path);
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        for(String key : yaml.getKeys(false)){
            ret.put(key, base64StringToItemStack(yaml.getString(key)));
        }
        return ret;
    }

    public static List<RPGPlayer> readPlayers(String path){
        File file = new File(path);
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        for(String key : yaml.getKeys(false)){
            RPGPlayer player = new RPGPlayer(UUID.fromString(key));
            System.out.println(yaml.get(key));
        }
        return new ArrayList<>();
    }

}
