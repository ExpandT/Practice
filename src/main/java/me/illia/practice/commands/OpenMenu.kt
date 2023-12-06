package me.illia.practice.commands

import me.illia.practice.guis.BasicGui
import me.illia.practice.guis.GuiItem
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object OpenMenu: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p: Player = sender as Player

        val gui = BasicGui(18, "Гуишкаа)")

        val guiItem = GuiItem.Builder()
            .stack(Material.DIAMOND)
            .onClick { event -> Bukkit.broadcastMessage("Clicked on Diamondddd") }
            .build()

        val guiItemClose = GuiItem.Builder()
            .stack(Material.BARRIER)
            .onClick { event -> gui.close(p) }
            .build()

        gui.addItem(guiItem, 5)
        gui.addItem(guiItemClose, 6)

        gui.open(p)
        p.sendMessage("${ChatColor.GOLD}You opened menu! ;)")
        return true
    }
}
