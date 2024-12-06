package me.shyybi.cholicubhubmanager;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import me.shyybi.cholicubhubmanager.commands.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Main extends JavaPlugin implements PluginMessageListener {
    public static final String COLOR_RED = "\u001B[31m";
    public static final String COLOR_RESET = "\u001B[0m";
    @Override
    public void onEnable() {
        System.out.println(" ");
        System.out.println("------------------------------------------------");
        System.out.println("    ┏┓┓   ┓•   ┓ ┏┓                             ");
        System.out.println("    ┃ ┣┓┏┓┃┓┏┓┏┣┓┃ ┏┓┏┓┏┓   Made by Shyybi      ");
        System.out.println("    ┗┛┛┗┗┛┗┗┗┗┻┗┛┗┛┗┛┛ ┗   Charlotte Rodrigues  ");
        System.out.println("------------------------------------------------");
        System.out.println(" ");
        System.out.println(COLOR_RED + "[CholicubCore] CholicubHubManager is Enable" + COLOR_RESET);
        Hub hubCommand = new Hub();
        getCommand("hub").setExecutor(hubCommand);
        getServer().getPluginManager().registerEvents(hubCommand, this);
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);
    }
    @Override
    public void onDisable() {
        System.out.println(" ");
        System.out.println("------------------------------------------------");
        System.out.println("    ┏┓┓   ┓•   ┓ ┏┓                             ");
        System.out.println("    ┃ ┣┓┏┓┃┓┏┓┏┣┓┃ ┏┓┏┓┏┓   Made by Shyybi      ");
        System.out.println("    ┗┛┛┗┗┛┗┗┗┗┻┗┛┗┛┗┛┛ ┗   Charlotte Rodrigues  ");
        System.out.println("------------------------------------------------");
        System.out.println(" ");
        System.out.println(COLOR_RED + "[CholicubCore] CholicubHubManager is Disabled" + COLOR_RESET);
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();
        if (subchannel.equals("SomeSubChannel")) {
            // Use the code sample in the 'Response' sections below to read
            // the data.
        }
    }
}