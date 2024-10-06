package dk.bimulu.library.bimululib.utils.text;

import dk.bimulu.library.bimululib.utils.general.ColorTranslator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;


public class Center {

    public static String getCenteredMessage(String message) {
        if(message == null || message.equals("")) {
            return "";
        }
        message = ColorTranslator.string(message);

        int messagePxSize = 0;
        boolean previousCode = false;
        boolean isBold = false;

        for(char c : message.toCharArray()){
            if(c == '§'){
                previousCode = true;
            }else if(previousCode){
                previousCode = false;
                isBold = c == 'l' || c == 'L';
            }else{
                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
                messagePxSize++;
            }
        }
        int CENTER_PX = 154;
        int halvedMessageSize = messagePxSize / 2;
        int toCompensate = CENTER_PX - halvedMessageSize;
        int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
        int compensated = 0;
        StringBuilder sb = new StringBuilder();
        while(compensated < toCompensate){
            sb.append(" ");
            compensated += spaceLength;
        }
        return sb + message;
    }

    public static void sendCenteredMessage(Player player, String message) {
        player.sendMessage(getCenteredMessage(message));
    }

    public static void broadcastCenteredMessage(String prefix, String... messages) {

        Bukkit.broadcastMessage(getCenteredMessage(" "));
        Bukkit.broadcastMessage(getCenteredMessage(prefix));
        Bukkit.broadcastMessage(getCenteredMessage("&7&m-----&r"));

        for (String message : messages) Bukkit.broadcastMessage(getCenteredMessage(message));

        Bukkit.broadcastMessage(getCenteredMessage(" "));
    }

}
