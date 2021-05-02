package howkudyou.rpgdialogue.Dialogue.Actions;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ActionGiveItem extends DialogueAction {

    private ItemStack item;

    public ActionGiveItem(ItemStack item){
        this.item = item;
    }

    @Override
    public void exec(Player player) {
        player.getInventory().addItem(item);
        player.sendMessage(ChatColor.GREEN + "Received " + ChatColor.GOLD + Objects.requireNonNull(item.getItemMeta()).getDisplayName() + ChatColor.GREEN + "!");
    }
}
