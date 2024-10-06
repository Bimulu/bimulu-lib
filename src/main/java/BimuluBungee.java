import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import dk.bimulu.library.bimululib.Main;
import dk.bimulu.library.bimululib.utils.general.ColorTranslator;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class BimuluBungee implements PluginMessageListener {

    private static int playerCount = 0;

    public BimuluBungee() {
        Main.getInstance().getServer().getMessenger().registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
        Main.getInstance().getServer().getMessenger().registerIncomingPluginChannel(Main.getInstance(), "BungeeCord", this);
    }

    public static void sendPlayerToServer(Player player, String server) {
        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("Connect");
            out.writeUTF(server);
            player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
            b.close();
            out.close();
        }
        catch (Exception e) {
            player.sendMessage(ColorTranslator.adventure("&cDer skete en fejl imens du prøvede at tilslutte " + server));
        }
    }

    public static int getPlayerCount(Player player, String server) {

        try {
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(b);
            out.writeUTF("PlayerCount");
            out.writeUTF(server);
            player.sendPluginMessage(Main.getInstance(), "BungeeCord", b.toByteArray());
            b.close();
            out.close();
        }
        catch (Exception e) {
            playerCount = 0;
        }

        return playerCount;
    }

    @Override
    public void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, @NotNull byte[] message) {
        if (!channel.equals("BungeeCord")) return;

        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String subchannel = in.readUTF();

        if (subchannel.equals("PlayerCount")) playerCount = in.readInt();
    }
}
