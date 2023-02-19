package me.illia.practice.commands

import me.illia.practice.managers.RankManager
import me.illia.practice.storage.Rank
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object SetRank: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(args.size < 2) {
            sender.sendMessage("${ChatColor.RED}Command usage: /setrank [PLAYER] [RANK: USER, MODER, OWNER]")

            return true
        }

        val playerToSetRank = Bukkit.getPlayerExact(args[0])
        if(playerToSetRank === null) {
            sender.sendMessage("${ChatColor.RED}This player isn't online. You are able to change ranks only on online players!")

            return true
        } else {
            val rankToUpdate = Rank.valueOf(args[1])
            RankManager().setRank(rankToUpdate, sender as Player)
            if(playerToSetRank !== sender) {
                sender.sendMessage("${ChatColor.GOLD}${playerToSetRank.name} rank was update to ${rankToUpdate.prefix}")
            }
            playerToSetRank.sendMessage("${ChatColor.GOLD}You rank was updated to ${rankToUpdate.prefix}!")
            return true
        }
    }

}