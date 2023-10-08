package com.ultrakox.idontwantportalyet.events;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.commands.endPortalOn;
import com.ultrakox.idontwantportalyet.commands.endPortalTimer;
import com.ultrakox.idontwantportalyet.commands.netherPortalOn;
import com.ultrakox.idontwantportalyet.commands.netherPortalTimer;
import com.ultrakox.idontwantportalyet.config.common;
import com.ultrakox.idontwantportalyet.dependencies.TF.commands.TFportalOn;
import com.ultrakox.idontwantportalyet.dependencies.TF.commands.tfPortalTimer;
import com.ultrakox.idontwantportalyet.dependencies.blueskies.commands.ebPortalOn;
import com.ultrakox.idontwantportalyet.dependencies.blueskies.commands.ebPortalTimer;
import com.ultrakox.idontwantportalyet.dependencies.blueskies.commands.edPortalOn;
import com.ultrakox.idontwantportalyet.dependencies.blueskies.commands.edPortalTimer;
import com.ultrakox.idontwantportalyet.dependencies.undergarden.commands.ugrPortalOn;
import com.ultrakox.idontwantportalyet.dependencies.undergarden.commands.ugrPortalTimer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = "idontwantportalyet", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class modEvents {

    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int PORTAL_SCAN_RANGE = 10;
    @SubscribeEvent
    public static void deleteUgrPortal(TickEvent.WorldTickEvent event) {
        if (!common.isPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return; // Wykonuj tylko w fazie startowej ticku serwera

            for (Player player : event.world.players()) {
                removePortalBlocks(player);

            }
        }
    }

    private static void removePortalBlocks(Player player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level.getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == Blocks.NETHER_PORTAL) {
                        player.level.setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Nether portal is disabled");
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void deleteEndPortal(PlayerInteractEvent.RightClickBlock event) {

        Player player = (Player) event.getEntity();
        BlockHitResult hitResult = event.getHitVec();
        if (!common.isEndPortalEnabled.get()) {
            if (hitResult.getType() == BlockHitResult.Type.BLOCK) {
                BlockState blockState = player.level.getBlockState(hitResult.getBlockPos());
                if (blockState.getBlock() == Blocks.END_PORTAL_FRAME) {
                    event.setCanceled(true);
                    LOGGER.debug("End portal is disabled");
                } else if (common.isEndPortalEnabled.get()) {
                    LOGGER.debug("End portal is enabled");
                }
            }
        }
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.WorldTickEvent event){
        //end portal timer
        if(event.phase == TickEvent.Phase.START && common.endPortalTimerInt.get() >= 0){
            common.endPortalTimerInt.set(common.endPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(common.endPortalTimerInt.get()));
            if(common.endPortalTimerInt.get() == 0){
                common.isEndPortalEnabled.set(true);
                LOGGER.debug("end portal is now enabled!");
                for (Player player : event.world.players()) {
                    player.sendMessage(Component.nullToEmpty(common.endPortalTimerAfter.get()), player.getUUID());
                }
            }
        }
        //nether portal timer
        if(event.phase == TickEvent.Phase.START && common.netherPortalTimerInt.get() >= 0){
            common.netherPortalTimerInt.set(common.netherPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(common.netherPortalTimerInt.get()));
            if(common.netherPortalTimerInt.get() == 0){
                common.isPortalEnabled.set(true);
                LOGGER.debug("nether portal is now enabled!");
                for (Player player : event.world.players()) {
                    player.sendMessage(Component.nullToEmpty(common.netherPortalTimerAfter.get()), player.getUUID());
                }
            }
        }
    }
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event){
        new netherPortalOn(event.getDispatcher());
        new netherPortalTimer(event.getDispatcher());
        new endPortalOn(event.getDispatcher());
        new endPortalTimer(event.getDispatcher());

        if(ModList.get().isLoaded("twilightforest")){
            new TFportalOn(event.getDispatcher());
            new tfPortalTimer(event.getDispatcher());
        }
        if(ModList.get().isLoaded("blue_skies")){
            //everbright
            new ebPortalOn(event.getDispatcher());
            new ebPortalTimer(event.getDispatcher());
            //everdawn
            new edPortalOn(event.getDispatcher());
            new edPortalTimer(event.getDispatcher());
        }
        if (ModList.get().isLoaded("undergarden")){
            new ugrPortalOn(event.getDispatcher());
            new ugrPortalTimer(event.getDispatcher());
        }
        ConfigCommand.register(event.getDispatcher());
    }

}
