package com.idontwantportalyet.dependencies.drpg.commands;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class edenPortalTimer {
    public edenPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("eden")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.edenPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isEdenPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.edenPortalTimerInt.get();
                }))
        ));
    }
}
