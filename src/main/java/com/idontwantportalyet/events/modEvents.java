package com.idontwantportalyet.events;

import com.idontwantportalyet.config.commonConfig;

import com.idontwantportalyet.IDontWantPortalYet;
import com.idontwantportalyet.commands.portalOn;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;

@Mod.EventBusSubscriber(modid = IDontWantPortalYet.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class modEvents {
    public static Boolean isPortalEnabled;


    @SubscribeEvent
    public static void deletePortal(BlockEvent.PortalSpawnEvent event){
        if(!commonConfig.isPortalEnabled.get()){
            event.setCanceled(true);
            System.out.println(commonConfig.isPortalEnabled.get());
        }
    }

    @SubscribeEvent
    public static void returnPortal(BlockEvent.PortalSpawnEvent event){
        if(commonConfig.isPortalEnabled.get()) {
            event.setCanceled(false);
            System.out.println(commonConfig.isPortalEnabled.get());
        }
    }


    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event){
        new portalOn(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }


}
