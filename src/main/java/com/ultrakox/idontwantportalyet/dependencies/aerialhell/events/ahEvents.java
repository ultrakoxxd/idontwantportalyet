package com.ultrakox.idontwantportalyet.dependencies.aerialhell.events;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import fr.factionbedrock.aerialhell.Registry.AerialHellBlocksAndItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;

public class ahEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int PORTAL_SCAN_RANGE = 10;


    @SubscribeEvent
    public static void deleteAetherPortal(TickEvent.ServerTickEvent event) {
        if (!commonConfig.isAHPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return;

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeAHPortalBlocks(player);

            }
        }
    }

    private static void removeAHPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == AerialHellBlocksAndItems.AERIAL_HELL_PORTAL.get()) {
                        player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                        LOGGER.debug("Aerial Hell portal is disabled");
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
        if (event.phase == TickEvent.Phase.START && commonConfig.ahPortalTimerInt.get() >= 0) {
            commonConfig.ahPortalTimerInt.set(commonConfig.ahPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.aetherPortalTimerInt.get()));
            if (commonConfig.ahPortalTimerInt.get() == 0) {
                commonConfig.isAHPortalEnabled.set(true);
                LOGGER.debug("Aerial hell portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.ahPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
    }
    @SubscribeEvent
    public static void portalAvancementMade(AdvancementEvent.AdvancementEarnEvent event) {

        if (event.getAdvancement().getId().toString().equals(commonConfig.ahPortalAdvancement.get())) {
            commonConfig.isAHPortalEnabled.set(true);
            LOGGER.debug("Aerial hell portal is enabled advancement made" + event.getAdvancement().toString());
            event.getEntity().sendSystemMessage(Component.literal("Aerial Hell portal is now enabled!").withStyle(ChatFormatting.AQUA));
        }

    }
}
