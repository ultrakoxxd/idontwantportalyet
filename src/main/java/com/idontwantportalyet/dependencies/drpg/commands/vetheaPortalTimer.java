package com.idontwantportalyet.dependencies.drpg.commands;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class vetheaPortalTimer {
    public vetheaPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("vethea")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.vetheaPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isVetheaPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.vetheaPortalTimerInt.get();
                }))
        ));
    }
}

