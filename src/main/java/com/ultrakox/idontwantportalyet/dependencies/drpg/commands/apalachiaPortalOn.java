package com.ultrakox.idontwantportalyet.dependencies.drpg.commands;


import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class apalachiaPortalOn {
    public apalachiaPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("apalachia").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    commonConfig.isApalachiaPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    commonConfig.SPEC.save();
                    return isEnabled(command.getSource());
                }))
        )));
    }

    private int isEnabled(CommandSourceStack source) throws CommandSyntaxException {
        if(Boolean.valueOf(String.valueOf(commonConfig.isApalachiaPortalEnabled))){
            return 1;
        }else{
            return 0;
        }
    }
}
