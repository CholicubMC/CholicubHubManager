package me.shyybi.cholicubhubmanager.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import java.util.HashMap;
import java.util.UUID;

public class Hub implements CommandExecutor, Listener {
    private final HashMap<UUID, Long> lastDamageTime = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be executed by a player.");
            return true;
        }

        Player player = (Player) sender;
        long currentTime = System.currentTimeMillis();
        long lastDamage = lastDamageTime.getOrDefault(player.getUniqueId(), 0L);

        if (currentTime - lastDamage < 10000) {
            player.sendMessage(ChatColor.RED + "Vous avez pris des dégats dans les 8 dernières secondes, vous ne pouvez pas vous teleporter.");
            return true;
        }

        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        player.getInventory().clear();
        out.writeUTF("Connect");
        out.writeUTF("lobby");

        player.sendPluginMessage(Bukkit.getPluginManager().getPlugin("CholicubHubManager"), "BungeeCord", out.toByteArray());
        player.closeInventory();
        return true;
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            lastDamageTime.put(player.getUniqueId(), System.currentTimeMillis());
        }
    }
}