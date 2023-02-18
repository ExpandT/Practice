package me.illia.practice.managers

import me.illia.practice.storage.Rank
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class NameTagManager {

    fun setNameTags(player: Player) {
        player.scoreboard = Bukkit.getScoreboardManager().newScoreboard

        for (rank in Rank.values()) {
            val team = player.scoreboard.registerNewTeam(rank.name)
            team.prefix = rank.getPrefix() + "${ChatColor.WHITE} | "
        }

        for (target in Bukkit.getOnlinePlayers()) {
            player.scoreboard.getTeam(RankManager().getRank(target).name)?.addEntry(target.name)
        }

    }

    fun newTag(player: Player) {
        val rank: Rank = RankManager().getRank(player)

        for (target in Bukkit.getOnlinePlayers()) {
            target.scoreboard.getTeam(rank.name)?.addEntry(player.name)
        }
    }

}