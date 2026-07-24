package com.shugabrush.hardcodedunification.mixin.mekanism;

import com.falcon2235.moremultiblock.machine.ChemRecipes;
import com.shugabrush.hardcodedunification.utils.FluidUnification;
import com.shugabrush.hardcodedunification.utils.ItemUnification;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = ChemRecipes.class, remap = false)
public class ChemRecipesMixin
{
    @Inject(method = "item", at = @At(value = "RETURN"), cancellable = true)
    private static void unifiedItem(Item item, int count, CallbackInfoReturnable<ItemStack> cir)
    {
        cir.setReturnValue(new ItemStack(ItemUnification.getItem(cir.getReturnValue()), cir.getReturnValue().getCount()));
    }

    @Inject(method = "modItem", at = @At(value = "RETURN"), cancellable = true)
    private static void unifiedModItem(String namespace, String path, int count, CallbackInfoReturnable<ItemStack> cir)
    {
        cir.setReturnValue(new ItemStack(ItemUnification.getItem(cir.getReturnValue()), cir.getReturnValue().getCount()));
    }
}
