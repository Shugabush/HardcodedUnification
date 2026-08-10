package com.shugabrush.hardcodedunification.mixin.sgjourney;

import net.minecraftforge.fluids.FluidStack;
import net.povstalec.sgjourney.common.init.FluidInit;
import net.povstalec.sgjourney.common.items.StaffWeaponItem;

import com.shugabrush.hardcodedunification.utils.FluidUnification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = StaffWeaponItem.class, remap = false)
public class SGJourneyStaffWeaponItemMixin
{

    @Inject(method = "isCorrectFluid", at = @At(value = "RETURN"), cancellable = true)
    private void isUnifiedFluid(FluidStack fluidStack, CallbackInfoReturnable< Boolean> cir)
    {
        cir.setReturnValue(fluidStack.getFluid() ==
                FluidUnification.getFluid(FluidInit.LIQUID_NAQUADAH_SOURCE.get().getSource()) ||
                fluidStack.getFluid() ==
                        FluidUnification.getFluid(FluidInit.HEAVY_LIQUID_NAQUADAH_SOURCE.get().getSource()));
    }
}
