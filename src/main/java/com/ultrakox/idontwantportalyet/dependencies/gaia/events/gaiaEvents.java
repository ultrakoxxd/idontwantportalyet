package com.ultrakox.idontwantportalyet.dependencies.gaia.events;

import androsa.gaiadimension.registry.registration.ModBlocks;
import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;

public class gaiaEvents {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final int PORTAL_SCAN_RANGE = 10;


    @SubscribeEvent
    public static void deleteGaiaPortal(TickEvent.ServerTickEvent event) {
        if (!commonConfig.isGaiaPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return; // Wykonuj tylko w fazie startowej ticku serwera

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeGaiaPortalBlocks(player);

            }
        }
    }

    private static void removeGaiaPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock().equals(ModBlocks.gaia_portal.get())) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Gaia portal is disabled");
                        player.sendSystemMessage(Component.literal("This portal is disabled").withStyle(ChatFormatting.DARK_RED));
                    }
                }
            }
        }
    }
    //
    // TIMER
    //
    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        //ec portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.gaiaPortalTimerInt.get() >= 0) {
            commonConfig.gaiaPortalTimerInt.set(commonConfig.gaiaPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.gaiaPortalTimerInt.get()));
            if (commonConfig.gaiaPortalTimerInt.get() == 0) {
                commonConfig.isGaiaPortalEnabled.set(true);
                LOGGER.debug("gaia portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.gaiaPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }
    @SubscribeEvent
    public static void portalAvancementMade(AdvancementEvent.AdvancementEarnEvent event) {

        if (event.getAdvancement().getId().toString().equals(commonConfig.gaiaPortalAdvancement.get())) {
            commonConfig.isGaiaPortalEnabled.set(true);
            LOGGER.debug("Gaia portal is enabled advancement made" + event.getAdvancement().toString());
            event.getEntity().sendSystemMessage(Component.literal("Gaia portal is now enabled!").withStyle(ChatFormatting.AQUA));
        }

    }
}

