package dk.bimulu.library.bimululib.utils.text;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuUtility {
    private Player player;
    //store the player that will be killed so we can access him in the next menu
    private Player selectedPlayer;

    public MenuUtility(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getSelectedPlayer() {
        return selectedPlayer;
    }

    public void selectPlayer(Player selectedPlayer) {
        this.selectedPlayer = selectedPlayer;
    }


    public static MenuUtility get(Player player) {
        MenuUtility menuUtility;

        HashMap<Player, MenuUtility> map = Main.getMenuUtilityMap();

        if (map.containsKey(player)) return map.get(player);

        menuUtility = new MenuUtility(player);
        map.put(player, menuUtility);

        return menuUtility;
    }
}
