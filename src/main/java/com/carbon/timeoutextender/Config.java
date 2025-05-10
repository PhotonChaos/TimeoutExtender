package com.carbon.timeoutextender;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
@EventBusSubscriber(modid = TimeoutExtender.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config
{
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.IntValue LOGIN_TIMEOUT_SECONDS = BUILDER
            .comment("Time in seconds the server waits during login beyond the default timeout point before disconnecting the player.")
            .defineInRange("loginTimeoutExtraSeconds", 5, 0, Integer.MAX_VALUE);

    private static final ModConfigSpec.IntValue GAME_TIMEOUT_SECONDS = BUILDER
            .comment("Time in seconds the server waits during gameplay beyond the default timeout point before disconnecting the player.")
            .defineInRange("gameTimeoutExtraSeconds", 0, 0, Integer.MAX_VALUE);


    static final ModConfigSpec SPEC = BUILDER.build();

    public static int loginTimeoutExtraSeconds;
    public static int gameTimeoutExtraSeconds;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        loginTimeoutExtraSeconds = LOGIN_TIMEOUT_SECONDS.get();
        gameTimeoutExtraSeconds = GAME_TIMEOUT_SECONDS.get();
    }
}
