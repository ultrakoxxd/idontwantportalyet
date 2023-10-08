package com.ultrakox.idontwantportalyet.dependencies.aether.events;

import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.block.portal.AetherPortalBlock;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;

public class aetherEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int PORTAL_SCAN_RANGE = 10;


    @SubscribeEvent
    public static void deleteAetherPortal(TickEvent.ServerTickEvent event) {
        if (!commonConfig.isAetherPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return;

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeAetherPortalBlocks(player);

            }
        }
    }

    private static void removeAetherPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == AetherBlocks.AETHER_PORTAL.get()) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Aether portal is disabled");
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
        if (event.phase == TickEvent.Phase.START && commonConfig.aetherPortalTimerInt.get() >= 0) {
            commonConfig.aetherPortalTimerInt.set(commonConfig.aetherPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.aetherPortalTimerInt.get()));
            if (commonConfig.aetherPortalTimerInt.get() == 0) {
                commonConfig.isAetherPortalEnabled.set(true);
                LOGGER.debug("Aether portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.aetherPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }

}
