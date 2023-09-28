package com.ultrakox.idontwantportalyet.commands;

import com.ultrakox.idontwantportalyet.config.common;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class netherPortalTimer {
    public netherPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("nether")
                .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                    common.netherPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                    common.isPortalEnabled.set(false);
                    common.SPEC.save();
                    return common.netherPortalTimerInt.get();
                }))
        ));
    }
}
