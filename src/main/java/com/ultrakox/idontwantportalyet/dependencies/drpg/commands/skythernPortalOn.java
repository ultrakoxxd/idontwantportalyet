package com.ultrakox.idontwantportalyet.dependencies.drpg.commands;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class skythernPortalOn {
    public skythernPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("skythern").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    commonConfig.isSkythernPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    commonConfig.SPEC.save();
                    if(commonConfig.isSkythernPortalEnabled.get()) {
                        command.getSource().sendSystemMessage(Component.literal("Skythern portal is now enabled").withStyle(ChatFormatting.AQUA));
                    }else{
                        command.getSource().sendSystemMessage(Component.literal("Skythern portal is now disabled").withStyle(ChatFormatting.RED));
                    }
                    return isEnabled(command.getSource());
                }))
        )));
    }

    private int isEnabled(CommandSourceStack source) throws CommandSyntaxException {
        if(Boolean.valueOf(String.valueOf(commonConfig.isSkythernPortalEnabled))){
            return 1;
        }else{
            return 0;
        }
    }
}
