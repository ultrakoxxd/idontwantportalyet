package com.ultrakox.idontwantportalyet.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class common {
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
    //twilight forest
    public static final ForgeConfigSpec.ConfigValue<Boolean> isTFPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> TFPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> TFPortalTimerAfter;
    //blue skies
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEverbrightPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> everbrightPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> everbrightPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEverdawnPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> everdawnPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> everdawnPortalTimerAfter;
    // undergarden
    public static final ForgeConfigSpec.ConfigValue<Boolean> isUgrPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> ugrPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> ugrPortalTimerAfter;


    static {
        BUILDER.push("Configs");
        isPortalEnabled = BUILDER.comment().define("Is portal enabled", true);
        isEndPortalEnabled = BUILDER.comment().define("Is End portal enabled", true);

        BUILDER.comment("time is equal to one tick, if you want to disable timer set value to -1 (or anything below 0)");

        netherPortalTimerInt = BUILDER.comment().define("Time to enable nether portal", -1);
        netherPortalTimerAfter = BUILDER.comment().define("Message after timer for nether reach 0","nether portal is now enabled!");

        endPortalTimerInt = BUILDER.comment().define("Time to enable end portal", -1);
        endPortalTimerAfter = BUILDER.comment().define("Message after timer for end reach 0","end portal is now enabled!");

        BUILDER.push("Mods portals configs");
        BUILDER.comment("Twilight Forest");

        isTFPortalEnabled = BUILDER.comment().define("Is Twilight portal enabled", true);
        TFPortalTimerInt = BUILDER.comment().define("Time to enable twilight portal", -1);
        TFPortalTimerAfter = BUILDER.comment().define("Message after timer for twilight reach 0","twilight portal is now enabled!");

        BUILDER.comment("Blue Skies");

        isEverbrightPortalEnabled = BUILDER.define("Is Everbright portal enabled", true);
        everbrightPortalTimerInt = BUILDER.comment().define("Time to enable Everbright portal", -1);
        everbrightPortalTimerAfter = BUILDER.comment().define("Message after timer for Everbright reach 0","Everbright portal is now enabled!");


        isEverdawnPortalEnabled = BUILDER.define("Is Everdawn portal enabled", true);
        everdawnPortalTimerInt = BUILDER.comment().define("Time to enable Everdawn portal", -1);
        everdawnPortalTimerAfter = BUILDER.comment().define("Message after timer for Everdawn reach 0","Everdawn portal is now enabled!");

        BUILDER.comment("Undergarden");

        isUgrPortalEnabled = BUILDER.define("Is Undergarden portal enabled", true);
        ugrPortalTimerInt = BUILDER.comment().define("Time to enable Undergarden portal", -1);
        ugrPortalTimerAfter = BUILDER.comment().define("Message after timer for Undergarden reach 0","Undergarden portal is now enabled!");




        BUILDER.pop();
        SPEC = BUILDER.build();

    }

}
