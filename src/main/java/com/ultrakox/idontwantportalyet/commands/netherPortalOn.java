package com.ultrakox.idontwantportalyet.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.ultrakox.idontwantportalyet.config.common;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class netherPortalOn {
    public netherPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("nether").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    common.isPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    common.SPEC.save();
                    return isEnabled(command.getSource());
                }))
        )));
    }

    private int isEnabled(CommandSourceStack ignoredSource){
        if(Boolean.valueOf(String.valueOf(common.isPortalEnabled))){
            return 1;
        }else{
            return 0;
        }
    }
}
