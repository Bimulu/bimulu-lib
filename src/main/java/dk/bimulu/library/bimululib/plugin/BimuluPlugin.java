package dk.bimulu.library.bimululib.plugin;

import dk.bimulu.library.bimululib.bungee.BimuluBungee;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BimuluPlugin extends JavaPlugin {

    public abstract void run();
    public abstract void stop();

    public abstract void loadListeners();
    public abstract void loadCommands();
    public abstract void loadHooks();

    @Override
    public void onEnable() {
        run();
        loadListeners();
        loadCommands();

        new BimuluBungee();
    }

    @Override
    public void onDisable() {
        stop();
    }

    public void addListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    public void addHook(Hook hook) {
        hook.register();
    }
}
