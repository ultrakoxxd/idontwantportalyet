package com.ultrakox.idontwantportalyet.dependencies.aether.commands;

import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class aetherPortalTimer {
    public aetherPortalTimer(CommandDispatcher<CommandSourceStack> dispatcher){
    dispatcher.register(Commands.literal("portaltimer").requires((source) -> source.hasPermission(2)).then(Commands.literal("aether")
            .then(Commands.argument("time", IntegerArgumentType.integer()).executes((command) -> {
                commonConfig.aetherPortalTimerInt.set(IntegerArgumentType.getInteger(command, "time"));
                commonConfig.isAetherPortalEnabled.set(false);
                commonConfig.SPEC.save();

                return commonConfig.aetherPortalTimerInt.get();
            }))
    ));
}

}
