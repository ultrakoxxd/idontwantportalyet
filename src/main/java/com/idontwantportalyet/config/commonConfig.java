package com.idontwantportalyet.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class commonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    //nether
    public static final ForgeConfigSpec.ConfigValue<Boolean> isPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> netherPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> netherPortalTimerAfter;

    //end
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEndPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> endPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> endPortalTimerAfter;

    //otherside
    public static final ForgeConfigSpec.ConfigValue<Boolean> isOthersidePortalEnabled;
    //twilight forest
    public static final ForgeConfigSpec.ConfigValue<Boolean> isTFPortalEnabled;
    //blue skies
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEverbrightPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEverdawnPortalEnabled;
   //divine rpg
    public static final ForgeConfigSpec.ConfigValue<Boolean> isIceikaPortalEnabled;


    static {
        BUILDER.push("Configs");
        isPortalEnabled = BUILDER.comment().define("Is portal enabled", true);
        isEndPortalEnabled = BUILDER.comment().define("Is End portal enabled", true);

        BUILDER.comment("time is equal to one tick, if you want to disable timer set value to -1 (or anything below 0)");

        netherPortalTimerInt = BUILDER.comment().define("Time to enable nether portal", -1);
        netherPortalTimerAfter = BUILDER.comment().define("Messaage after timer for nether reach 0","nether portal is now enabled!");

        endPortalTimerInt = BUILDER.comment().define("Time to enable end portal", -1);
        endPortalTimerAfter = BUILDER.comment().define("Messaage after timer for end reach 0","end portal is now enabled!");

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

