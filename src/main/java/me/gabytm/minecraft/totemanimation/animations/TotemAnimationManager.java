package me.gabytm.minecraft.totemanimation.animations;

import me.gabytm.minecraft.totemanimation.TotemAnimationPlugin;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TotemAnimationManager {

    private final Map<String, TotemAnimation> animations = new HashMap<>();
    private final List<String> animationsIds = new ArrayList<>();

    private final TotemAnimationPlugin plugin;

    public TotemAnimationManager(TotemAnimationPlugin plugin) {
        this.plugin = plugin;
        load();
    }

    public void load() {
        animations.clear();
        animationsIds.clear();

        final ConfigurationSection totemsSection = plugin.getConfig().getConfigurationSection("totems");

        if (totemsSection == null) {
            plugin.getLogger().warning("Could not find the 'totems' section.");
            return;
        }

        for (final String key : totemsSection.getKeys(false)) {
            final ConfigurationSection section = totemsSection.getConfigurationSection(key);

            if (section != null) {
                animations.put(key.toLowerCase(), TotemAnimation.from(section));
            }
        }

        animationsIds.addAll(animations.keySet());
        animationsIds.sort(String.CASE_INSENSITIVE_ORDER);
    }

    @Nullable
    public TotemAnimation getAnimation(@NotNull final String id) {
        return animations.get(id.toLowerCase());
    }

    @NotNull
    public List<String> getAnimationsIds() {
        return animationsIds;
    }

}
