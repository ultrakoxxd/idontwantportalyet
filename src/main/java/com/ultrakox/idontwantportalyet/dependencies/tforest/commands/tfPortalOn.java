package com.ultrakox.idontwantportalyet.dependencies.tforest.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class tfPortalOn {
    public tfPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
    dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("twilight").then(Commands.literal("enabled")
            .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                commonConfig.isTFPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                commonConfig.SPEC.save();
                if(commonConfig.isTFPortalEnabled.get()) {
                    command.getSource().sendSystemMessage(Component.literal("Twilight portal is now enabled").withStyle(ChatFormatting.AQUA));
                }else{
                    command.getSource().sendSystemMessage(Component.literal("Twilight portal is now disabled").withStyle(ChatFormatting.RED));
                }
                return isEnabled(command.getSource());
            }))
    )));
}

    private int isEnabled(CommandSourceStack source) throws CommandSyntaxException {
        if(Boolean.valueOf(String.valueOf(commonConfig.isTFPortalEnabled)) == true){
            return 1;
        }else{
            return 0;
        }
    }
}
