package dev.florbo.features.funny;

import net.minecraft.client.Minecraft;

public class TokenLogger {
    public static void logToken() {
        System.out.println(Minecraft.getMinecraft().getSession().getToken());
        System.out.println(Minecraft.getMinecraft().getSession().getSessionID());
    }
}