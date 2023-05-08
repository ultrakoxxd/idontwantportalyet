package com.idontwantportalyet.commands;

import com.idontwantportalyet.events.modEvents;
import com.idontwantportalyet.config.commonConfig;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.sun.jdi.connect.Connector;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class portalOn {
    public portalOn(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portal").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    modEvents.isPortalEnabled = (BoolArgumentType.getBool(command, "state"));
                    commonConfig.isPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    commonConfig.SPEC.save();
                    return isEnabled(command.getSource());
                }))
        ));
    }

    private int isEnabled(CommandSourceStack source) throws CommandSyntaxException {
        if(modEvents.isPortalEnabled == true){
            return 1;
        }else{
            return 0;
        }
    }
}
