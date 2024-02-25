package com.ultrakox.idontwantportalyet.dependencies.blueskies.events;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.legacy.blue_skies.blocks.SkyPortalBlock;
import com.legacy.blue_skies.registries.SkiesBlocks;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.slf4j.Logger;

public class bsEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int PORTAL_SCAN_RANGE = 10;

    @SubscribeEvent
    public static void deleteEverbright(TickEvent.ServerTickEvent event){
        if(!commonConfig.isEverbrightPortalEnabled.get()){
            if (event.phase != TickEvent.Phase.START) return;

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeEverbrightPortalBlocks(player);

            }
            }

        }
    private static void removeEverbrightPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock().equals(SkiesBlocks.everbright_portal)) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Everbright is disabled");
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void deleteEverdawn(TickEvent.ServerTickEvent event){
        if(!commonConfig.isEverdawnPortalEnabled.get()){
            if (event.phase != TickEvent.Phase.START) return;

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeEverdownPortalBlocks(player);

            }
        }

    }
    private static void removeEverdownPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock().equals(SkiesBlocks.everdawn_portal)) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
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
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        //everbright portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.everbrightPortalTimerInt.get() >= 0) {
            commonConfig.everbrightPortalTimerInt.set(commonConfig.everbrightPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.everbrightPortalTimerInt.get()));
            if (commonConfig.everbrightPortalTimerInt.get() == 0) {
                commonConfig.isEverbrightPortalEnabled.set(true);
                LOGGER.debug("Everbright portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.everbrightPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
        //everdawn portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.everdawnPortalTimerInt.get() >= 0) {
            commonConfig.everdawnPortalTimerInt.set(commonConfig.everdawnPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.everdawnPortalTimerInt.get()));
            if (commonConfig.everdawnPortalTimerInt.get() == 0) {
                commonConfig.isEverdawnPortalEnabled.set(true);
                LOGGER.debug("Everdawn portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.everdawnPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }
    @SubscribeEvent
    public static void portalAvancementMade(AdvancementEvent.AdvancementEarnEvent event) {

        if (event.getAdvancement().getId().toString().equals(commonConfig.everbrightPortalAdvancement.get())) {
            commonConfig.isEverbrightPortalEnabled.set(true);
            LOGGER.debug("Everbright portal is enabled advancement made" + event.getAdvancement().toString());
            event.getEntity().sendSystemMessage(Component.literal("Everbright portal is now enabled!").withStyle(ChatFormatting.AQUA));
        }
        if (event.getAdvancement().getId().toString().equals(commonConfig.everdawnPortalAdvancement.get())) {
            commonConfig.isEverdawnPortalEnabled.set(true);
            LOGGER.debug("Everdawn portal is enabled advancement made" + event.getAdvancement().toString());
            event.getEntity().sendSystemMessage(Component.literal("Everdawn portal is now enabled!").withStyle(ChatFormatting.AQUA));
        }
    }
}
