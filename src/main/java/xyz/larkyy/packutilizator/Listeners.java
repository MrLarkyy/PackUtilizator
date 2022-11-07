package xyz.larkyy.packutilizator;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        PackUtilizator.getInstance().getHeadVariations().cachePlayer(e.getPlayer());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        PackUtilizator.getInstance().getHeadVariations().removeCachedPlayer(e.getPlayer());
    }

}
