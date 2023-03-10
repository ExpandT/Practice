package me.illia.practice

import me.illia.practice.commands.SetRank
import me.illia.practice.listeners.PlayerEventListener
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
    }

    private fun registerCommands() {
        getCommand("setRank")?.setExecutor(SetRank)
    }
}