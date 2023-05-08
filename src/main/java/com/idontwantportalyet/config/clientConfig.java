package com.idontwantportalyet.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class clientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    static {
        BUILDER.push("Configs");

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
