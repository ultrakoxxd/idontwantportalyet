package com.idontwantportalyet.dependencies.drpg.events;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.logging.LogUtils;
import divinerpg.blocks.base.BlockModPortal;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

public class drpgEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static final int PORTAL_SCAN_RANGE = 10;


    @SubscribeEvent
    public static void deleteIceikaPortal(TickEvent.ServerTickEvent event) {


        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (!commonConfig.isIceikaPortalEnabled.get()) {
                for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
                    for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                        for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                            if (player.level.getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("divinerpg", "iceika_portal"))) {
                                player.level.setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                                LOGGER.debug("Iceika portal is disabled");
                            }
                        }
                    }
                }
            }
        }
    }


}
