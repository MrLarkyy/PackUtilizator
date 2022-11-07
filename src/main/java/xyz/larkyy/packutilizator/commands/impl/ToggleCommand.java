package xyz.larkyy.packutilizator.commands.impl;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import xyz.larkyy.packutilizator.PackUtilizator;
import xyz.larkyy.packutilizator.commands.ICommand;

public class ToggleCommand implements ICommand {

    public static final NamespacedKey NAMESPACED_KEY = new NamespacedKey(PackUtilizator.getInstance(),"Heads-Disabled");

    @Override
    public void run(CommandSender sender, String[] args) {
        if (!(sender instanceof Player p)) {
            return;
        }

        if (!p.hasPermission("packutilizator.commands.toggleheads")) {
            return;
        }

        var container = p.getPersistentDataContainer();
        if (container.has(NAMESPACED_KEY, PersistentDataType.STRING)) {
            container.remove(NAMESPACED_KEY);
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',getCfg().getString("skulls.messages.heads-enabled")));
        } else {
            container.set(NAMESPACED_KEY,PersistentDataType.STRING,"disabled");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&',getCfg().getString("skulls.messages.heads-disabled")));
        }
    }

    private FileConfiguration getCfg() {
        return PackUtilizator.getInstance().getHeadVariations().getCfg();
    }
}
