package com.ultrakox.idontwantportalyet.dependencies.TF.commands;

import com.ultrakox.idontwantportalyet.config.common;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class tfPortalTimer {
    public tfPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("twilightforest")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    common.ugrPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    common.isTFPortalEnabled.set(false);
                    common.SPEC.save();

                    return common.ugrPortalTimerInt.get();
                }))
        ));
    }
}
