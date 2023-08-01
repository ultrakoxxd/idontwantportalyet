package com.ultrakox.idontwantportalyet.dependencies.drpg.commands;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class skythernPortalTimer {
    public skythernPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("skythern")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.skythernPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isSkythernPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.skythernPortalTimerInt.get();
                }))
        ));
    }
}
