package dk.bimulu.library.bimululib.menu;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class PaginatedMenu extends Menu {

    //Keep track of what page the menu is on
    protected int page = 0;
    //28 is max items because with the border set below,
    //28 empty slots are remaining.
    //the index represents the index of the slot
    //that the loop is on
    protected int index = 0;

    public abstract List<ItemStack> getItems();

    public PaginatedMenu(MenuUtility menuUtility) {
        super(menuUtility);
    }

    public abstract int getMaxItemsPerPage();

    //Set the border and menu buttons for the men

    public void nextPage() {
        if (!((index + 1) >= getItems().size())) {

            page = page + 1;
            super.open();
        }
    }

    public void previousPage() {
        if (page == 0) return;

        page = page - 1;
        super.open();
    }

    public void setPaginatedItems() {

        if (getItems() == null || getItems().isEmpty()) return;

        for (int i = 0; i < getMaxItemsPerPage(); i++) {

            index = getMaxItemsPerPage() * page + i;

            if (index >= getItems().size()) break;

            if (getItems().get(index) == null) break;

            inventory.addItem(getItems().get(index));
        }
    }
}

