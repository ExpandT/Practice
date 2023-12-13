package me.illia.practice.commands

import me.illia.practice.guis.PrefixMenu
import me.illia.practice.managers.PrefixManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object OpenPrefixMenu: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player

        val gui = PrefixMenu(player, PrefixManager())
        gui.initialize()
        gui.open(player)


        return true
    }

}
