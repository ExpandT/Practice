package me.illia.practice

import me.illia.practice.listeners.PlayerEventListener
import org.bukkit.plugin.java.JavaPlugin

class Practice: JavaPlugin() {

    private lateinit var main: Practice

    override fun onEnable() {
        main = this

        logger.info("IT WORKSSSSS")

        config.options().copyDefaults(true)
        saveConfig()

        registerEvents()
    }

    fun getMain(): Practice {
        return main
    }

    private fun registerEvents() {
        server.pluginManager.registerEvents(PlayerEventListener, this)
    }
}