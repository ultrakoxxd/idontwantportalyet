package com.ultrakox.idontwantportalyet.dependencies.blueskies.commands;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ebPortalTimer {
    public ebPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("everbright")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.everbrightPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isEverbrightPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.everbrightPortalTimerInt.get();
                }))
        ));
    }
}
