package xyz.larkyy.packutilizator;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.larkyy.packutilizator.heads.HeadVariations;

public final class PackUtilizator extends JavaPlugin {

    private static PackUtilizator instance;
    private HeadVariations headVariations;

    @Override
    public void onEnable() {
        instance = this;
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new PAPIHook().register();
        }
        headVariations = new HeadVariations();
        headVariations.load();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PackUtilizator getInstance() {
        return instance;
    }

    public HeadVariations getHeadVariations() {
        return headVariations;
    }
}
