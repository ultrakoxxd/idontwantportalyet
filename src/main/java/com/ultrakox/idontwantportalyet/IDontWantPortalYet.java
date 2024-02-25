package com.ultrakox.idontwantportalyet;

import com.mojang.logging.LogUtils;
import com.ultrakox.idontwantportalyet.config.commonConfig;
import com.ultrakox.idontwantportalyet.dependencies.aerialhell.events.ahEvents;
import com.ultrakox.idontwantportalyet.dependencies.aether.events.aetherEvents;
import com.ultrakox.idontwantportalyet.dependencies.blueskies.events.bsEvents;
import com.ultrakox.idontwantportalyet.dependencies.deeperdarker.events.ddEvents;
import com.ultrakox.idontwantportalyet.dependencies.drpg.events.drpgEvents;
import com.ultrakox.idontwantportalyet.dependencies.exotelcraft.events.ecEvents;
import com.ultrakox.idontwantportalyet.dependencies.gaia.events.gaiaEvents;
import com.ultrakox.idontwantportalyet.dependencies.tforest.events.tfEvents;
import com.ultrakox.idontwantportalyet.dependencies.undergarden.events.ugrEvents;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(IDontWantPortalYet.MODID)
public class IDontWantPortalYet {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "idontwantportalyet";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    // Create a Deferred Register to hold Blocks which will all be registered under the "idontwantportalyet" namespace

    public IDontWantPortalYet() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonConfig.SPEC, "IDontWantPortalYet-common.toml");
        if(ModList.get().isLoaded("aerialhell")){
            MinecraftForge.EVENT_BUS.register(ahEvents.class);
            LOGGER.info("dependency found: aerial hell");
        }
        if(ModList.get().isLoaded("aether")){
            MinecraftForge.EVENT_BUS.register(aetherEvents.class);
            LOGGER.info("dependency found: aether");
        }
        if(ModList.get().isLoaded("blue_skies")){
            MinecraftForge.EVENT_BUS.register(bsEvents.class);
            LOGGER.info("dependency found: blue skies");
        }
        if(ModList.get().isLoaded("deeperdarker")){
            MinecraftForge.EVENT_BUS.register(ddEvents.class);
            LOGGER.info("dependency found: deeper and darker");
        }
        if(ModList.get().isLoaded("divinerpg")){
            MinecraftForge.EVENT_BUS.register(drpgEvents.class);
            LOGGER.info("dependency found: divinerpg");
        }
        if(ModList.get().isLoaded("twilightforest")){
            MinecraftForge.EVENT_BUS.register(tfEvents.class);
            LOGGER.info("dependency found: twilightforest");
        }
        if(ModList.get().isLoaded("undergarden")){
            MinecraftForge.EVENT_BUS.register(ugrEvents.class);
            LOGGER.info("dependency found: undergarden");
        }
        if(ModList.get().isLoaded("exotelcraft")){
            MinecraftForge.EVENT_BUS.register(ecEvents.class);
            LOGGER.info("dependency found: exotelcraft");
        }
        if(ModList.get().isLoaded("gaiadimension")){
            MinecraftForge.EVENT_BUS.register(gaiaEvents.class);
            LOGGER.info("dependency found: gaia dimension");
        }


    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");
        LOGGER.info("DIRT BLOCK >> {}", ForgeRegistries.BLOCKS.getKey(Blocks.DIRT));

        MinecraftForge.EVENT_BUS.register(RegisterCommandsEvent.class);
    }



    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
