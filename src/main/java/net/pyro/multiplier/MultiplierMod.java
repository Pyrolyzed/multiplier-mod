package net.pyro.multiplier;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod(MultiplierMod.MOD_ID)
public class MultiplierMod {
    public static final String MOD_ID = "multipliermod";

    public MultiplierMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, MultiplierModCommonConfig.SPEC,
                "multipliermod-common.toml");
    }
}
