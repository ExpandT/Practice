package me.illia.practice.commands

import me.illia.practice.guis.Menu
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object OpenMenu: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val p: Player = sender as Player

        val gui = Menu()
        gui.initialize()
        gui.open(p)

        p.sendMessage("${ChatColor.GOLD}You opened menu! ;)")
        return true
    }
}
