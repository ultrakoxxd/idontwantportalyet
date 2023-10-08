package com.ultrakox.idontwantportalyet.events;

import com.ultrakox.idontwantportalyet.commands.endPortalOn;
import com.ultrakox.idontwantportalyet.commands.endPortalTimer;
import com.ultrakox.idontwantportalyet.commands.netherPortalTimer;
import com.ultrakox.idontwantportalyet.config.commonConfig;

import com.ultrakox.idontwantportalyet.IDontWantPortalYet;
import com.ultrakox.idontwantportalyet.commands.portalOn;
import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.dependencies.deeperdarker.commands.othersidePortalOn;
import com.ultrakox.idontwantportalyet.dependencies.deeperdarker.commands.othersidePortalTimer;
import com.ultrakox.idontwantportalyet.dependencies.drpg.commands.*;
import com.ultrakox.idontwantportalyet.dependencies.tforest.commands.tfPortalOn;
import com.ultrakox.idontwantportalyet.dependencies.tforest.commands.tfPortalTimer;
import com.ultrakox.idontwantportalyet.dependencies.undergarden.commands.ugrPortalOn;
import com.ultrakox.idontwantportalyet.dependencies.undergarden.commands.ugrPortalTimer;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import org.slf4j.Logger;
import twilightforest.init.TFBlocks;

@Mod.EventBusSubscriber(modid = IDontWantPortalYet.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class modEvents {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int PORTAL_SCAN_RANGE = 10;
    @SubscribeEvent
    public static void deletePortal(TickEvent.ServerTickEvent event){
        if (!commonConfig.isPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return; // Wykonuj tylko w fazie startowej ticku serwera

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removePortalBlocks(player);

            }
        }
    }

    private static void removePortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == Blocks.NETHER_PORTAL) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Nether portal is disabled");
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void deleteEndPortal(PlayerInteractEvent.RightClickBlock event) {

        Player player = event.getEntity();
        BlockHitResult hitResult = event.getHitVec();
        if(!commonConfig.isEndPortalEnabled.get()) {
            if (hitResult.getType() == BlockHitResult.Type.BLOCK) {
                BlockState blockState = player.level().getBlockState(hitResult.getBlockPos());
                if (blockState.getBlock() == Blocks.END_PORTAL_FRAME) {
                        event.setCanceled(true);
                        LOGGER.info("End portal is disabled");
                    } else if(commonConfig.isEndPortalEnabled.get()){
                        event.setCanceled(false);
                        LOGGER.info("End portal is enabled");
                    }
                }
            }
        }
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event){
        //end portal timer
        if(event.phase == TickEvent.Phase.START && commonConfig.endPortalTimerInt.get() >= 0){
            commonConfig.endPortalTimerInt.set(commonConfig.endPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.endPortalTimerInt.get()));
            if(commonConfig.endPortalTimerInt.get() == 0){
                commonConfig.isEndPortalEnabled.set(true);
                LOGGER.debug("end portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.endPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
        //nether portal timer
        if(event.phase == TickEvent.Phase.START && commonConfig.netherPortalTimerInt.get() >= 0){
            commonConfig.netherPortalTimerInt.set(commonConfig.netherPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.netherPortalTimerInt.get()));
            if(commonConfig.netherPortalTimerInt.get() == 0){
                commonConfig.isPortalEnabled.set(true);
                LOGGER.debug("nether portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.netherPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }



    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event) {
        new portalOn(event.getDispatcher());
        new endPortalOn(event.getDispatcher());
        new endPortalTimer(event.getDispatcher());
        new netherPortalTimer(event.getDispatcher());
        if (ModList.get().isLoaded("deeperdarker")) {
            new othersidePortalOn(event.getDispatcher());
            new othersidePortalTimer(event.getDispatcher());
        }
        if (ModList.get().isLoaded("twilightforest")) {
            new tfPortalOn(event.getDispatcher());
            new tfPortalTimer(event.getDispatcher());
        }
        if (ModList.get().isLoaded("blue_skies")) {
            //everbright
            //   new ebPortalOn(event.getDispatcher());
            //  new ebPortalTimer(event.getDispatcher());
            //everdawn
            //  new edPortalOn(event.getDispatcher());
            //   new edPortalTimer(event.getDispatcher());
        }
        if (ModList.get().isLoaded("divinerpg")) {
            //apalachia
            new apalachiaPortalOn(event.getDispatcher());
            new apalachiaPortalTimer(event.getDispatcher());
            //eden
            new edenPortalOn(event.getDispatcher());
            new edenPortalTimer(event.getDispatcher());
            //iceika
            new iceikaPortalOn(event.getDispatcher());
            new iceikaPortalTimer(event.getDispatcher());
            //mortum
            new mortumPortalOn(event.getDispatcher());
            new mortumPortalTimer(event.getDispatcher());
            //skythern
            new skythernPortalOn(event.getDispatcher());
            new skythernPortalTimer(event.getDispatcher());
            //vethea
            new vetheaPortalOn(event.getDispatcher());
            new vetheaPortalTimer(event.getDispatcher());
            //wildwood
            new wildwoodPortalOn(event.getDispatcher());
            new wildwoodPortalTimer(event.getDispatcher());

        if(ModList.get().isLoaded("undergarden")){
            new ugrPortalOn(event.getDispatcher());
            new ugrPortalTimer(event.getDispatcher());
        }
            ConfigCommand.register(event.getDispatcher());
        }

    }
}
