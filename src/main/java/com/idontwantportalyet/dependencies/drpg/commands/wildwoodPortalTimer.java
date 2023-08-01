package com.idontwantportalyet.dependencies.drpg.commands;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class wildwoodPortalTimer {
    public wildwoodPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("wildwood")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.wildwoodPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isWildwoodPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.wildwoodPortalTimerInt.get();
                }))
        ));
    }
}
