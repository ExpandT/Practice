package me.illia.practice.guis

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack


class Menu: BasicGui(27, "NewYear is coming!!!") {

    override fun initialize() {
        val closeItem = GuiItem.Builder()
            .stack(Material.BARRIER)
            .onClick {  event ->
                close(event.whoClicked as Player)
            }
            .build()

        val diamondItem = GuiItem.Builder()
            .stack(Material.DIAMOND)
            .onClick { event ->
                (event.whoClicked as? Player)?.inventory?.addItem(ItemStack(Material.DIAMOND))
            }
            .build()

        addItem(closeItem, 14)
        addItem(diamondItem, 12)
    }

    override fun onClick(event: InventoryClickEvent) {
        if (event.inventory == inventory) {
            event.isCancelled = true
            val clickedSlot = event.slot
            val guiItem = items[clickedSlot]
            guiItem?.onClick(event)
        }
    }

}
