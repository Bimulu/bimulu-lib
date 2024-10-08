package dk.bimulu.library.bimululib.utils.text;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Main extends BimuluPlugin {

    private static Main instance;

    private static final HashMap<Player, MenuUtility> menuUtilityMap = new HashMap<>();

    @Override
    public void run() {
        instance = this;

        Bukkit.getConsoleSender().sendMessage(ColorTranslator.string(" "));
        Bukkit.getConsoleSender().sendMessage(ColorTranslator.string("&a--------------------------------------------&r"));
        Bukkit.getConsoleSender().sendMessage(ColorTranslator.string(" &2bimulu-lib &fer lavet af &eBimulu &fog bliver"));
        Bukkit.getConsoleSender().sendMessage(ColorTranslator.string(" &fprimært brugt til plugins lavet til &cBlocklandia"));
        Bukkit.getConsoleSender().sendMessage(ColorTranslator.string("&a--------------------------------------------&r"));
        Bukkit.getConsoleSender().sendMessage(ColorTranslator.string(" "));
    }

    @Override
    public void stop() {

    }

    @Override
    public void loadListeners() {
        new MenuListener();
    }

    @Override
    public void loadCommands() {

    }

    @Override
    public void loadHooks() {

    }

    public static Main getInstance() {
        return instance;
    }

    public static HashMap<Player, MenuUtility> getMenuUtilityMap() {
        return menuUtilityMap;
    }
}
