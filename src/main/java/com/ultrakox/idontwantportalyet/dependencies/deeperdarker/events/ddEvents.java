package com.ultrakox.idontwantportalyet.dependencies.deeperdarker.events;

import com.kyanite.deeperdarker.content.DDBlocks;
import com.kyanite.deeperdarker.content.blocks.OthersidePortalBlock;
import com.ultrakox.idontwantportalyet.config.commonConfig;

import com.ultrakox.idontwantportalyet.IDontWantPortalYet;

import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import org.slf4j.Logger;


public class ddEvents {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int PORTAL_SCAN_RANGE = 10;

    @SubscribeEvent
    public static void deleteOthersidePortal(TickEvent.ServerTickEvent event){
        if (!commonConfig.isOthersidePortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return;

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeOthersidePortalBlocks(player);

            }
        }
    }

    private static void removeOthersidePortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == DDBlocks.OTHERSIDE_PORTAL.get()) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Otherside portal is disabled");
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
        if (event.phase == TickEvent.Phase.START && commonConfig.othersidePortalTimerInt.get() >= 0) {
            commonConfig.othersidePortalTimerInt.set(commonConfig.othersidePortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.othersidePortalTimerInt.get()));
            if (commonConfig.othersidePortalTimerInt.get() == 0) {
                commonConfig.isOthersidePortalEnabled.set(true);
                LOGGER.debug("Otherside portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.othersidePortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }
    @SubscribeEvent
    public static void portalAvancementMade(AdvancementEvent.AdvancementEarnEvent event) {

        if (event.getAdvancement().getId().toString().equals(commonConfig.othersidePortalAdvancement.get())) {
            commonConfig.isOthersidePortalEnabled.set(true);
            LOGGER.debug("Otherside portal is enabled advancement made" + event.getAdvancement().toString());
            event.getEntity().sendSystemMessage(Component.literal("Otherside portal is now enabled!").withStyle(ChatFormatting.AQUA));
        }

    }
}
