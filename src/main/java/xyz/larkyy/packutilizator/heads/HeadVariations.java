package xyz.larkyy.packutilizator.heads;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class HeadVariations {

    private final Map<String,HeadVariation> variations = new HashMap<>();

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
        variations.put("scale1",new HeadVariation(
                        "scale1",
                        "\uE002",
                        "\uE003",
                        "\uE004",
                        "\uE005",
                        "\uE006",
                        "\uE007",
                        "\uE008",
                        "\uE009",
                        "七",
                        9
                )
        );
        variations.put("scale2",new HeadVariation(
                        "scale2",
                        "\uE00A",
                        "\uE00B",
                        "\uE00C",
                        "\uE00D",
                        "\uE00E",
                        "\uE00F",
                        "\uE010",
                        "\uE011",
                        "七",
                        4
                )
        );
    }

}
