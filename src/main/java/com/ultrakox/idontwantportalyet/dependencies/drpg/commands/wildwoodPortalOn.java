package com.ultrakox.idontwantportalyet.dependencies.drpg.commands;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class wildwoodPortalOn {
    public wildwoodPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
    dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("wildwood").then(Commands.literal("enabled")
            .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                commonConfig.isWildwoodPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                commonConfig.SPEC.save();
                return isEnabled(command.getSource());
            }))
    )));
}

    private int isEnabled(CommandSourceStack source) throws CommandSyntaxException {
        if(Boolean.valueOf(String.valueOf(commonConfig.isWildwoodPortalEnabled))){
            return 1;
        }else{
            return 0;
        }
    }
}
