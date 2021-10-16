package io.slimemc.slimecore.utils;

import io.slimemc.slimecore.compatibility.ServerVersion;
import org.bukkit.ChatColor;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextUtils {
    private static final Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

    public static String formatText(String text) {
        if (text == null || text.equals("")) {
            return "";

        } else {
            Matcher match = pattern.matcher(text);

            while (match.find()) {
                String colour = text.substring(match.start(), match.end());

                if (ServerVersion.isAtLeast(ServerVersion.V1_16)) {
                    text = text.replace("&" + colour, String.valueOf(net.md_5.bungee.api.ChatColor.of(colour)));
                } else {
                    //ToDo: Change to get the nearest color and change it to that.
                    text = text.replace(colour, "");
                }

                match = pattern.matcher(text);
            }

            return ChatColor.translateAlternateColorCodes('&', text);
        }
    }

    public static List<String> formatText(List<String> list) {
        return list.stream().map(TextUtils::formatText).collect(Collectors.toList());
    }

    public static List<String> formatText(String... list) {
        return Arrays.stream(list).map(TextUtils::formatText).collect(Collectors.toList());
    }
}
