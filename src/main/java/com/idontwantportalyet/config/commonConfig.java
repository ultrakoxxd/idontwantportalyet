package com.idontwantportalyet.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class commonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Boolean> isPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEndPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isOthersidePortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isTFPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEverbrightPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEverdawnPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isIceikaPortalEnabled;


    static {
        BUILDER.push("Configs");
        isPortalEnabled = BUILDER.comment().define("Is portal enabled", true);
        isEndPortalEnabled = BUILDER.comment().define("Is End portal enabled", true);
        BUILDER.push("Mods portals configs");
        isOthersidePortalEnabled = BUILDER.comment().define("Is otherside portal enabled", true);
        isTFPortalEnabled = BUILDER.comment().define("Is Twilight portal enabled", true);
        isEverbrightPortalEnabled = BUILDER.define("Is Everbright portal enabled", true);
        isEverdawnPortalEnabled = BUILDER.define("Is Everdawn portal enabled", true);
        isIceikaPortalEnabled = BUILDER.define("Is Iceika portal enabled", true);

        BUILDER.pop();
        SPEC = BUILDER.build();

    }


}

