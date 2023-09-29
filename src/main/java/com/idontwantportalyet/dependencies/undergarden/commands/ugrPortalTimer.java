package com.idontwantportalyet.dependencies.undergarden.commands;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ugrPortalTimer {
    public ugrPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("undergarden")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.ugrPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isUgrPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.ugrPortalTimerInt.get();
                }))
        ));
    }
}
