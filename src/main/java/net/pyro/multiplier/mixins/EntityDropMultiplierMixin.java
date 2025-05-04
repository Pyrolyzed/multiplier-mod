package net.pyro.multiplier.mixins;

import java.util.function.Consumer;

import net.pyro.multiplier.MultiplierModCommonConfig;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

@Mixin(LivingEntity.class)
public class EntityDropMultiplierMixin {

    @ModifyArg(method = "dropFromLootTable", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootParams;JLjava/util/function/Consumer;)V"))
    private Consumer<ItemStack> injected(Consumer<ItemStack> output) {
        return itemStack -> {
            itemStack.setCount(itemStack.getCount() * MultiplierModCommonConfig.ITEM_MULT.get());
            output.accept(itemStack);
        };
    }

}
