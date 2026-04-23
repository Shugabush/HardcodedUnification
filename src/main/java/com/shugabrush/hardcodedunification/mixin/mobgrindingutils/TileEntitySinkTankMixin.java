package com.shugabrush.hardcodedunification.mixin.mobgrindingutils;

import net.minecraftforge.fluids.FluidStack;

import com.shugabrush.hardcodedunification.utils.FluidUnification;
import mob_grinding_utils.tile.TileEntitySinkTank;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = TileEntitySinkTank.class, remap = false)
public class TileEntitySinkTankMixin
{

    @ModifyArg(method = "captureDroppedXP",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraftforge/fluids/capability/templates/FluidTank;fill(Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)I"),
               index = 0)
    FluidStack getUnifiedFluid(FluidStack resource)
    {
        return FluidUnification.getFluid(resource);
    }
}
