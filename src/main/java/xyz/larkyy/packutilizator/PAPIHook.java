package xyz.larkyy.packutilizator;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.clip.placeholderapi.expansion.Relational;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import xyz.larkyy.packutilizator.heads.HeadVariation;

public class PAPIHook extends PlaceholderExpansion {


    @Override
    public String getAuthor() {
        return "Larkyy";
    }

    @Override
    public String getIdentifier() {
        return "packutilizator";
    }

    @Override
    public String getVersion() {
        return "0.0.1";
    }

    @Override
    public boolean canRegister() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (!player.isOnline()) {
            return "";
        }
        //https://mc-heads.net/avatar/MHF_Steve/8.png

        if(params.contains("skull")) {
            String variantId = params.split("_")[1];
            HeadVariation variation = PackUtilizator.getInstance().getHeadVariations().getVariation(variantId);
            if (variation == null) return "";
            final var future = variation.translate(player.getUniqueId());
            return future.getNow(variation.getSteveSkull());
        }

        return null; // Placeholder is unknown by the Expansion
    }
}
