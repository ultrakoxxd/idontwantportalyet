package com.idontwantportalyet.dependencies.deeperdarker.events;

import com.idontwantportalyet.config.commonConfig;

import com.idontwantportalyet.IDontWantPortalYet;
import com.kyanite.deeperdarker.forge.OthersidePortalBlock;
import com.mojang.logging.LogUtils;
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

}
