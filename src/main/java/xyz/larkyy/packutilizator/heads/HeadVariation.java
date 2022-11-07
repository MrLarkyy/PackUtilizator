package xyz.larkyy.packutilizator.heads;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class HeadVariation {
    private final Map<UUID,String> cachedSkulls = new HashMap<>();
    private final String steveSkull;

    private final String id;
    private final String character1;
    private final String character2;
    private final String character3;
    private final String character4;
    private final String character5;
    private final String character6;
    private final String character7;
    private final String character8;
    private final String backCharacter;
    private final int length;

    public HeadVariation(String id, String character1, String character2,
            String character3, String character4, String character5,
            String character6, String character7, String character8, String backCharacter, int length) {
        this.id = id;
        this.character1 = character1;
        this.character2 = character2;
        this.character3 = character3;
        this.character4 = character4;
        this.character5 = character5;
        this.character6 = character6;
        this.character7 = character7;
        this.character8 = character8;
        this.backCharacter = backCharacter;
        this.length = length;

        this.steveSkull = translate("MHF_Steve");
    }

    public String getId() {
        return id;
    }

    public void removeCachedPlayer(Player player) {
        cachedSkulls.remove(player.getUniqueId());
    }

    public String getCharacter1() {
        return character1;
    }

    public String getCharacter2() {
        return character2;
    }

    public String getCharacter3() {
        return character3;
    }

    public String getCharacter4() {
        return character4;
    }

    public String getCharacter5() {
        return character5;
    }

    public String getCharacter6() {
        return character6;
    }

    public String getCharacter7() {
        return character7;
    }

    public String getCharacter8() {
        return character8;
    }

    public int getLength() {
        return length;
    }

    public String getBackCharacter() {
        return backCharacter;
    }

    public String translate(String name) {
        try {
            var url = new URL("https://mc-heads.net/avatar/"+name+"/8.png");
            return translateUrl(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public CompletableFuture<String> translate(UUID uuid) {
        var cachedSkull = cachedSkulls.get(uuid);
        if (cachedSkull != null) {
            return CompletableFuture.completedFuture(cachedSkull);
        }
        return CompletableFuture.supplyAsync(() -> {
            String skull;
            try {
                var url = new URL("https://mc-heads.net/avatar/"+uuid+"/8.png");
                skull = translateUrl(url);
                cachedSkulls.put(uuid,skull);
                return skull;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public String getSteveSkull() {
        return steveSkull;
    }

    private final String translateUrl(URL url) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(url);
            StringBuilder builder = new StringBuilder();
            for (int x = 0; x < 8; x++) {
                if (x > 0) {
                    builder.append(backCharacter);
                }
                for (int y = 0; y < 8; y++) {
                    String character = character1;
                    if (y == 1) character = character2;
                    else if (y == 2) character = character3;
                    else if (y == 3) character = character4;
                    else if (y == 4) character = character5;
                    else if (y == 5) character = character6;
                    else if (y == 6) character = character7;
                    else if (y == 7) character = character8;
                    builder.append(ChatColor.of(new Color(image.getRGB(x,y)))+character);
                    if (y < 7) {
                        for (int i = 0; i <= length; i++) {
                            builder.append(backCharacter);
                        }
                    }
                }
            }
            return builder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
