package me.gabytm.minecraft.totemanimation.commands;

import me.gabytm.minecraft.totemanimation.Message;
import me.gabytm.minecraft.totemanimation.animations.TotemAnimationManager;
import me.gabytm.minecraft.totemanimation.utils.Strings;
import me.mattstudios.mf.annotations.Alias;
import me.mattstudios.mf.annotations.Command;
import me.mattstudios.mf.annotations.Permission;
import me.mattstudios.mf.annotations.SubCommand;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;

@Command("totemanimation")
@Alias("ta")
public class ListCommand extends CommandBase {

    private static final String DELIMITER = Strings.color("&f, &9");

    private final TotemAnimationManager manager;

    public ListCommand(TotemAnimationManager manager) {
        this.manager = manager;
    }

    @Permission("totemanimation.admin")
    @SubCommand("list")
    public void onCommand(final CommandSender sender) {
        Message.LIST.send(sender, manager.getAnimationsIds().size(), String.join(DELIMITER, manager.getAnimationsIds()));
    }

}
