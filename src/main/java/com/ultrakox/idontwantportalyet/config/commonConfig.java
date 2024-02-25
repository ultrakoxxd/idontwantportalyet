package com.ultrakox.idontwantportalyet.config;

import net.minecraftforge.common.ForgeConfigSpec;


public class commonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    //nether
    public static final ForgeConfigSpec.ConfigValue<Boolean> isPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> netherPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> netherPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> netherPortalAdvancement;

    //end
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEndPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> endPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> endPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> endPortalAdvancement;

    //otherside
    public static final ForgeConfigSpec.ConfigValue<Boolean> isOthersidePortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> othersidePortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> othersidePortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> othersidePortalAdvancement;
    //twilight forest
    public static final ForgeConfigSpec.ConfigValue<Boolean> isTFPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> TFPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> TFPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> TFPortalAdvancement;
    //blue skies
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEverbrightPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> everbrightPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> everbrightPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> everbrightPortalAdvancement;
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEverdawnPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> everdawnPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> everdawnPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> everdawnPortalAdvancement;

    //divine rpg
    // ICEIKA
    public static final ForgeConfigSpec.ConfigValue<Boolean> isIceikaPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> iceikaPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> iceikaPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> iceikaPortalAdvancement;
    // EDEN
    public static final ForgeConfigSpec.ConfigValue<Boolean> isEdenPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> edenPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> edenPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> edenPortalAdvancement;
    // WILDWOOD
    public static final ForgeConfigSpec.ConfigValue<Boolean> isWildwoodPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> wildwoodPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> wildwoodPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> wildwoodPortalAdvancement;
    // APALACHIA
    public static final ForgeConfigSpec.ConfigValue<Boolean> isApalachiaPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> apalachiaPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> apalachiaPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> apalachiaPortalAdvancement;
    // SKYTHERN
    public static final ForgeConfigSpec.ConfigValue<Boolean> isSkythernPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> skythernPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> skythernPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> skythernPortalAdvancement;
    // MORTUM
    public static final ForgeConfigSpec.ConfigValue<Boolean> isMortumPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> mortumPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> mortumPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> mortumPortalAdvancement;
    // VETHEA
    public static final ForgeConfigSpec.ConfigValue<Boolean> isVetheaPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> vetheaPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> vetheaPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> vetheaPortalAdvancement;
    //undergarden
    public static final ForgeConfigSpec.ConfigValue<Boolean> isUgrPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> ugrPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> ugrPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> ugrPortalAdvancement;

    //aether
    public static final ForgeConfigSpec.ConfigValue<Boolean> isAetherPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> aetherPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> aetherPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> aetherPortalAdvancement;
    // Aerial hell
    public static final ForgeConfigSpec.ConfigValue<Boolean> isAHPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> ahPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> ahPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> ahPortalAdvancement;
    //Exotelcraft
    public static final ForgeConfigSpec.ConfigValue<Boolean> isECPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> ECPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> ECPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> ECPortalAdvancement;
    //Gaia
    public static final ForgeConfigSpec.ConfigValue<Boolean> isGaiaPortalEnabled;
    public static final ForgeConfigSpec.ConfigValue<Integer> gaiaPortalTimerInt;
    public static final ForgeConfigSpec.ConfigValue<String> gaiaPortalTimerAfter;
    public static final ForgeConfigSpec.ConfigValue<String> gaiaPortalAdvancement;
    static {
        BUILDER.push("Configs");
        isPortalEnabled = BUILDER.comment().define("Is portal enabled", true);
        isEndPortalEnabled = BUILDER.comment().define("Is End portal enabled", true);

        BUILDER.comment("time is equal to one tick, if you want to disable timer set value to -1 (or anything below 0)");

        netherPortalTimerInt = BUILDER.comment().define("Time to enable nether portal", -1);
        netherPortalTimerAfter = BUILDER.comment().define("Message after timer for nether reach 0","nether portal is now enabled!");
        netherPortalAdvancement = BUILDER.comment().define("After this advancement nether portal will be enabled","");

        endPortalTimerInt = BUILDER.comment().define("Time to enable end portal", -1);
        endPortalTimerAfter = BUILDER.comment().define("Message after timer for end reach 0","end portal is now enabled!");
        endPortalAdvancement = BUILDER.comment().define("After this advancement end portal will be enabled","");

        BUILDER.push("Mods portals configs");

        BUILDER.comment("Deeper and Darker");

        isOthersidePortalEnabled = BUILDER.comment().define("Is Otherside portal enabled", true);
        othersidePortalTimerInt = BUILDER.comment().define("Time to enable Otherside portal", -1);
        othersidePortalTimerAfter = BUILDER.comment().define("Message after timer for Otherside reach 0","otherside portal is now enabled!");
        othersidePortalAdvancement = BUILDER.comment().define("After this advancement Otherside portal will be enabled","");

        BUILDER.comment("Twilight Forest");

        isTFPortalEnabled = BUILDER.comment().define("Is Twilight portal enabled", true);
        TFPortalTimerInt = BUILDER.comment().define("Time to enable Twilight portal", -1);
        TFPortalTimerAfter = BUILDER.comment().define("Message after timer for Twilight reach 0","twilight portal is now enabled!");
        TFPortalAdvancement = BUILDER.comment().define("After this advancement Twilight portal will be enabled","");

        BUILDER.comment("Blue Skies");

        isEverbrightPortalEnabled = BUILDER.define("Is Everbright portal enabled", true);
        everbrightPortalTimerInt = BUILDER.comment().define("Time to enable Everbright portal", -1);
        everbrightPortalTimerAfter = BUILDER.comment().define("Message after timer for Everbright reach 0","Everbright portal is now enabled!");
        everbrightPortalAdvancement = BUILDER.comment().define("After this advancement Everbright portal will be enabled","");


        isEverdawnPortalEnabled = BUILDER.define("Is Everdawn portal enabled", true);
        everdawnPortalTimerInt = BUILDER.comment().define("Time to enable Everdawn portal", -1);
        everdawnPortalTimerAfter = BUILDER.comment().define("Message after timer for Everdawn reach 0","Everdawn portal is now enabled!");
        everdawnPortalAdvancement = BUILDER.comment().define("After this advancement Everdawn portal will be enabled","");

        BUILDER.comment("Divine rpg");

        // ICEIKA
        isIceikaPortalEnabled = BUILDER.define("Is Iceika portal enabled", true);
        iceikaPortalTimerInt = BUILDER.comment().define("Time to enable Iceika portal", -1);
        iceikaPortalTimerAfter = BUILDER.comment().define("Message after timer for Iceika reach 0","Iceika portal is now enabled!");
        iceikaPortalAdvancement = BUILDER.comment().define("After this advancement Iceika portal will be enabled","");
        // EDEN
        isEdenPortalEnabled = BUILDER.define("Is Eden portal enabled", true);
        edenPortalTimerInt = BUILDER.comment().define("Time to enable Eden portal", -1);
        edenPortalTimerAfter = BUILDER.comment().define("Message after timer for Eden reach 0","Eden portal is now enabled!");
        edenPortalAdvancement = BUILDER.comment().define("After this advancement Eden portal will be enabled","");
        // WILDWOOD
        isWildwoodPortalEnabled = BUILDER.define("Is Wildwood portal enabled", true);
        wildwoodPortalTimerInt = BUILDER.comment().define("Time to enable Wildwood portal", -1);
        wildwoodPortalTimerAfter = BUILDER.comment().define("Message after timer for Wildwood reach 0","Wildwood portal is now enabled!");
        wildwoodPortalAdvancement = BUILDER.comment().define("After this advancement Wildwood portal will be enabled","");
        // APALACHIA
        isApalachiaPortalEnabled = BUILDER.define("Is Apalachia portal enabled", true);
        apalachiaPortalTimerInt = BUILDER.comment().define("Time to enable Apalachia portal", -1);
        apalachiaPortalTimerAfter = BUILDER.comment().define("Message after timer for Apalachia reach 0","Apalachia portal is now enabled!");
        apalachiaPortalAdvancement = BUILDER.comment().define("After this advancement Apalachia portal will be enabled","");
        // SKYTHERN
        isSkythernPortalEnabled = BUILDER.define("Is Skythern portal enabled", true);
        skythernPortalTimerInt = BUILDER.comment().define("Time to enable Skythern portal", -1);
        skythernPortalTimerAfter = BUILDER.comment().define("Message after timer for Skythern reach 0","Skythern portal is now enabled!");
        skythernPortalAdvancement = BUILDER.comment().define("After this advancement Skythern portal will be enabled","");
        // MORTUM
        isMortumPortalEnabled = BUILDER.define("Is Mortum portal enabled", true);
        mortumPortalTimerInt = BUILDER.comment().define("Time to enable Mortum portal", -1);
        mortumPortalTimerAfter = BUILDER.comment().define("Message after timer for Mortum reach 0","Mortum portal is now enabled!");
        mortumPortalAdvancement = BUILDER.comment().define("After this advancement Mortum portal will be enabled","");
        // VETHEA
        isVetheaPortalEnabled = BUILDER.define("Is Vethea portal enabled", true);
        vetheaPortalTimerInt = BUILDER.comment().define("Time to enable Vethea portal", -1);
        vetheaPortalTimerAfter = BUILDER.comment().define("Message after timer for Vethea reach 0","Vethea portal is now enabled!");
        vetheaPortalAdvancement = BUILDER.comment().define("After this advancement Vethea portal will be enabled","");

        BUILDER.comment("Undergarden");

        isUgrPortalEnabled = BUILDER.define("Is Undergarden portal enabled", true);
        ugrPortalTimerInt = BUILDER.define("Time to enable Undergarden portal", -1);
        ugrPortalTimerAfter = BUILDER.define("Message after timer for Undergarden rach 0", "Undergarden portal is now enabled!");
        ugrPortalAdvancement = BUILDER.comment().define("After this advancement Undergarden portal will be enabled","");

        BUILDER.comment("Aether");

        isAetherPortalEnabled = BUILDER.define("Is Aether portal enabled", true);
        aetherPortalTimerInt = BUILDER.define("Time to enable Aether portal", -1);
        aetherPortalTimerAfter = BUILDER.define("Message after timer for Aether reach 0", "Aether portal is now enabled!");
        aetherPortalAdvancement = BUILDER.comment().define("After this advancement Aether portal will be enabled","");

        BUILDER.comment("Aerial hell");

        isAHPortalEnabled = BUILDER.define("Is Aerial Hell portal enabled", true);
        ahPortalTimerInt = BUILDER.comment().define("Time to enable Aerial Hell portal", -1);
        ahPortalTimerAfter = BUILDER.comment().define("Message after timer for Aerial Hell reach 0","Aerial Hell portal is now enabled!");
        ahPortalAdvancement = BUILDER.comment().define("After this advancement Aeriaal Hell portal will be enabled","");

        BUILDER.comment("Exotelcraft");

        isECPortalEnabled = BUILDER.comment().define("Is Exotel portal enabled", true);
        ECPortalTimerInt = BUILDER.comment().define("Time to enable Exotel portal", -1);
        ECPortalTimerAfter = BUILDER.comment().define("Message after timer for Exotel reach 0","Exotel portal is now enabled!");
        ECPortalAdvancement = BUILDER.comment().define("After this advancement Exotel portal will be enabled","");

        BUILDER.comment("Gaia");

        isGaiaPortalEnabled = BUILDER.comment().define("Is Gaia portal enabled", true);
        gaiaPortalTimerInt = BUILDER.comment().define("Time to enable Gaia portal", -1);
        gaiaPortalTimerAfter = BUILDER.comment().define("Message after timer for Gaia reach 0","Gaia portal is now enabled!");
        gaiaPortalAdvancement = BUILDER.comment().define("After this advancement Gaia portal will be enabled","");

        BUILDER.pop();
        SPEC = BUILDER.build();

    }

}

