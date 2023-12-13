package me.illia.practice

import me.illia.practice.commands.*
import me.illia.practice.listeners.GuiListener
import me.illia.practice.listeners.PlayerEventListener
import me.illia.practice.managers.PrefixManager
import org.bukkit.plugin.java.JavaPlugin

class Practice: JavaPlugin() {

    companion object {
        lateinit var INSTANCE: Practice
    }

    override fun onEnable() {
        INSTANCE = this

        logger.info("IT WORKSSSSS")

        config.options().copyDefaults(true)
        saveConfig()

        registerEvents()
        registerCommands()
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(PlayerEventListener, this)
        server.pluginManager.registerEvents(GuiListener, this)
    }

    private fun registerCommands() {
        getCommand("setRank")?.setExecutor(SetRank)
        getCommand("openMenu")?.setExecutor(OpenMenu)
        getCommand("magicWand")?.setExecutor(MagicWand)
        getCommand("teleporter")?.setExecutor(TeleportWand)
        getCommand("setprefix")?.setExecutor(SetPrefix(this ,PrefixManager()))
        getCommand("openPrefixMenu")?.setExecutor(OpenPrefixMenu)
    }

}
