package com.shugabrush.hardcodedunification.mixin.utilitix;

import com.shugabrush.hardcodedunification.utils.FluidUnification;
import de.melanx.utilitix.content.experiencecrystal.TileExperienceCrystal;
import net.minecraftforge.fluids.FluidStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = TileExperienceCrystal.class, remap = false)
public class TileExperienceCrystalMixin
{
    @Inject(method = "getFluidInTank", at = @At("RETURN"), cancellable = true)
    void getUnifiedFluidInTank(int tank, CallbackInfoReturnable<FluidStack> cir)
    {
        cir.setReturnValue(FluidUnification.getFluid(cir.getReturnValue()));
    }
}
