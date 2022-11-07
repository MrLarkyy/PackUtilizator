package xyz.larkyy.packutilizator.heads;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import xyz.larkyy.packutilizator.Config;
import xyz.larkyy.packutilizator.PackUtilizator;

import java.util.HashMap;
import java.util.Map;

public class HeadVariations {

    private final Map<String,HeadVariation> variations = new HashMap<>();
    private final Config config = new Config(PackUtilizator.getInstance(),"config.yml");


    public HeadVariation getVariation(String id) {
        return variations.get(id);
    }

    public void cachePlayer(Player player) {
        variations.values().forEach(v -> {
            v.translate(player.getUniqueId());
        });
    }

    public void removeCachedPlayer(Player player) {
        variations.values().forEach(v -> {
            v.removeCachedPlayer(player);
        });
    }

    public void load() {
        config.load();

        getCfg().getConfigurationSection("skulls.variations").getKeys(false).forEach(str -> {
            String path = "skulls.variations."+str;
            var variation = new HeadVariation(
                    str,
                    getCfg().getString(path+".character1"),
                    getCfg().getString(path+".character2"),
                    getCfg().getString(path+".character3"),
                    getCfg().getString(path+".character4"),
                    getCfg().getString(path+".character5"),
                    getCfg().getString(path+".character6"),
                    getCfg().getString(path+".character7"),
                    getCfg().getString(path+".character8"),
                    getCfg().getString("skulls.negative-character"),
                    getCfg().getInt(path+".length")
            );
            variations.put(variation.getId(),variation);
        });
    }

    public FileConfiguration getCfg() {
        return config.getConfiguration();
    }

}
