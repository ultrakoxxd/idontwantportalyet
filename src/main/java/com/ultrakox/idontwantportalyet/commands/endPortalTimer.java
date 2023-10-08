package com.ultrakox.idontwantportalyet.commands;

import com.ultrakox.idontwantportalyet.config.common;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class endPortalTimer {
    public endPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("end")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                     common.endPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                     common.isEndPortalEnabled.set(false);
                     common.SPEC.save();

                    return  common.endPortalTimerInt.get();
                }))
        ));
    }


}
