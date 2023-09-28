package com.ultrakox.idontwantportalyet.dependencies.blueskies.commands;

import com.ultrakox.idontwantportalyet.config.common;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class edPortalTimer {
    public edPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("everdawn")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    common.everdawnPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    common.isEverdawnPortalEnabled.set(false);
                    common.SPEC.save();

                    return common.everdawnPortalTimerInt.get();
                }))
        ));
    }
}
