package me.gabytm.minecraft.totemanimation.commands;

import me.gabytm.minecraft.totemanimation.Message;
import me.gabytm.minecraft.totemanimation.TotemAnimationPlugin;
import me.mattstudios.mf.base.CompletionHandler;
import me.mattstudios.mf.base.MessageHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class CommandManager {

    private final TotemAnimationPlugin plugin;

    public CommandManager(TotemAnimationPlugin plugin) {
        this.plugin = plugin;
        final me.mattstudios.mf.base.CommandManager manager = new me.mattstudios.mf.base.CommandManager(plugin, true);

        registerCompletions(manager.getCompletionHandler());
        registerMessages(manager.getMessageHandler());

        manager.register(
                new ListCommand(plugin.getTotemAnimationManager()),
                new ReloadCommand(plugin),
                new SendCommand(plugin.getTotemAnimationManager())
        );
    }

    private void registerCompletions(@NotNull final CompletionHandler handler) {
        handler.register("#animations", input -> plugin.getTotemAnimationManager().getAnimationsIds());

        final List<String> booleans = Arrays.asList("false", "true");
        handler.register("#boolean", input -> booleans);
    }

    private void registerMessages(@NotNull final MessageHandler handler) {
        handler.register("cmd.no.permission", Message.NO_PERMISSION::send);
        handler.register("cmd.no.exists", Message.UNKNOWN_COMMAND::send);
        handler.register("#ta.usage.send", Message.USAGE__SEND::send);
    }

}
