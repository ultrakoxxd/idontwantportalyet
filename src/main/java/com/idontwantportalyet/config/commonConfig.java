package com.idontwantportalyet.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class commonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> isPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEndPortalEnabled;


    static {
        BUILDER.push("Configs");
        isPortalEnabled = BUILDER.comment().define("Is portal enabled", true);
        isEndPortalEnabled = BUILDER.comment().define("Is End portal enabled", true);

        BUILDER.pop();
        SPEC = BUILDER.build();

    }


}

