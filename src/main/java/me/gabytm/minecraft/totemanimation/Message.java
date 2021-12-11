package me.gabytm.minecraft.totemanimation;

import me.gabytm.minecraft.totemanimation.utils.Strings;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public enum Message {

    LIST("&9TotemAnimation &8| &9%d &fanimations loaded:&9 %s&f."),

    NO_PERMISSION("&4TotemAnimation &8| &cNo permission."),

    PLUGIN_RELOADED("&9TotemAnimation &8| &fPlugin reloaded!"),

    UNKNOWN_ANIMATION("&4TotemAnimation &8| &cUnknown animation &n%s&c."),
    UNKNOWN_COMMAND("&4TotemAnimation &8| &cUnknown command."),

    USAGE__SEND("&9TotemAnimation &8| &fUsage: &9/totemanimation send [player] [animation] [silent]&f.");

    private final String message;

    Message(String message) {
        this.message = Strings.color(message);
    }

    public void send(@NotNull final CommandSender receiver, @NotNull final Object... args) {
        if (args.length == 0) {
            receiver.sendMessage(message);
        } else {
            receiver.sendMessage(String.format(message, args));
        }
    }

}
