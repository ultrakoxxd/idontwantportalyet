package com.ultrakox.idontwantportalyet.dependencies.drpg.commands;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class iceikaPortalTimer {
    public iceikaPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("iceika")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.iceikaPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isIceikaPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.iceikaPortalTimerInt.get();
                }))
        ));
    }
}
