package com.shugabrush.hardcodedunification.mixin.sgjourney;

import net.minecraftforge.fluids.FluidStack;
import net.povstalec.sgjourney.common.init.FluidInit;
import net.povstalec.sgjourney.common.items.PowerCellItem;

import com.shugabrush.hardcodedunification.ConfigData;
import com.shugabrush.hardcodedunification.utils.FluidUnification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PowerCellItem.class, remap = false)
public class SGJourneyPowerCellItemMixin
{

    @Inject(method = "isCorrectFluid", at = @At(value = "RETURN"), cancellable = true)
    private void isUnifiedFluid(FluidStack fluidStack, CallbackInfoReturnable< Boolean> cir)
    {
        if (ConfigData.INSTANCE.stargateJourneyUnificationEnabled)
        {
            cir.setReturnValue(fluidStack.getFluid() ==
                    FluidUnification.getFluid(FluidInit.LIQUID_NAQUADAH_SOURCE.get().getSource()) ||
                    fluidStack.getFluid() ==
                            FluidUnification.getFluid(FluidInit.HEAVY_LIQUID_NAQUADAH_SOURCE.get().getSource()));
        }
    }
}
