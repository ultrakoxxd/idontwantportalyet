package com.idontwantportalyet.dependencies.twilightforest.events;

import com.idontwantportalyet.IDontWantPortalYet;
import com.idontwantportalyet.config.commonConfig;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.item.ItemEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;
import twilightforest.block.TFPortalBlock;
import twilightforest.init.TFBlocks;
import twilightforest.world.registration.TFGenerationSettings;


public class tfEvents {
    private static final Logger LOGGER = LogUtils.getLogger();


    private static final int PORTAL_SCAN_RANGE = 10;


    @SubscribeEvent
    public static void deleteTFPortal(TickEvent.ServerTickEvent event) {
        if (!commonConfig.isTFPortalEnabled.get()) {
            if (event.phase != TickEvent.Phase.START) return; // Wykonuj tylko w fazie startowej ticku serwera

            for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                removeTwilightPortalBlocks(player);
                LOGGER.info("Twilight portal is disabled");
            }
        }
    }

    private static void removeTwilightPortalBlocks(ServerPlayer player) {
        for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
            for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                    if (player.level.getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == TFBlocks.TWILIGHT_PORTAL.get()) {
                        player.level.setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                    }
                }
            }
        }
    }
}