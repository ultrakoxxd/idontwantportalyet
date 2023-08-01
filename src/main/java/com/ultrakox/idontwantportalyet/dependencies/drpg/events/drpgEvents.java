package com.ultrakox.idontwantportalyet.dependencies.drpg.events;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.logging.LogUtils;
import divinerpg.blocks.base.BlockModPortal;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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
    public static void deleteApalachiaPortal(TickEvent.ServerTickEvent event) {


        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (!commonConfig.isApalachiaPortalEnabled.get()) {
                for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
                    for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                        for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                            if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("divinerpg", "apalachia_portal"))) {
                                player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                                LOGGER.debug("Apalachia portal is disabled");
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void deleteEdenPortal(TickEvent.ServerTickEvent event) {


        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (!commonConfig.isEdenPortalEnabled.get()) {
                for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
                    for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                        for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                            if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("divinerpg", "eden_portal"))) {
                                player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                                LOGGER.debug("Eden portal is disabled");
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void deleteIceikaPortal(TickEvent.ServerTickEvent event) {


        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (!commonConfig.isIceikaPortalEnabled.get()) {
                for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
                    for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                        for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                            if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("divinerpg", "iceika_portal"))) {
                                player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                                LOGGER.debug("Iceika portal is disabled");
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void deleteMortumPortal(TickEvent.ServerTickEvent event) {


        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (!commonConfig.isMortumPortalEnabled.get()) {
                for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
                    for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                        for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                            if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("divinerpg", "mortum_portal"))) {
                                player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                                LOGGER.debug("Mortum portal is disabled");
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void deleteSkythernPortal(TickEvent.ServerTickEvent event) {


        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (!commonConfig.isSkythernPortalEnabled.get()) {
                for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
                    for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                        for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                            if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("divinerpg", "skythern_portal"))) {
                                player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                                LOGGER.debug("Skythern portal is disabled");
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void deleteVetheaPortal(TickEvent.ServerTickEvent event) {


        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (!commonConfig.isVetheaPortalEnabled.get()) {
                for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
                    for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                        for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                            if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("divinerpg", "vethea_portal"))) {
                                player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                                LOGGER.debug("Vethea portal is disabled");
                            }
                        }
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void deleteWildwoodPortal(TickEvent.ServerTickEvent event) {


        for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
            if (!commonConfig.isWildwoodPortalEnabled.get()) {
                for (int dx = -PORTAL_SCAN_RANGE; dx <= PORTAL_SCAN_RANGE; dx++) {
                    for (int dy = -PORTAL_SCAN_RANGE; dy <= PORTAL_SCAN_RANGE; dy++) {
                        for (int dz = -PORTAL_SCAN_RANGE; dz <= PORTAL_SCAN_RANGE; dz++) {
                            if (player.level().getBlockState(player.blockPosition().offset(dx, dy, dz)).getBlock() == ForgeRegistries.BLOCKS.getValue(new ResourceLocation("divinerpg", "wildwood_portal"))) {
                                player.level().setBlockAndUpdate(player.blockPosition().offset(dx, dy, dz), Blocks.AIR.defaultBlockState());
                                LOGGER.debug("Wildwood portal is disabled");
                            }
                        }
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
        //apalachia portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.apalachiaPortalTimerInt.get() >= 0) {
            commonConfig.apalachiaPortalTimerInt.set(commonConfig.apalachiaPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.apalachiaPortalTimerInt.get()));
            if (commonConfig.apalachiaPortalTimerInt.get() == 0) {
                commonConfig.isApalachiaPortalEnabled.set(true);
                LOGGER.debug("Apalachia portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.apalachiaPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
        //eden portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.edenPortalTimerInt.get() >= 0) {
            commonConfig.edenPortalTimerInt.set(commonConfig.edenPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.edenPortalTimerInt.get()));
            if (commonConfig.edenPortalTimerInt.get() == 0) {
                commonConfig.isEdenPortalEnabled.set(true);
                LOGGER.debug("Eden portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.edenPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
        //iceika portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.iceikaPortalTimerInt.get() >= 0) {
            commonConfig.iceikaPortalTimerInt.set(commonConfig.iceikaPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.iceikaPortalTimerInt.get()));
            if (commonConfig.iceikaPortalTimerInt.get() == 0) {
                commonConfig.isIceikaPortalEnabled.set(true);
                LOGGER.debug("Iceika portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.iceikaPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
        //mortum portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.mortumPortalTimerInt.get() >= 0) {
            commonConfig.mortumPortalTimerInt.set(commonConfig.mortumPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.mortumPortalTimerInt.get()));
            if (commonConfig.mortumPortalTimerInt.get() == 0) {
                commonConfig.isMortumPortalEnabled.set(true);
                LOGGER.debug("Mortum portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.mortumPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
        //skythern portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.skythernPortalTimerInt.get() >= 0) {
            commonConfig.skythernPortalTimerInt.set(commonConfig.skythernPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.skythernPortalTimerInt.get()));
            if (commonConfig.skythernPortalTimerInt.get() == 0) {
                commonConfig.isSkythernPortalEnabled.set(true);
                LOGGER.debug("Skythern portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.skythernPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
        //vethea portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.vetheaPortalTimerInt.get() >= 0) {
            commonConfig.vetheaPortalTimerInt.set(commonConfig.vetheaPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.vetheaPortalTimerInt.get()));
            if (commonConfig.vetheaPortalTimerInt.get() == 0) {
                commonConfig.isVetheaPortalEnabled.set(true);
                LOGGER.debug("Vethea portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.vetheaPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }
        //wildwood portal timer
        if (event.phase == TickEvent.Phase.START && commonConfig.wildwoodPortalTimerInt.get() >= 0) {
            commonConfig.wildwoodPortalTimerInt.set(commonConfig.wildwoodPortalTimerInt.get() - 1);
            LOGGER.debug(String.valueOf(commonConfig.wildwoodPortalTimerInt.get()));
            if (commonConfig.wildwoodPortalTimerInt.get() == 0) {
                commonConfig.isWildwoodPortalEnabled.set(true);
                LOGGER.debug("Wildwood portal is now enabled!");
                for (ServerPlayer player : event.getServer().getPlayerList().getPlayers()) {
                    player.sendSystemMessage(Component.literal(commonConfig.wildwoodPortalTimerAfter.get()).withStyle(ChatFormatting.AQUA));
                }
            }
        }


    }

}
