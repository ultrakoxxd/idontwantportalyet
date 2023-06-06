package com.ultrakox.idontwantportalyet.events;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.IDontWantPortalYet;
import com.ultrakox.idontwantportalyet.commands.endPortalOn;
import com.ultrakox.idontwantportalyet.commands.netherPortalOn;
import com.ultrakox.idontwantportalyet.config.common;
import com.ultrakox.idontwantportalyet.dependencies.TF.commands.TFportalOn;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.server.command.ConfigCommand;
import org.slf4j.Logger;

@Mod.EventBusSubscriber(modid = "idontwantportalyet", bus = Mod.EventBusSubscriber.Bus.FORGE)
public class modEvents {

    private static final Logger LOGGER = LogUtils.getLogger();
    @SubscribeEvent
    public static void deletePortal(BlockEvent.PortalSpawnEvent event){
        if(!common.isPortalEnabled.get()){
            event.setCanceled(true);
            LOGGER.info("Nether portal is disabled");

        } else  if(common.isPortalEnabled.get()) {
            event.setCanceled(false);
            LOGGER.info("Nether portal is enabled");
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
                    if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.ENDER_EYE) {
                        event.setCanceled(true);
                        LOGGER.info("End portal is disabled");
                    } else if (common.isEndPortalEnabled.get()) {
                        event.setCanceled(false);
                        LOGGER.info("End portal is enabled");
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void onCommandRegister(RegisterCommandsEvent event){
        new netherPortalOn(event.getDispatcher());
        new endPortalOn(event.getDispatcher());
        if(ModList.get().isLoaded("twilightforest")){
            new TFportalOn(event.getDispatcher());
        }
        ConfigCommand.register(event.getDispatcher());
    }

}
