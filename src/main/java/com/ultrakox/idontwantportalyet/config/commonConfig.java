package com.ultrakox.idontwantportalyet.config;

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
    public static final ForgeConfigSpec.ConfigValue<Integer> othersidePortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> othersidePortalTimerAfter;
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

    //divine rpg
    // ICEIKA
    public static final ForgeConfigSpec.ConfigValue<Boolean> isIceikaPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> iceikaPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> iceikaPortalTimerAfter;
    // EDEN
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEdenPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> edenPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> edenPortalTimerAfter;
    // WILDWOOD
    public static final ForgeConfigSpec.ConfigValue<Boolean> isWildwoodPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> wildwoodPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> wildwoodPortalTimerAfter;
    // APALACHIA
    public static final ForgeConfigSpec.ConfigValue<Boolean> isApalachiaPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> apalachiaPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> apalachiaPortalTimerAfter;
    // SKYTHERN
    public static final ForgeConfigSpec.ConfigValue<Boolean> isSkythernPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> skythernPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> skythernPortalTimerAfter;
    // MORTUM
    public static final ForgeConfigSpec.ConfigValue<Boolean> isMortumPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> mortumPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> mortumPortalTimerAfter;
    // VETHEA
    public static final ForgeConfigSpec.ConfigValue<Boolean> isVetheaPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> vetheaPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> vetheaPortalTimerAfter;
    //undergarden
    public static final ForgeConfigSpec.ConfigValue<Boolean> isUgrPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> ugrPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> ugrPortalTimerAfter;

    //aether
    public static final ForgeConfigSpec.ConfigValue<Boolean> isAetherPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> aetherPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> aetherPortalTimerAfter;
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

        BUILDER.comment("Deeper and Darker");

        isOthersidePortalEnabled = BUILDER.comment().define("Is otherside portal enabled", true);
        othersidePortalTimerInt = BUILDER.comment().define("Time to enable otherside portal", -1);
        othersidePortalTimerAfter = BUILDER.comment().define("Message after timer for otherside reach 0","otherside portal is now enabled!");

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

        BUILDER.comment("Divine rpg");

        // ICEIKA
        isIceikaPortalEnabled = BUILDER.define("Is Iceika portal enabled", true);
        iceikaPortalTimerInt = BUILDER.comment().define("Time to enable Iceika portal", -1);
        iceikaPortalTimerAfter = BUILDER.comment().define("Message after timer for Iceika reach 0","Iceika portal is now enabled!");
        // EDEN
        isEdenPortalEnabled = BUILDER.define("Is Eden portal enabled", true);
        edenPortalTimerInt = BUILDER.comment().define("Time to enable Eden portal", -1);
        edenPortalTimerAfter = BUILDER.comment().define("Message after timer for Eden reach 0","Eden portal is now enabled!");
        // WILDWOOD
        isWildwoodPortalEnabled = BUILDER.define("Is Wildwood portal enabled", true);
        wildwoodPortalTimerInt = BUILDER.comment().define("Time to enable Wildwood portal", -1);
        wildwoodPortalTimerAfter = BUILDER.comment().define("Message after timer for Wildwood reach 0","Wildwood portal is now enabled!");
        // APALACHIA
        isApalachiaPortalEnabled = BUILDER.define("Is Apalachia portal enabled", true);
        apalachiaPortalTimerInt = BUILDER.comment().define("Time to enable Apalachia portal", -1);
        apalachiaPortalTimerAfter = BUILDER.comment().define("Message after timer for Apalachia reach 0","Apalachia portal is now enabled!");
        // SKYTHERN
        isSkythernPortalEnabled = BUILDER.define("Is Skythern portal enabled", true);
        skythernPortalTimerInt = BUILDER.comment().define("Time to enable Skythern portal", -1);
        skythernPortalTimerAfter = BUILDER.comment().define("Message after timer for Skythern reach 0","Skythern portal is now enabled!");
        // MORTUM
        isMortumPortalEnabled = BUILDER.define("Is Mortum portal enabled", true);
        mortumPortalTimerInt = BUILDER.comment().define("Time to enable Mortum portal", -1);
        mortumPortalTimerAfter = BUILDER.comment().define("Message after timer for Mortum reach 0","Mortum portal is now enabled!");
        // VETHEA
        isVetheaPortalEnabled = BUILDER.define("Is Vethea portal enabled", true);
        vetheaPortalTimerInt = BUILDER.comment().define("Time to enable Vethea portal", -1);
        vetheaPortalTimerAfter = BUILDER.comment().define("Message after timer for Vethea reach 0","Vethea portal is now enabled!");

        BUILDER.comment("Undergarden");

        isUgrPortalEnabled = BUILDER.define("Is Undergarden portal enabled", true);
        ugrPortalTimerInt = BUILDER.define("Time to enable Undergarden portal", -1);
        ugrPortalTimerAfter = BUILDER.define("Message after timer for Undergarden rach 0", "Undergarden portal is now enabled!");

        BUILDER.comment("Aether");

        isAetherPortalEnabled = BUILDER.define("Is Aether portal enabled", true);
        aetherPortalTimerInt = BUILDER.define("Time to enable Aether portal", -1);
        aetherPortalTimerAfter = BUILDER.define("Message after timer for Aether reach 0", "Aether portal is now enabled!");


        BUILDER.pop();
        SPEC = BUILDER.build();

    }

}

