package me.gabytm.minecraft.totemanimation.commands;

import me.gabytm.minecraft.totemanimation.Message;
import me.gabytm.minecraft.totemanimation.animations.TotemAnimation;
import me.gabytm.minecraft.totemanimation.animations.TotemAnimationManager;
import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

@Command("totemanimation")
@Alias("ta")
public class SendCommand extends CommandBase {

    private final TotemAnimationManager animationManager;

    public SendCommand(TotemAnimationManager animationManager) {
        this.animationManager = animationManager;
    }

    @WrongUsage("#ta.usage.send")
    @Permission("totemanimation.admin")
    @Completion({"#players", "#animations", "#boolean"})
    @SubCommand("send")
    public void onCommand(@NotNull final CommandSender sender, @NotNull final Player target,
                          @NotNull final String animationId, final boolean silent
    ) {
        final TotemAnimation animation = animationManager.getAnimation(animationId);

        if (animation == null) {
            Message.UNKNOWN_ANIMATION.send(sender, animationId);
            return;
        }

        animation.send(target, silent);
    }

}
