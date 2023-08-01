package com.ultrakox.idontwantportalyet.dependencies.tforest.events;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.config.commonConfig;
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
                    }
                }
            }
        }
    }
}
