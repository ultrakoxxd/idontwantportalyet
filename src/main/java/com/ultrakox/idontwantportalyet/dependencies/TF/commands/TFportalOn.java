package com.ultrakox.idontwantportalyet.dependencies.TF.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.ultrakox.idontwantportalyet.config.common;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class TFportalOn {
    public TFportalOn(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("twilight").then(Commands.literal("enabled")
                .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                    common.isTFPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                    common.SPEC.save();
                    return isEnabled(command.getSource());
                }))
        )));
    }

    private int isEnabled(CommandSourceStack source) throws CommandSyntaxException {
        if(Boolean.valueOf(String.valueOf(common.isTFPortalEnabled)) == true){
            return 1;
        }else{
            return 0;
        }
    }
}
