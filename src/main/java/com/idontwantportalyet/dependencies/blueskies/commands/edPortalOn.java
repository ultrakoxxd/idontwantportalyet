package com.idontwantportalyet.dependencies.blueskies.commands;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class edPortalOn {
    public edPortalOn(CommandDispatcher<CommandSourceStack> dispatcher){
    dispatcher.register(Commands.literal("portal").requires((source) -> source.hasPermission(2)).then(Commands.literal("everdawn").then(Commands.literal("enabled")
            .then(Commands.argument("state", BoolArgumentType.bool()).executes((command) -> {
                commonConfig.isEverdawnPortalEnabled.set(BoolArgumentType.getBool(command, "state"));
                commonConfig.SPEC.save();
                return isEnabled(command.getSource());
            }))
    )));
}

    private int isEnabled(CommandSourceStack source) throws CommandSyntaxException {
        if(Boolean.valueOf(String.valueOf(commonConfig.isEverdawnPortalEnabled)) == true){
            return 1;
        }else{
            return 0;
        }
    }

}
