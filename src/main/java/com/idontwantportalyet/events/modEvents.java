package com.idontwantportalyet.events;

import com.idontwantportalyet.IDontWantPortalYet;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = IDontWantPortalYet.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class modEvents {
    @SubscribeEvent
    public static void deletePortal(BlockEvent.PortalSpawnEvent event){
        event.setCanceled(true);
    }
}
