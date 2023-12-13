package me.illia.practice.guis

import me.illia.practice.managers.PrefixManager
import me.illia.practice.storage.Prefix
import org.bukkit.Material
import org.bukkit.entity.Player

class PrefixMenu(val player: Player, val prefixManager: PrefixManager): BasicGui(27, "Префиксы") {

    override fun initialize() {
        initPrefixes()
    }

    private fun initPrefixes() {
        val prefixList = Prefix.values().map {
            GuiItem.Builder()
                .stack(Material.BLAZE_POWDER)
                .displayName("Префикс - " + it.prefix)
                .onClick {  event ->
                    val player = (event.whoClicked as Player)
                    prefixManager.setPlayerPrefix(player, it.prefix)
                    close(player)
                }
                .build()
        }

        prefixList.forEachIndexed { index, guiItem ->
            addItem(guiItem, index)
        }
    }
}
