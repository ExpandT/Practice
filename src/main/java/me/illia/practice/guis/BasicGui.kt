package me.illia.practice.guis

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

interface GuiItem {
    fun onClick(event: InventoryClickEvent)

    fun toItemStack(): ItemStack

    class Builder {
        private var material: Material = Material.AIR
        private var onClick: ((InventoryClickEvent) -> Unit)? = null

        fun stack(material: Material): Builder {
            this.material = material
            return this
        }

        fun onClick(onClick: (InventoryClickEvent) -> Unit): Builder {
            this.onClick = onClick
            return this
        }

        fun build(): GuiItem {
            require(onClick != null) { "onClick must be set" }

            return object : GuiItem {
                override fun onClick(event: InventoryClickEvent) {
                    onClick?.invoke(event)
                }

                override fun toItemStack(): ItemStack {
                    return ItemStack(material)
                }
            }
        }

    }
}

interface Gui {
    fun addItem(item: GuiItem, slot: Int)

    fun open(player: Player)

    fun close(player: Player)
}

class BasicGui(guiSize: Int = 9, guiName: String = "Default") : Gui, Listener {
    private val items = mutableMapOf<Int, GuiItem>()
    private val inventory: Inventory = Bukkit.createInventory(null, guiSize, guiName)

    init {
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().plugins[0])
    }

    override fun addItem(item: GuiItem, slot: Int) {
        items[slot] = item
        inventory.setItem(slot, item.toItemStack())
    }

    override fun open(player: Player) {
        player.openInventory(inventory)
    }

    override fun close(player: Player) {
        player.closeInventory()
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.inventory == inventory) {
            event.isCancelled = true
            val clickedSlot = event.slot
            val guiItem = items[clickedSlot]
            guiItem?.onClick(event)
        }
    }

    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        if (event.inventory == inventory) {
            Bukkit.broadcastMessage("GUI IS CLOSED")
        }
    }
}
