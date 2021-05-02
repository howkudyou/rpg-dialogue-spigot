package howkudyou.rpgdialogue.Utils;

import java.util.HashMap;
import java.util.UUID;

public class RPGPlayer {

    UUID mcuuid;

    HashMap<String, UUID> dialogues = new HashMap<>();

    public RPGPlayer(UUID uuid){
        this.mcuuid = uuid;
    }

    public void addDialogue(String npcName, UUID dialogueID){
        this.dialogues.put(npcName, dialogueID);
    }

    public UUID getUUID(){
        return this.mcuuid;
    }

    public UUID getDialogue(String name){
        if (dialogues.containsKey(name))
            return dialogues.get(name);
        return null;
    }

    public HashMap<String, UUID> getDialogues(){
        return this.dialogues;
    }

}
