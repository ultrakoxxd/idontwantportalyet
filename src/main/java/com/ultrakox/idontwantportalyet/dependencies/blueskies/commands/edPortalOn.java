package com.ultrakox.idontwantportalyet.dependencies.blueskies.commands;

import com.ultrakox.idontwantportalyet.config.common;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class edPortalOn {
    public edPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("everdawn").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    common.isEverdawnPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    common.SPEC.save();
                    return isEnabled(command.getSource());
                }))
        )));
    }

    private int isEnabled(CommandSourceStack ignoredSource){
        if(Boolean.parseBoolean(String.valueOf(common.isEverdawnPortalEnabled))){
            return 1;
        }else{
            return 0;
        }
    }

}
