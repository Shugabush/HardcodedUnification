package com.shugabrush.hardcodedunification.mixin.mobgrindingutils;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;

import com.shugabrush.hardcodedunification.utils.FluidUnification;
import mob_grinding_utils.tile.TileEntityAbsorptionHopper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = TileEntityAbsorptionHopper.class, remap = false)
public class TileEntityAbsorptionHopperMixin
{

    @ModifyArg(method = "serverTick",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraftforge/fluids/FluidStack;<init>(Lnet/minecraft/world/level/material/Fluid;I)V"),
               index = 0)
    private static Fluid serverTickFluid(Fluid fluid)
    {
        return FluidUnification.getFluid(fluid);
    }

    @ModifyArg(method = "captureDroppedXP",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraftforge/fluids/capability/templates/FluidTank;fill(Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)I"),
               index = 0)
    FluidStack getUnifiedFluidToFill(FluidStack resource)
    {
        return FluidUnification.getFluid(resource);
    }
}
