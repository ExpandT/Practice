package me.illia.practice.storage

import org.bukkit.ChatColor

enum class Rank(prefix: String) {

    OWNER("${ChatColor.RED}OWNER"),
    MODER("${ChatColor.BLUE}MODER"),
    USER("${ChatColor.YELLOW}USER");

    private lateinit var prefix: String;

    fun Rank(prefix: String) {
        this.prefix = prefix
    }

    fun getPrefix(): String {
        return this.prefix;
    }

}