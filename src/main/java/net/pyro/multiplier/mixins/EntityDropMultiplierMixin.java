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

    /**
     * Get the proper amount of items to drop.
     * 
     * @param itemCount Unmodified item count.
     * @return Modified item count.
     */
    private int getAmount(int itemCount) {
        return itemCount * MultiplierModCommonConfig.ITEM_MULT.get();
    }

    /**
     * Mixin that overrides the `dropFromLootTable` method, and makes drops be
     * multiplied by the amount given in getAmount().
     * 
     * @param output Consumer of ItemStack drop.
     * @return Modified ItemStack Consumer.
     */
    @ModifyArg(method = "dropFromLootTable", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/storage/loot/LootTable;getRandomItems(Lnet/minecraft/world/level/storage/loot/LootParams;JLjava/util/function/Consumer;)V"))
    private Consumer<ItemStack> injected(Consumer<ItemStack> output) {
        return itemStack -> {
            itemStack.setCount(getAmount(itemStack.getCount()));
            output.accept(itemStack);
        };
    }

}
