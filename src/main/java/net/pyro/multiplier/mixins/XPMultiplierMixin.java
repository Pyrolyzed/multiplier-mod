package net.pyro.multiplier.mixins;

import net.pyro.multiplier.MultiplierModCommonConfig;

import java.lang.Math;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.world.entity.ExperienceOrb;

@Mixin(ExperienceOrb.class)
public class XPMultiplierMixin {
    /**
     * Gets the proper amount of experience by multiplying it by the configured
     * amount.
     * 
     * @param experience The experience to multiply.
     * @return The multiplied experience.
     */
    private int getAmount(int experience) {
        return (int) Math.ceil(experience * MultiplierModCommonConfig.XP_MULT.get());
    }

    /**
     * Modifies the experience items gain when using the Mending enchantment.
     * 
     * @param experience The experience gained.
     * @return Modified experience amount.
     */
    @ModifyArg(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ExperienceOrb;repairPlayerItems(Lnet/minecraft/world/entity/player/Player;I)I"), index = 1)
    private int injectItemRepair(int experience) {
        return getAmount(experience);
    }

    /**
     * Modifies the experience gained by the player.
     * 
     * @param experience The experience gained.
     * @return Modified experience amount.
     */
    @ModifyArg(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;giveExperiencePoints(I)V"))
    private int injectXPGive(int experience) {
        return getAmount(experience);
    }
}
