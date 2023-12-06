package me.illia.practice.listeners

import me.illia.practice.guis.BasicGui
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent

object GuiListener: Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        (event.inventory.holder as? BasicGui)?.onClick(event)
    }

}
