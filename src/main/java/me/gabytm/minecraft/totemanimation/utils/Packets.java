package me.gabytm.minecraft.totemanimation.utils;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import me.gabytm.minecraft.totemanimation.TotemAnimationPlugin;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;

public final class Packets {

    private static final TotemAnimationPlugin plugin = JavaPlugin.getPlugin(TotemAnimationPlugin.class);

    private Packets() {
        throw new IllegalAccessError();
    }

    private static void send(@NotNull final Player player, @NotNull final PacketContainer packet) {
        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
        } catch (InvocationTargetException e) {
            plugin.getLogger().log(Level.SEVERE, "Could not send packet " + packet.getType() + " to " + player.getName(), e);
        }
    }

    public static void setItemInOffhand(@NotNull final Player player, @NotNull final ItemStack item) {
        final PacketContainer packet = new PacketContainer(PacketType.Play.Server.SET_SLOT);
        packet.getIntegers().write(0, 0); // windowId
        packet.getIntegers().write(1, 45); // slot
        packet.getItemModifier().write(0, item); // slotData

        send(player, packet);
    }

    public static void activateTotem(@NotNull final Player player, final boolean silent) {
        final PacketContainer packet = new PacketContainer(PacketType.Play.Server.ENTITY_STATUS);
        packet.getIntegers().write(0, player.getEntityId()); // entityId
        packet.getBytes().write(0, (byte) 35); // status

        Packets.send(player, packet);

        if (silent) {
            player.stopSound(Sound.ITEM_TOTEM_USE, SoundCategory.PLAYERS);
        }
    }

}
