package com.ultrakox.idontwantportalyet.dependencies.undergarden.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.ultrakox.idontwantportalyet.config.common;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ugrPortalOn {
    public ugrPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("undergarden").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    common.isUgrPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    common.SPEC.save();
                    return isEnabled(command.getSource());
                }))
        )));
    }

    private int isEnabled(CommandSourceStack ignoredSource){
        if(Boolean.parseBoolean(String.valueOf(common.isUgrPortalEnabled))){
            return 1;
        }else{
            return 0;
        }
    }
}
