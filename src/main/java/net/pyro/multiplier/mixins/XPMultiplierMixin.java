package net.pyro.multiplier.mixins;

import net.pyro.multiplier.MultiplierModCommonConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.world.entity.ExperienceOrb;

@Mixin(ExperienceOrb.class)
public class XPMultiplierMixin {
    @ModifyArg(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ExperienceOrb;repairPlayerItems(Lnet/minecraft/world/entity/player/Player;I)I"), index = 1)
    private int injectItemRepair(int x) {
        return x * MultiplierModCommonConfig.XP_MULT.get();
    }

    @ModifyArg(method = "playerTouch", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/player/Player;giveExperiencePoints(I)V"))
    private int injectXPGive(int x) {
        return x * MultiplierModCommonConfig.XP_MULT.get();
    }
}
