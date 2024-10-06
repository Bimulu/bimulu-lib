package dk.bimulu.library.bimululib.utils.text;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.InventoryHolder;

public class MenuListener implements Listener {

    public MenuListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent event){

        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof Menu) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }

            Menu menu = (Menu) holder;

            menu.onClick(event);
        }
    }

    @EventHandler
    public void onMenuDrag(InventoryDragEvent event){

        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof Menu) {
            event.setCancelled(true);
            if (event.getCursor() == null) {
                return;
            }

            Menu menu = (Menu) holder;

            menu.onDrag(event);
        }
    }

    @EventHandler
    public void onMenuOpen(InventoryOpenEvent event){

        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;

            menu.onOpen(event);
        }
    }

    @EventHandler
    public void onMenuClose(InventoryCloseEvent event){

        InventoryHolder holder = event.getInventory().getHolder();

        if (holder instanceof Menu) {
            Menu menu = (Menu) holder;

            menu.onClose(event);
        }
    }

}
