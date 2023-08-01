package com.idontwantportalyet.dependencies.twilightforest.commands;

import com.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class tfPortalTimer {
    public tfPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("twilightforest")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    commonConfig.TFPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    commonConfig.isTFPortalEnabled.set(false);
                    commonConfig.SPEC.save();

                    return commonConfig.TFPortalTimerInt.get();
                }))
        ));
    }
}
