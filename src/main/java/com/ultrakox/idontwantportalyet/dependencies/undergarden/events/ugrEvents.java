package com.ultrakox.idontwantportalyet.dependencies.undergarden.events;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.config.common;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;
import quek.undergarden.block.UndergardenPortalBlock;


public class ugrEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void deleteUgrPortal(UndergardenPortalBlock.PortalSpawnEvent event) {
        if(!common.isUgrPortalEnabled.get()){
            event.setCanceled(true);
            LOGGER.debug("Undergarden portal is disabled");

        } else  if(common.isUgrPortalEnabled.get()) {
            event.setCanceled(false);
            LOGGER.debug("Undergarden portal is enabled");
        }
    }



    //
    // TIMER
    //
    @SubscribeEvent
    public static void onServerTick(TickEvent.WorldTickEvent event) {
        if (event.phase == TickEvent.Phase.START && common.ugrPortalTimerInt.get() >= 0) {
            common.ugrPortalTimerInt.set(common.ugrPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(common.ugrPortalTimerInt.get()));
            if (common.ugrPortalTimerInt.get() == 0) {
                common.isUgrPortalEnabled.set(true);
                LOGGER.debug("ugr portal is now enabled!");
                for (Player player : event.world.players()) {
                    player.sendMessage(Component.nullToEmpty(common.ugrPortalTimerAfter.get()), player.getUUID());
                }
            }
        }
    }
}
