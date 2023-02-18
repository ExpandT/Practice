package me.illia.practice.managers

import me.illia.practice.Practice
import me.illia.practice.storage.Rank
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player


class RankManager {

    fun setRank(rank: Rank, player: Player) {
        val main: Practice = Practice().getMain()
        val config: FileConfiguration = main.config
        val uuid = player.uniqueId.toString()

        config.set(uuid, rank.name)
        main.saveConfig()
    }


    fun getRank(player: Player): Rank {
        val main: Practice = Practice().getMain()
        val config: FileConfiguration = main.config

        return Rank.valueOf(config.getString(player.uniqueId.toString()).toString())
    }

}