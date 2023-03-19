package me.illia.practice.listeners

import me.illia.practice.Practice
import me.illia.practice.managers.NameTagManager
import me.illia.practice.managers.RankManager
import me.illia.practice.storage.Rank
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.*

object PlayerEventListener: Listener {

    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("${ChatColor.GOLD}Hey, Bro ;)")
        val main: Practice = Practice.INSTANCE
        val config: FileConfiguration = main.config
        val player = event.player
        val uuid = player.uniqueId.toString()

        if(!config.contains(uuid)) {
            RankManager().setRank(Rank.USER, player)
        }

        NameTagManager().setNameTags(player)
    }

    @EventHandler
    fun onPlayerChat(event: AsyncPlayerChatEvent) {
        event.format = RankManager().getRank(event.player).prefix + "${ChatColor.WHITE} | "  + event.player.name + ": " + event.message
    }

    @EventHandler
    fun onBedEnter(event: PlayerBedEnterEvent) {
        val player: Player = event.player
        event.isCancelled = true

        player.sendMessage("${ChatColor.BLUE}Hey ${player.name}, you can't sleep on this Server")
    }

    @EventHandler
    fun onBlockHarvest(event: PlayerHarvestBlockEvent) {
        val harvestedBlock = event.harvestedBlock
        for (player in Bukkit.getOnlinePlayers()) {
            player.sendMessage("${ChatColor.RED}${event.player.name} has harvested ${harvestedBlock.type} at ${harvestedBlock.getLocation()}")
        }
    }

    @EventHandler
    fun onRightClick(event: PlayerInteractEvent) {
        val player = event.player
        val block = event.clickedBlock

        if (event.action == Action.RIGHT_CLICK_BLOCK) {
            if (player.inventory.itemInMainHand.itemMeta.getDisplayName() == "Diamond Wand") {
                block?.type = Material.DIAMOND_BLOCK
            }
        }

        if (event.action == Action.RIGHT_CLICK_AIR) {
            if (player.inventory.itemInMainHand.itemMeta.getDisplayName() == "Teleporter") {
                val targetedBlock = player.getTargetBlock(null, 100)
                if(targetedBlock.type == Material.AIR) return

                player.teleport(targetedBlock.location)
                player.sendMessage("${ChatColor.LIGHT_PURPLE}TELEPORTED")
            }
        }

    }
}
