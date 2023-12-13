package me.illia.practice.managers

import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.node.NodeType
import net.luckperms.api.node.types.PrefixNode
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class PrefixManager {
    fun setPlayerPrefix(player: Player, prefix: String) {
        val luckPerms = LuckPermsProvider.get()
        val user = luckPerms.userManager.getUser(player.uniqueId)


        val node = PrefixNode.builder(prefix, 1).build()

        user?.data()?.clear(NodeType.PREFIX::matches)

        user?.data()?.add(node)
        if (user != null) {
            luckPerms.userManager.saveUser(user)
        }

        player.setPlayerListName("$prefix ${ChatColor.WHITE}${player.displayName}")
        player.sendMessage("Префикс успешно установлен!)")
    }
}
