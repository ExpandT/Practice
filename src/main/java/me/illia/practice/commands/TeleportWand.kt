package me.illia.practice.commands

import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object TeleportWand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        val p: Player = sender as Player

        val item = ItemStack(Material.DIAMOND_HOE)
        val meta = item.itemMeta
        meta.setDisplayName("Teleporter")
        item.itemMeta = meta
        p.inventory.addItem(item)
        p.sendMessage("${ChatColor.GOLD}Take this powerful item")
        return true
    }
}
