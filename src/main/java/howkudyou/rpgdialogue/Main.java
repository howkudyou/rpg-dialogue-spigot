package howkudyou.rpgdialogue;

import howkudyou.rpgdialogue.Commands.ChooseCommand;
import howkudyou.rpgdialogue.Commands.DiagCommand;
import howkudyou.rpgdialogue.Dialogue.Dialogue;
import howkudyou.rpgdialogue.Listener.PlayerJoinListener;
import howkudyou.rpgdialogue.Listener.PlayerMoveListener;
import howkudyou.rpgdialogue.Listener.PlayerRightClickOnNpcListener;
import howkudyou.rpgdialogue.Utils.RPGPlayer;
import howkudyou.rpgdialogue.Utils.Reader;
import howkudyou.rpgdialogue.Utils.Writer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.*;

public final class Main extends JavaPlugin {


    public HashMap<String, HashMap<UUID, Dialogue>> dialogues;
    public List<Player> inDialogue;
    public Map<String, ItemStack> custom_items;
    public List<RPGPlayer> players;

    @Override
    public void onEnable() {
        inDialogue = new ArrayList<>();
        players = Reader.readPlayers("plugins/rpg-dialogue/playerdata.yml");

        try {
            custom_items = Reader.readCustomItems("plugins/rpg-dialogue/custom_items.yml");
            custom_items.put("none", null);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error Reading custom_items.yml!");
        }

        dialogues = Reader.readDialogue("plugins/rpg-dialogue/dialogue", this);

        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerRightClickOnNpcListener(this), this);
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(this), this);

        getCommand("choose").setExecutor(new ChooseCommand(this));
        getCommand("diag").setExecutor(new DiagCommand(this));

        Writer.writePlayers("plugins/rpg-dialogue/playerdata.yml", players);
        getLogger().info("Plugin loaded!");
    }

    @Override
    public void onDisable() {
        Writer.writePlayers("plugins/rpg-dialogue/playerdata.yml", players);
        getLogger().info("Plugin unloaded!");
    }

    public RPGPlayer getPlayerByUUID(UUID uuid){
        for (RPGPlayer p : players)
            if (p.getUUID().equals(uuid))
                return p;
        return null;
    }
}
