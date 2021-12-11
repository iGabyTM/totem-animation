package me.gabytm.minecraft.totemanimation.utils;

import com.google.common.primitives.Ints;
import org.bukkit.Bukkit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Matt (<a href="https://github.com/ipsk">@ipsk</a>)
 * <p>
 * Class for detecting server version for legacy support :(
 */
public final class VersionHelper {

    private VersionHelper() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

    private static final int VERSION = getCurrentVersion();

    /**
     * The plugin only runs on 1.11+ (when {@link org.bukkit.Material#TOTEM_OF_UNDYING} was added)
     */
    public static final boolean IS_COMPATIBLE = VERSION >= 1110;

    /**
     * If the version has hex support
     */
    public static final boolean HAS_HEX_SUPPORT = VERSION > 1152;

    /**
     * Gets the current server version
     *
     * @return A protocol like number representing the version, for example 1.16.5 - 1165
     */
    @SuppressWarnings("UnstableApiUsage")
    private static int getCurrentVersion() {
        // No need to cache since will only run once
        final Matcher matcher = Pattern.compile("(?<version>\\d+\\.\\d+)(?<patch>\\.\\d+)?").matcher(Bukkit.getBukkitVersion());

        final StringBuilder stringBuilder = new StringBuilder();
        if (matcher.find()) {
            stringBuilder.append(matcher.group("version").replace(".", ""));
            final String patch = matcher.group("patch");
            if (patch == null) stringBuilder.append("0");
            else stringBuilder.append(patch.replace(".", ""));
        }

        final Integer version = Ints.tryParse(stringBuilder.toString());

        // Should never fail
        if (version == null) throw new RuntimeException("Could not retrieve server version!");

        return version;
    }

}
