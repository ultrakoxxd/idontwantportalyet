package com.ultrakox.idontwantportalyet.dependencies.aerialhell.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ahPortalTimer {
    public ahPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("aerialhell")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.ahPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isAHPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.ahPortalTimerInt.get();
                }))
        ));
    }

}
