package me.gabytm.minecraft.totemanimation;

import me.gabytm.minecraft.totemanimation.animations.TotemAnimationManager;
import me.gabytm.minecraft.totemanimation.commands.CommandManager;
import me.gabytm.minecraft.totemanimation.utils.VersionHelper;
import org.bukkit.plugin.java.JavaPlugin;

public class TotemAnimationPlugin extends JavaPlugin {

    private TotemAnimationManager totemAnimationManager;

    @Override
    public void onEnable() {
        if (!VersionHelper.IS_COMPATIBLE) {
            getLogger().severe(getDescription().getName() + " is compatible only with 1.11+.");
            setEnabled(false);
            return;
        }

        saveDefaultConfig();

        this.totemAnimationManager = new TotemAnimationManager(this);
        new CommandManager(this);
    }

    public TotemAnimationManager getTotemAnimationManager() {
        return totemAnimationManager;
    }

}
