package com.ultrakox.idontwantportalyet.dependencies.undergarden.events;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;
import quek.undergarden.registry.UGBlocks;

public class ugrEvents {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int PORTAL_SCAN_RANGE = 10;
    @SubscribeEvent
    public static void deleteUgrPortal(TickEvent.ServerTickEvent event){
        if (!commonConfig.isUgrPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return;

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removePortalBlocks(player);

            }
        }
    }

    private static void removePortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == UGBlocks.UNDERGARDEN_PORTAL.get()) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Undegarden portal is disabled");
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
        if (event.phase == TickEvent.Phase.START && commonConfig.ugrPortalTimerInt.get() >= 0) {
            commonConfig.ugrPortalTimerInt.set(commonConfig.ugrPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.ugrPortalTimerInt.get()));
            if (commonConfig.ugrPortalTimerInt.get() == 0) {
                commonConfig.isUgrPortalEnabled.set(true);
                LOGGER.debug("Undergarden portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.ugrPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }
    @SubscribeEvent
    public static void portalAvancementMade(AdvancementEvent.AdvancementEarnEvent event) {

        if (event.getAdvancement().getId().toString().equals(commonConfig.ugrPortalAdvancement.get())) {
            commonConfig.isUgrPortalEnabled.set(true);
            LOGGER.debug("Undergarden portal is enabled advancement made" + event.getAdvancement().toString());
            event.getEntity().sendSystemMessage(Component.literal("Undergarden portal is now enabled!").withStyle(ChatFormatting.AQUA));
        }

    }
}
