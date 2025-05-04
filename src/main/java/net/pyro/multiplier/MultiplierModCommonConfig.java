package net.pyro.multiplier;

import net.minecraftforge.common.ForgeConfigSpec;

public class MultiplierModCommonConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> ITEM_MULT;
    public static final ForgeConfigSpec.ConfigValue<Integer> XP_MULT;

    static {
        BUILDER.push("Multiplier Mod");

        ITEM_MULT = BUILDER.comment("Multiplier for item drops")
                .define("Item Mult", 2);
        XP_MULT = BUILDER.comment("Multiplier for XP")
                .define("XP Mult", 3);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
