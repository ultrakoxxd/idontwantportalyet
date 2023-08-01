package com.idontwantportalyet.dependencies.twilightforest.events;


import com.idontwantportalyet.config.commonConfig;

import com.mojang.logging.LogUtils;

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
                    if (player.level.getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == TFBlocks.TWILIGHT_PORTAL.get()) {
                        player.level.setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Twilight portal is disabled");
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
        //tf portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.TFPortalTimerInt.get() >= 0) {
            commonConfig.TFPortalTimerInt.set(commonConfig.TFPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.TFPortalTimerInt.get()));
            if (commonConfig.TFPortalTimerInt.get() == 0) {
                commonConfig.isTFPortalEnabled.set(true);
                LOGGER.debug("tf portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.TFPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }
}