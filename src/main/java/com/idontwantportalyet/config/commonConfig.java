package com.idontwantportalyet.config;

import net.minecraftforge.common.ForgeConfigSpec;

import com.idontwantportalyet.events.modEvents;

public class commonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> isPortalEnabled;

    static {
        BUILDER.push("Configs");
            isPortalEnabled = BUILDER.comment().define("Is portal enabled", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }


}

