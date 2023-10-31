package com.ultrakox.idontwantportalyet.dependencies.tforest.events;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;
import twilightforest.init.TFBlocks;

public class tfEvents {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int PORTAL_SCAN_RANGE = 10;


    @SubscribeEvent
    public static void deleteTFPortal(TickEvent.ServerTickEvent event) {
        if (!commonConfig.isTFPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return; // Wykonuj tylko w fazie startowej ticku serwera

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeTwilightPortalBlocks(player);

            }
        }
    }

    private static void removeTwilightPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == TFBlocks.TWILIGHT_PORTAL.get()) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Twilight portal is disabled");
                        player.sendSystemMessage(Component.literal("This portal is disabled").withStyle(ChatFormatting.DARK_RED));
                    }
                }
            }
        }
    }

    //
    // TIMER
    //
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        //otherside portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.TFPortalTimerInt.get() >= 0) {
            commonConfig.TFPortalTimerInt.set(commonConfig.TFPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.TFPortalTimerInt.get()));
            if (commonConfig.TFPortalTimerInt.get() == 0) {
                commonConfig.isTFPortalEnabled.set(true);
                LOGGER.debug("Twilight forest portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.TFPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }

}
