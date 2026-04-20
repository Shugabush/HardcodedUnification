package com.shugabrush.hardcodedunification.mixin.gtceu;

import com.gregtechceu.gtceu.api.machine.steam.SteamBoilerMachine;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.ModList;

import com.shugabrush.hardcodedunification.utils.FluidUnification;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = SteamBoilerMachine.class, remap = false)
public abstract class SteamBoilerMachineMixin
{

    // Make steam boilers produce Configured Liquid Steam
    @ModifyArg(
               method = "updateCurrentTemperature",
               at = @At(
                        value = "INVOKE",
                        target = "Lcom/gregtechceu/gtceu/api/machine/trait/NotifiableFluidTank;fillInternal(Lnet/minecraftforge/fluids/FluidStack;Lnet/minecraftforge/fluids/capability/IFluidHandler$FluidAction;)I"),
               index = 0)
    private FluidStack unifySteam(FluidStack stack)
    {
        if (!ModList.get().isLoaded("almostfluidified"))
            return stack;
        return new FluidStack(FluidUnification.getFluid(stack.getFluid()), stack.getAmount());
    }
}
