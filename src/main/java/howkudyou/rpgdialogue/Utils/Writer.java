package howkudyou.rpgdialogue.Utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Writer {

    public static void writePlayers(String path, List<RPGPlayer> players){
        File file = new File(path);
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
        for(RPGPlayer p : players){
            for(Map.Entry<String, UUID> entry : p.getDialogues().entrySet()){
                yaml.set(p.getUUID().toString() + "." + entry.getKey(), entry.getValue().toString());
            }
        }
        try {
            yaml.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
