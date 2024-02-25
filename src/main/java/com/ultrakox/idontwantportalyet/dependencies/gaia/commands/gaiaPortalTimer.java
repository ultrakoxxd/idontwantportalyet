package com.ultrakox.idontwantportalyet.dependencies.gaia.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class gaiaPortalTimer {
    public gaiaPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("gaia")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.gaiaPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isGaiaPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.gaiaPortalTimerInt.get();
                }))
        ));
    }
}
