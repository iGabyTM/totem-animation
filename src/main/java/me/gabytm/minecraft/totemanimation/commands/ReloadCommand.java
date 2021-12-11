package me.gabytm.minecraft.totemanimation.commands;

import me.gabytm.minecraft.totemanimation.Message;
import me.gabytm.minecraft.totemanimation.TotemAnimationPlugin;
import me.mattstudios.mf.annotations.Alias;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;

@Command("totemanimation")
@Alias("ta")
public class ReloadCommand extends CommandBase {

    private final TotemAnimationPlugin plugin;

    public ReloadCommand(TotemAnimationPlugin plugin) {
        this.plugin = plugin;
    }

    @Permission("totemanimation.admin")
    @SubCommand("reload")
    public void onCommand(final CommandSender sender) {
        plugin.reloadConfig();
        plugin.getTotemAnimationManager().load();
        Message.PLUGIN_RELOADED.send(sender);
    }

}
