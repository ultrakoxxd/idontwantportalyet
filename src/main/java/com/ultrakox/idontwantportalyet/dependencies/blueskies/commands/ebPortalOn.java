package com.ultrakox.idontwantportalyet.dependencies.blueskies.commands;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

public class ebPortalOn {
    public ebPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("everbright").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    commonConfig.isEverbrightPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    commonConfig.everbrightPortalTimerInt.set(-1);
                    commonConfig.SPEC.save();
                    if(commonConfig.isEverbrightPortalEnabled.get()) {
                        command.getSource().sendSystemMessage(Component.literal("Everbright portal is now enabled").withStyle(ChatFormatting.AQUA));
                    }else{
                        command.getSource().sendSystemMessage(Component.literal("Everbright portal is now disabled").withStyle(ChatFormatting.RED));
                    }
                    return isEnabled(command.getSource());
                }))
        )));
    }

    private int isEnabled(CommandSourceStack source) throws CommandSyntaxException {
        if(Boolean.valueOf(String.valueOf(commonConfig.isEverbrightPortalEnabled))){
            return 1;
        }else{
            return 0;
        }
    }
}
