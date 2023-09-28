package com.ultrakox.idontwantportalyet.dependencies.TF.events;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.config.common;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;
import twilightforest.block.TFBlocks;


public class TFEvents {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int PORTAL_SCAN_RANGE = 10;
    @SubscribeEvent
    public static void deleteTFPortal(TickEvent.WorldTickEvent event) {
        if (!common.isTFPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return; // Wykonuj tylko w fazie startowej ticku serwera

            for (Player player : event.world.players()) {
                removeTwilightPortalBlocks(player);

            }
        }
    }

    private static void removeTwilightPortalBlocks(Player player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level.getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == TFBlocks.TWILIGHT_PORTAL.get()) {
                        player.level.setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.info("Twilight portal is disabled");
                    }
                }
            }
        }
    }

    //
    // TIMER
    //
    @SubscribeEvent
    public static void onServerTick(TickEvent.WorldTickEvent event) {
        //tf portal timer
        if (event.phase == TickEvent.Phase.START && common.ugrPortalTimerInt.get() >= 0) {
            common.ugrPortalTimerInt.set(common.ugrPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(common.ugrPortalTimerInt.get()));
            if (common.ugrPortalTimerInt.get() == 0) {
                common.isTFPortalEnabled.set(true);
                LOGGER.debug("tf portal is now enabled!");
                for (Player player : event.world.players()) {
                    player.sendMessage(Component.nullToEmpty(common.TFPortalTimerAfter.get()), player.getUUID());
                }
            }
        }
    }
}
