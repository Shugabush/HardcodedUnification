package com.shugabrush.hardcodedunification.mixin.sgjourney;

import net.minecraft.world.level.material.Fluid;
import net.povstalec.sgjourney.common.items.FluidItem;

import com.shugabrush.hardcodedunification.ConfigData;
import com.shugabrush.hardcodedunification.utils.FluidUnification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = FluidItem.class, remap = false)
public class SGJourneyFluidItemMixin
{

    @ModifyVariable(method = "fluidSetup", at = @At(value = "HEAD"), index = 1)
    private static Fluid unifiedFluidSetup(Fluid fluid)
    {
        return ConfigData.INSTANCE.stargateJourneyUnificationEnabled ? FluidUnification.getFluid(fluid) : fluid;
    }
}
