package net.pyro.multiplier;

import java.util.HashMap;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(MultiplierMod.MOD_ID)
public class MultiplierMod {
    public static final String MOD_ID = "multipliermod";

    public MultiplierMod() {
        // IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register common (server-side) configuration file.
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MultiplierModCommonConfig.SPEC,
                "multipliermod-common.toml");

        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void modifyAttributes(EntityJoinLevelEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            for (var entry : MultiplierModCommonConfig.ATTRIBUTE_MODIFIERS.get()) {
                var attribute = ForgeRegistries.ATTRIBUTES
                        .getValue(new ResourceLocation(entry.split(":")[0] + ":" + entry.split(":")[1]));
                if (attribute == null)
                    continue;

                AttributeInstance instance = player.getAttribute(attribute);
                if (instance == null)
                    continue;

                AttributeModifier modifier = MultiplierModCommonConfig
                        .deserialize(entry.split(":")[2] + ":" + entry.split(":")[3]);
                instance.addTransientModifier(modifier);
            }
        }
    }
}
