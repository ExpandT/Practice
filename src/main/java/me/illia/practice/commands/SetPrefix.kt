package me.illia.practice.commands

import me.illia.practice.Practice
import me.illia.practice.managers.PrefixManager
import net.luckperms.api.LuckPermsProvider
import net.luckperms.api.node.NodeType
import net.luckperms.api.node.types.PrefixNode
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetPrefix( private val main: Practice, private val prefixManager: PrefixManager): CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player && label.equals("setprefix", ignoreCase = true)) {
            if (args.isNotEmpty()) {
                val player = Bukkit.getPlayerExact(args[0]) as Player
                val prefix = args[1]

                setPlayerPrefix(player, prefix)
                sender.sendMessage("${ChatColor.GOLD}Префикс установлен: $prefix")
            } else {
                sender.sendMessage("${ChatColor.GOLD}Используйте: /setprefix <ник> <префикс>")
            }
            return true
        }
        return false
    }

    private fun setPlayerPrefix(player: Player, prefix: String) {
        val luckPerms = LuckPermsProvider.get()
        val user = luckPerms.userManager.getUser(player.uniqueId)


        val node = PrefixNode.builder(prefix, 1).build()

        user?.data()?.clear(NodeType.PREFIX::matches)

        user?.data()?.add(node)
        if (user != null) {
            luckPerms.userManager.saveUser(user)
        }

        player.setPlayerListName("$prefix ${ChatColor.WHITE}${player.displayName}")
    }


}
