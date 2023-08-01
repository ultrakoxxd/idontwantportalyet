package com.idontwantportalyet.dependencies.deeperdarker.events;

import com.idontwantportalyet.config.commonConfig;

import com.idontwantportalyet.IDontWantPortalYet;
import com.kyanite.deeperdarker.forge.OthersidePortalBlock;
import com.mojang.logging.LogUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import org.slf4j.Logger;
import com.kyanite.deeperdarker.DeeperAndDarker;


public class ddEvents {

    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public static void deleteOthersidePortal(OthersidePortalBlock.PortalSpawnEvent event){
            if (!commonConfig.isOthersidePortalEnabled.get()) {
                event.setCanceled(true);
                LOGGER.info("Otherside portal is disabled");
            } else if (commonConfig.isOthersidePortalEnabled.get()) {
                event.setCanceled(false);
                LOGGER.info("Otherside portal is enabled");
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

}
