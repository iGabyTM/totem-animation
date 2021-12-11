package me.gabytm.minecraft.totemanimation.animations;

import com.google.common.base.Enums;
import com.google.common.primitives.Ints;
import dev.triumphteam.gui.builder.item.ItemBuilder;
import me.gabytm.minecraft.totemanimation.utils.Packets;
import me.gabytm.minecraft.totemanimation.utils.Strings;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class TotemAnimation {

    private final ItemStack totem;

    TotemAnimation(ItemStack totem) {
        this.totem = totem;
    }

    @NotNull
    public static TotemAnimation from(@NotNull final ConfigurationSection section) {
        final String name = section.getString("name", "");
        final List<Component> lore = section.getStringList("lore").stream()
                .map(it -> Component.text(Strings.color(it)))
                .collect(Collectors.toList());

        final ItemFlag[] flags = section.getStringList("flags").stream()
                .map(it -> Enums.getIfPresent(ItemFlag.class, it.toUpperCase()).orNull())
                .filter(Objects::nonNull)
                .toArray(ItemFlag[]::new);

        final Map<Enchantment, Integer> enchantments = section.getStringList("enchantments").stream()
                .map(it -> {
                    final String[] parts = it.split(";", 2);

                    if (parts.length != 2) {
                        return null;
                    }

                    final Enchantment enchantment = Enchantment.getByName(parts[0].toUpperCase());

                    if (enchantment == null) {
                        return null;
                    }

                    final Integer level = Ints.tryParse(parts[1]);

                    if (level == null || level < 1) {
                        return null;
                    }

                    return new AbstractMap.SimpleEntry<>(enchantment, level);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));

        final boolean unbreakable = section.getBoolean("unbreakable");
        final int customModelData = section.getInt("customModelData");

        final ItemBuilder builder = ItemBuilder.from(Material.TOTEM_OF_UNDYING);

        if (name != null) {
            builder.name(Component.text(Strings.color(name)));
        }

        builder.lore(lore)
                .flags(flags)
                .unbreakable(unbreakable);

        enchantments.forEach((enchantment, level) -> builder.enchant(enchantment, level, true));

        if (customModelData > 0) {
            builder.model(customModelData);
        }

        return new TotemAnimation(builder.build());
    }

    public void send(@NotNull final Player player, final boolean silent) {
        Packets.setItemInOffhand(player, totem.clone());
        Packets.activateTotem(player, silent);
        Packets.setItemInOffhand(player, player.getInventory().getItemInOffHand());
    }

}
