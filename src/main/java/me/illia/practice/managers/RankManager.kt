package me.illia.practice.managers

import me.illia.practice.Practice
import me.illia.practice.storage.Rank
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player


class RankManager {

    fun setRank(rank: Rank, player: Player) {
        val main: Practice = Practice.INSTANCE
        val config: FileConfiguration = main.config
        val uuid = player.uniqueId.toString()

        config.set(uuid, rank.name)
        main.saveConfig()
    }


    fun getRank(player: Player): Rank {
        val main: Practice = Practice.INSTANCE
        val config: FileConfiguration = main.config

        return if(config.getString(player.uniqueId.toString()) is String) {
            Rank.valueOf(config.getString(player.uniqueId.toString()).toString())
        } else {
            Rank.USER
        }
    }

}