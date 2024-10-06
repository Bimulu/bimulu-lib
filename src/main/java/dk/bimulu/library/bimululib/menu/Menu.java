package dk.bimulu.library.bimululib.menu;

import dk.bimulu.library.bimululib.Main;
import dk.bimulu.library.bimululib.utils.general.ColorTranslator;
import dk.bimulu.library.bimululib.utils.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class Menu implements InventoryHolder {

    //Protected values that can be accessed in the menus
    protected MenuUtility menuUtility;
    protected Inventory inventory;

    public Menu(MenuUtility menuUtility) {
        this.menuUtility = menuUtility;
    }

    //let each menu decide their name
    public abstract String getMenuName();

    //let each menu decide their slot amount
    public abstract int getRows();

    //let each menu decide how the items in the menu will be handled when clicked
    public abstract void onClick(InventoryClickEvent event);
    public abstract void onDrag(InventoryDragEvent event);
    public abstract void onClose(InventoryCloseEvent event);
    public abstract void onOpen(InventoryOpenEvent event);

    public abstract Sound openSound();

    //let each menu decide what items are to be placed in the inventory menu
    public abstract void setItems();

    //When called, an inventory is created and opened for the player
    public void open() {
        //The owner of the inventory created is the Menu itself,
        // so we are able to reverse engineer the Menu object from the
        // inventoryHolder in the MenuListener class when handling clicks
        inventory = Bukkit.createInventory(this, getRows()*9, getMenuName());

        //grab all the items specified to be used for this menu and add to inventory
        this.setItems();

        //open the inventory for the player
        menuUtility.getPlayer().openInventory(inventory);

        if (openSound() != null) menuUtility.getPlayer().playSound(menuUtility.getPlayer(), openSound(), 1, 1);
    }

    public void setUpdatingItem(int slot, ItemStack itemStack, long interval) {

        AtomicInteger i = new AtomicInteger();

        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            i.getAndIncrement();
            if (inventory != null) {
                inventory.setItem(slot, itemStack);
            } else {
                return;
            }


        },0L, interval);
    }

    public void updateItems() {
        inventory.clear();
        this.setItems();
    }

    public void setUpdatingItems(long interval) {
        Bukkit.getScheduler().runTaskTimer(Main.getInstance(), () -> {
            if (menuUtility.getPlayer().getInventory() == inventory) updateItems();

        },0L, interval);
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

    public void fillBorder(Material material) {

        int max = 0;
        int min = 0;

        if (getRows() == 3) {
            max = 27;
            min = 18;
        } else if (getRows() == 4) {
            max = 36;
            min = 27;
        } else if (getRows() == 5) {
            max = 45;
            min = 36;
        } else if (getRows() == 6) {
            max = 54;
            min = 45;
        }
        if (getRows() >= 3) {
            for (int i = 0; i < 9; i++) if (inventory.getItem(i) == null) inventory.setItem(i, ItemBuilder.builder(material).name(ColorTranslator.string(" ")).build());
            for (int i = min; i < max; i++) if (inventory.getItem(i) == null) inventory.setItem(i, ItemBuilder.builder(Material.WHITE_STAINED_GLASS_PANE).name(ColorTranslator.string(" ")).build());
        }
    }
}

