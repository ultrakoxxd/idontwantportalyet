package com.idontwantportalyet.dependencies.blueskies.events;

import com.idontwantportalyet.config.commonConfig;
import com.legacy.blue_skies.blocks.SkyPortalBlock;
import com.legacy.blue_skies.registries.SkiesBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.slf4j.Logger;

public class bsEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int PORTAL_SCAN_RANGE = 10;

    @SubscribeEvent
    public static void deleteEverbright(TickEvent.ServerTickEvent event){
        if(!commonConfig.isEverbrightPortalEnabled.get()){
            if (event.phase != TickEvent.Phase.START) return; // Wykonuj tylko w fazie startowej ticku serwera

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeEverbrightPortalBlocks(player);

            }
            }

        }
    private static void removeEverbrightPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level.getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock().equals(SkiesBlocks.everbright_portal)) {
                        player.level.setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Everbright is disabled");
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void deleteEverdawn(TickEvent.ServerTickEvent event){
        if(!commonConfig.isEverdawnPortalEnabled.get()){
            if (event.phase != TickEvent.Phase.START) return; // Wykonuj tylko w fazie startowej ticku serwera

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeEverdownPortalBlocks(player);

            }
        }

    }
    private static void removeEverdownPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level.getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock().equals(SkiesBlocks.everdawn_portal)) {
                        player.level.setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Everdawn is disabled");
                    }
                }
            }
        }
    }
}
