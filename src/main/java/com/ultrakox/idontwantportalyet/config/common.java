package com.ultrakox.idontwantportalyet.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class common {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> isPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEndPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isTFPortalEnabled;



    static {
        BUILDER.push("Configs");
        isPortalEnabled = BUILDER.comment().define("Is portal enabled", true);
        isEndPortalEnabled = BUILDER.comment().define("Is End portal enabled", true);
        BUILDER.push("Mods portals configs");
        isTFPortalEnabled = BUILDER.comment().define("Is Twilight portal enabled", true);


        BUILDER.pop();
        SPEC = BUILDER.build();

    }

}
