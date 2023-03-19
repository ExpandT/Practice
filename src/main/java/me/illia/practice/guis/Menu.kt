package me.illia.practice.guis

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack


class Menu: Listener {
    private var inv: Inventory = Bukkit.createInventory(null, 27, "First Gui ;)")

    init {
        initGuiItems()
        Bukkit.getPluginManager().registerEvents(this, Bukkit.getPluginManager().plugins[0])
    }

    @EventHandler
    fun onInventoryClick(e: InventoryClickEvent) {
        if (e.inventory != inv) return
        e.isCancelled = true
        val clickedItem = e.currentItem


        if (clickedItem == null || clickedItem.type.isAir) return
        val p = e.whoClicked as Player

        if(e.rawSlot == 12) {
            p.teleport(Location(Bukkit.getWorld("world"), 0.0, 80.0, 0.0))
            p.sendMessage("${ChatColor.GOLD}You are teleported to world spawn")
        }

        if(e.rawSlot == 14) {
            p.inventory.addItem(ItemStack(Material.DIAMOND, 1))
            p.sendMessage("${ChatColor.GOLD}Take this!")
        }
    }

    @EventHandler
    fun onInventoryClick(e: InventoryDragEvent) {
        if (e.inventory == inv) {
            e.isCancelled = true
        }
    }

    fun openInventory(ent: Player) {
        ent.openInventory(inv)
    }

    private fun initGuiItems() {
        inv.setItem(12, createGuiItem(Material.BLUE_BED, "${ChatColor.BLUE}Go To World Spawn"))
        inv.setItem(14, createGuiItem(Material.DIAMOND, "${ChatColor.AQUA}Get some free diamonds"))
    }

    private fun createGuiItem(material: Material, name: String): ItemStack {
        val item = ItemStack(material, 1)
        val meta = item.itemMeta

        meta.setDisplayName(name)

        item.itemMeta = meta
        return item
    }
}
