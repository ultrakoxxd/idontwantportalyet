package com.idontwantportalyet.dependencies.drpg.commands;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class mortumPortalTimer {
    public mortumPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("mortum")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.mortumPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isMortumPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.mortumPortalTimerInt.get();
                }))
        ));
    }
}

