package net.pyro.multiplier;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeConfigSpec;

public class MultiplierModCommonConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    /**
     * The amount to multiply item drops by
     */
    public static final ForgeConfigSpec.ConfigValue<Integer> ITEM_MULT;

    /**
     * The amount to multiply experience by.
     */
    public static final ForgeConfigSpec.ConfigValue<Double> XP_MULT;

    public static final ForgeConfigSpec.ConfigValue<List<String>> ATTRIBUTE_MODIFIERS;

    public static AttributeModifier deserialize(String serializedString) {
        String[] splitString = serializedString.split(":");
        return new AttributeModifier("multiplier_mod_attribute", Double.parseDouble(splitString[1]),
                AttributeModifier.Operation.valueOf(splitString[0].toUpperCase()));
    }

    static {

        BUILDER.push("Multipliers");

        ITEM_MULT = BUILDER.comment("Multiplier for item drops")
                .define("Item Mult", 2);
        XP_MULT = BUILDER.comment("Multiplier for XP")
                .define("XP Mult", 3.0);

        BUILDER.pop();
        final List<String> defaultList = new ArrayList<>();
        defaultList.add("minecraft:generic.luck:addition:2.0");
        ATTRIBUTE_MODIFIERS = BUILDER.comment("Correct Syntax:\n\"minecraft:generic.luck:addition:2.0\"").define(
                "Attributes",
                defaultList);
        SPEC = BUILDER.build();
    }
}
