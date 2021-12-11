package me.gabytm.minecraft.totemanimation.utils;

import net.md_5.bungee.api.ChatColor;
import org.apache.commons.lang.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Strings {

    private static final Pattern PATTERN = Pattern.compile(
            "<(#[a-f0-9]{6}|aqua|black|blue|dark_(aqua|blue|gray|green|purple|red)|gray|gold|green|light_purple|red|white|yellow)>",
            Pattern.CASE_INSENSITIVE
    );

    private Strings() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    public static String color(@NotNull String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }

        if (!VersionHelper.HAS_HEX_SUPPORT) {
            return ChatColor.translateAlternateColorCodes('&', text);
        }

        final Matcher matcher = PATTERN.matcher(text);

        while (matcher.find()) {
            try {
                final ChatColor chatColor = ChatColor.of(matcher.group(1));

                if (chatColor != null) {
                    text = StringUtils.replace(text, matcher.group(), chatColor.toString());
                }
            } catch (IllegalArgumentException ignored) { }
        }

        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
