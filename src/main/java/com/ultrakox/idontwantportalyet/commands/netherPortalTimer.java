package com.ultrakox.idontwantportalyet.commands;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class netherPortalTimer {
    public netherPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
    dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("nether")
            .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                commonConfig.netherPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                commonConfig.isPortalEnabled.set(false);
                commonConfig.SPEC.save();
                return commonConfig.netherPortalTimerInt.get();
            }))
    ));
    }
}
