package com.idontwantportalyet;

import com.idontwantportalyet.config.*;

import com.idontwantportalyet.dependencies.deeperdarker.events.ddEvents;
import com.idontwantportalyet.dependencies.drpg.events.drpgEvents;
import com.idontwantportalyet.dependencies.twilightforest.events.tfEvents;
import com.idontwantportalyet.dependencies.blueskies.events.bsEvents;

import com.idontwantportalyet.dependencies.undergarden.events.ugrEvents;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.block.Blocks;
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
import net.minecraftforge.registries.ForgeRegistries;

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
        if(ModList.get().isLoaded("deeperdarker")){
          MinecraftForge.EVENT_BUS.register(ddEvents.class);
        }
        if(ModList.get().isLoaded("twilightforest")){
            MinecraftForge.EVENT_BUS.register(tfEvents.class);
        }
        if (ModList.get().isLoaded("blue_skies")){
            MinecraftForge.EVENT_BUS.register(bsEvents.class);
        }
        if(ModList.get().isLoaded("divinerpg")){
            MinecraftForge.EVENT_BUS.register(drpgEvents.class);
        }
        if(ModList.get().isLoaded("undergarden")){
            MinecraftForge.EVENT_BUS.register(ugrEvents.class);
        }

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, commonConfig.SPEC, "IDontWantPortalYet-common.toml");
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
