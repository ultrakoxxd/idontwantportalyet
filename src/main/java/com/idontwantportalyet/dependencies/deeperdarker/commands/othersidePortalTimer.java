package com.idontwantportalyet.dependencies.deeperdarker.commands;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class othersidePortalTimer {
    public othersidePortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("otherside")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.othersidePortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isOthersidePortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.othersidePortalTimerInt.get();
                }))
        ));
    }
}
