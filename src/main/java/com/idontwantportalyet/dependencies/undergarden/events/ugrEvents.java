package com.idontwantportalyet.dependencies.undergarden.events;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;
import quek.undergarden.block.UndergardenPortalBlock;

public class ugrEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void deleteUgrPortal(UndergardenPortalBlock.PortalSpawnEvent event){
        if (!commonConfig.isUgrPortalEnabled.get()) {
            event.setCanceled(true);
            LOGGER.debug("Undergarden portal is disabled");
        } else if (commonConfig.isUgrPortalEnabled.get()) {
            event.setCanceled(false);
            LOGGER.debug("Undergarden portal is enabled");
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
}
