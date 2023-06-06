package com.ultrakox.idontwantportalyet.dependencies.TF.events;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.config.common;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.dimension.DimensionDefaults;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.server.ServerMain;
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
}
