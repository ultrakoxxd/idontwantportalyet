package com.idontwantportalyet.events;

import com.idontwantportalyet.commands.endPortalOn;
import com.idontwantportalyet.commands.othersidePortalOn;
import com.idontwantportalyet.config.commonConfig;

import com.idontwantportalyet.IDontWantPortalYet;
import com.idontwantportalyet.commands.portalOn;
import com.kyanite.deeperdarker.forge.OthersidePortalBlock;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import com.kyanite.deeperdarker.DeeperAndDarker;

@Mod.EventBusSubscriber(modid = IDontWantPortalYet.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class modEvents {
    public static Boolean isPortalEnabled;


    @SubscribeEvent
    public static void deletePortal(BlockEvent.PortalSpawnEvent event){
        if(!commonConfig.isPortalEnabled.get()){
            event.setCanceled(true);
            System.out.println("Nether portal is disabled");
        } else  if(commonConfig.isPortalEnabled.get()) {
            event.setCanceled(false);
            System.out.println("Nether portal is enabled");
        }
    }
    @SubscribeEvent
    public static void deleteEndPortal(PlayerInteractEvent.RightClickBlock event) {

        Player player = event.getEntity();
        BlockHitResult hitResult = event.getHitVec();
       if(!commonConfig.isEndPortalEnabled.get()) {
           if (hitResult.getType() == BlockHitResult.Type.BLOCK) {
               BlockState blockState = player.level.getBlockState(hitResult.getBlockPos());
               if (blockState.getBlock() == Blocks.END_PORTAL_FRAME) {
                   if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.ENDER_EYE) {
                       event.setCanceled(true);
                   }
               }
           }
       } else if(commonConfig.isEndPortalEnabled.get()){
            event.setCanceled(false);
       }
    }

    public static void deleteOthersidePortal(OthersidePortalBlock.PortalSpawnEvent event){
        if(!commonConfig.isOthersidePortalEnabled.get()){
            event.setCanceled(true);
            System.out.println("Otherside portal is disabled");
        } else if (commonConfig.isOthersidePortalEnabled.get()) {
            event.setCanceled(false);
            System.out.println("Otherside portal is enabled");
        }
    }


    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event){
        new portalOn(event.getDispatcher());
        new endPortalOn(event.getDispatcher());
        new othersidePortalOn(event.getDispatcher());

        ConfigCommand.register(event.getDispatcher());
    }


}
