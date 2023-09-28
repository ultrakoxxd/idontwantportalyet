package com.ultrakox.idontwantportalyet.dependencies.blueskies.events;

import com.ultrakox.idontwantportalyet.config.common;
import com.legacy.blue_skies.registries.SkiesBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.slf4j.Logger;

public class bsEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int PORTAL_SCAN_RANGE = 10;

    @SubscribeEvent
    public static void deleteEverbright(TickEvent.WorldTickEvent event){
        if(!common.isEverbrightPortalEnabled.get()){
            if (event.phase != TickEvent.Phase.START) return;

            for (Player player : event.world.players()) {
                removeEverbrightPortalBlocks(player);

            }
        }

    }
    private static void removeEverbrightPortalBlocks(Player player) {
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
    public static void deleteEverdawn(TickEvent.WorldTickEvent event){
        if(!common.isEverdawnPortalEnabled.get()){
            if (event.phase != TickEvent.Phase.START) return;

            for (Player player : event.world.players()) {
                removeEverdownPortalBlocks(player);

            }
        }

    }
    private static void removeEverdownPortalBlocks(Player player) {
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
    //
    // TIMER
    //
    @SubscribeEvent
    public static void onServerTick(TickEvent.WorldTickEvent event) {
        //everbright portal timer
        if (event.phase == TickEvent.Phase.START && common.everbrightPortalTimerInt.get() >= 0) {
            common.everbrightPortalTimerInt.set(common.everbrightPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(common.everbrightPortalTimerInt.get()));
            if (common.everbrightPortalTimerInt.get() == 0) {
                common.isEverbrightPortalEnabled.set(true);
                LOGGER.debug("Everbright portal is now enabled!");
                for (Player player : event.world.players()) {
                    player.sendMessage(Component.nullToEmpty(common.everbrightPortalTimerAfter.get()), player.getUUID());
                }
            }
        }
        //everdawn portal timer
        if (event.phase == TickEvent.Phase.START && common.everdawnPortalTimerInt.get() >= 0) {
            common.everdawnPortalTimerInt.set(common.everdawnPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(common.everdawnPortalTimerInt.get()));
            if (common.everdawnPortalTimerInt.get() == 0) {
                common.isEverdawnPortalEnabled.set(true);
                LOGGER.debug("Everdawn portal is now enabled!");
                for (Player player : event.world.players()) {
                    player.sendMessage(Component.nullToEmpty(common.everdawnPortalTimerAfter.get()), player.getUUID());
                }
            }
        }
    }
}
