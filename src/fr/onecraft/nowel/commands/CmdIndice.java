package fr.onecraft.nowel.commands;

import fr.onecraft.nowel.Nowel;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;

public class CmdIndice implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        // send error if command sender is not a player
        if (!(sender instanceof Player)) {
            sender.sendMessage(Nowel.ERROR + "Cette commande n'est faisable que par un joueur.");
            return true;
        }

        Player player = (Player) sender;

        // add link
        TextComponent link = new TextComponent("Clique ici");
        link.setColor(ChatColor.GREEN);
        link.setBold(true);
        link.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(
                "§7Clique ici pour obtenir l'indice !"
        ).create()));

        // get current day
        String currentDayStr = new SimpleDateFormat("dd").format(System.currentTimeMillis());

        if (args.length == 0) {
            // add web redirect
            link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
                    "http://xmas.onecraft.fr/" + currentDayStr + ".php?uuid=" + player.getUniqueId().toString()
            ));

            player.sendMessage(Nowel.PREFIX + "Tu peux faire §a/indice <jour>§7 pour voir les anciens.");
        } else {
            int day;
            int currentDay;

            // parse string to int or else send error
            try {
                day = Integer.parseInt(args[0]);
                currentDay = Integer.parseInt(currentDayStr);
            } catch (NumberFormatException e) {
                player.sendMessage(Nowel.ERROR + "Ce jour n'est pas valide.");
                return true;
            }

            // return error if the date is not valid
            if (day < 1) {
                player.sendMessage(Nowel.ERROR + "Ce jour n'est pas valide.");
                return true;
            }

            // return error if the date is not valid
            if (day > 31) {
                player.sendMessage(Nowel.ERROR + "T'as cru que c'était noël toute l'année ?");
                return true;
            }

            // return error if the date is not valid
            if (currentDay < day) {
                player.sendMessage(Nowel.ERROR + "Patience, n'ouvre pas ton calendrier trop tôt ! :)");
                return true;
            }

            // add web redirect
            link.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL,
                    "http://xmas.onecraft.fr/" + (day < 10 ? "0" + day : day) + ".php?uuid=" + player.getUniqueId().toString()
            ));
        }

        // add prefix
        TextComponent message = new TextComponent(Nowel.PREFIX);
        message.setColor(ChatColor.GRAY);

        // add the clickable link and the end of the msg
        message.addExtra(link);
        message.addExtra(" pour obtenir l'indice !");

        player.spigot().sendMessage(message);
        return true;
    }
}