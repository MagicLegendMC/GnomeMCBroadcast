package broadcasts.broadcasts;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;
import java.util.logging.Logger;

/**
 *  @author Simon_Xz
 *  @since 1.0
 */

public final class CustomBroadcasts extends JavaPlugin {



    @Override
    public void onEnable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        this.getLogger().info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has been Enabled!");
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            getLogger().info("Config.yml not found, creating one for you!");
            saveDefaultConfig();
        } else {
            getLogger().info("Config.yml found, loading!");
        }
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        PluginDescriptionFile pdfFile = this.getDescription();
        this.getLogger().info(pdfFile.getName() + " Version " + pdfFile.getVersion() + " Has been Disabled!");
        // Plugin shutdown logic
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if ((sender.hasPermission("gnomemc.broadcast")) && (command.getName().equalsIgnoreCase("broadcast")) || (command.getName().equalsIgnoreCase("bc"))) {
            if (args.length == 0) {
                sender.sendMessage(colorize("&4Usage: /broadcast <message>"));
                return true;
            }

            StringBuilder builder = new StringBuilder();
            for(int i = 0; i< args.length; i++) {
                builder.append(args[i]).append(" ");
            }
            String msg = builder.toString();

            String prefix = this.getConfig().getString("prefix");
            Bukkit.broadcastMessage(colorize(prefix + " " + msg.toString().trim()));

            return true;
        }

        if ((sender.hasPermission("gnomemc.reload")) && (command.getName().equalsIgnoreCase("broadcastreload"))) {
            sender.sendMessage(colorize("&a&lGnomeMCBroadcast Reloaded!"));
            this.reloadConfig();

            return true;
        }

        if ((sender.hasPermission("gnomemc.shout")) && (command.getName().equalsIgnoreCase("shout"))) {
            if (args.length == 0) {
                sender.sendMessage(colorize("&4Usage: /shout <message>"));
                return true;
            }

            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                builder.append(args[i]).append(" ");
                        }
            String msg = builder.toString();

            String prefix = this.getConfig().getString("shout-prefix");
            Player player = (Player)sender;
            Bukkit.broadcastMessage(colorize(prefix + " &f" + player.getName() + ": &e"  + msg.toString().trim()));

            return true;
        }

        return true;
        }





        private String colorize(String todo) {
        return ChatColor.translateAlternateColorCodes('&', todo);
        }
    }
