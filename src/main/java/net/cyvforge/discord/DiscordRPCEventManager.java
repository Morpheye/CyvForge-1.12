package net.cyvforge.discord;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.*;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class DiscordRPCEventManager {

    @SubscribeEvent
    public void logInEvent(EntityJoinWorldEvent e) {
        if (e.getEntity() == null || !(e.getEntity().equals(Minecraft.getMinecraft().player))) return;
        try {
            if (Minecraft.getMinecraft().isSingleplayer()) {
                DiscordRPCHandler.instance.updateStatus("Playing Singleplayer", null, null);
                return;
            }

            ServerData data = Minecraft.getMinecraft().getCurrentServerData();
            if (data != null && data.serverIP.equalsIgnoreCase("play.hpknetwork.xyz")) {
                DiscordRPCHandler.instance.updateStatus("Playing Multiplayer", "hpk", "play.hpknetwork.xyz");
            } else {
                DiscordRPCHandler.instance.updateStatus("Playing Multiplayer", null, null);
            }


        } catch (Exception ignored) {}
    }

    @SubscribeEvent
    public void logoutEvent(GuiOpenEvent e) {
        String text = null;
        GuiScreen gui = e.getGui();

        if (gui instanceof GuiMultiplayer || gui instanceof GuiScreenServerList
                || gui instanceof GuiScreenAddServer) text = "In Multiplayer Menu";
        else if (gui instanceof GuiMainMenu) text = "In Main Menu";
        else if (gui instanceof GuiWorldSelection) text = "In Singleplayer Menu";
        else if (gui instanceof GuiConnecting || gui instanceof GuiDisconnected) text = "Connecting...";

        if (Minecraft.getMinecraft().world == null && text != null) {
            DiscordRPCHandler.instance.updateStatus(text, null, null);
        }
    }

}
