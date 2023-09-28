package com.ultrakox.idontwantportalyet.dependencies.blueskies.commands;

import com.ultrakox.idontwantportalyet.config.common;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class ebPortalTimer {
    public ebPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("everbright")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    common.everbrightPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    common.isEverbrightPortalEnabled.set(false);
                    common.SPEC.save();

                    return common.everbrightPortalTimerInt.get();
                }))
        ));
    }
}
