package fr.onecraft.nowel;

import fr.onecraft.nowel.commands.CmdIndice;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class Nowel extends JavaPlugin {
    public static String PREFIX = "§9Noël > §7";
    public static String ERROR = "§cErreur > §7";

    @Override
    public void onEnable() {
        // register command
        PluginCommand command = this.getCommand("indice");
        command.setExecutor(new CmdIndice());

        Bukkit.getConsoleSender().sendMessage("[" + getName() + "] Nowel has been enabled.");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[" + getName() + "] Nowel has been disabled.");
    }
}