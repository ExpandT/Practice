package me.illia.practice.storage

import org.bukkit.ChatColor

enum class Rank(val prefix: String) {
    OWNER("${ChatColor.RED}OWNER"),
    MODER("${ChatColor.BLUE}MODER"),
    USER("${ChatColor.YELLOW}USER");
}