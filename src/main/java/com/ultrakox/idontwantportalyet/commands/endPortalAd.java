package com.ultrakox.idontwantportalyet.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.Advancement;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;

public class endPortalAd {

    public endPortalAd(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("portaladvancement")
                .requires((source) -> source.hasPermission(2))
                .then(Commands.literal("end")
                        .then(Commands.argument("advancement", ResourceLocationArgument.id())
                                .executes((command) -> {
                                    ResourceLocation advancementId = ResourceLocationArgument.getId(command, "advancement");
                                    ServerPlayer player = command.getSource().getPlayerOrException();
                                    Advancement advancement = findAdvancement(player, advancementId);
                                    if (advancement != null) {
                                        commonConfig.isEndPortalEnabled.set(false);
                                        commonConfig.endPortalAdvancement.set(advancementId.toString());
                                        commonConfig.SPEC.save();
                                        command.getSource().sendSystemMessage(Component.literal("End portal will be enabled after getting advancement: " + advancementId).withStyle(ChatFormatting.AQUA));
                                        return 1;
                                    } else {
                                        command.getSource().sendFailure(Component.literal("Advancement '" + advancementId + "' not found!"));
                                        return 0;
                                    }
                                }))
                )
        );
    }

    public static Advancement findAdvancement(ServerPlayer player, ResourceLocation advancementId) {
        for (Advancement advancement : player.getServer().getAdvancements().getAllAdvancements()) {
            if (advancement.getId().equals(advancementId)) {
                return advancement;
            }
        }
        return null;
    }
}