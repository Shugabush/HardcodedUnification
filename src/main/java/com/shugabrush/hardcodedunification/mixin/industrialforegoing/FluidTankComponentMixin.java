package com.shugabrush.hardcodedunification.mixin.industrialforegoing;

import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import com.hrznstudio.titanium.component.fluid.FluidTankComponent;
import com.shugabrush.almostfluidified.unification.utils.FluidUnification;
import net.minecraftforge.fml.ModList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = FluidTankComponent.class, remap = false)
public class FluidTankComponentMixin extends FluidTank
{

    @Shadow
    private FluidTankComponent.Action tankAction;

    public FluidTankComponentMixin(int capacity)
    {
        super(capacity);
    }

    /**
     * @author Shugabrush
     * @reason Make sure fluid tanks that would normally fill
     *         with Industrial foregoing's XP fill with the configured XP instead
     */
    @Overwrite
    public int fill(FluidStack stack, FluidAction action)
    {
        Fluid fluid = ModList.get().isLoaded("almostfluidified") ? FluidUnification.getFluid(stack.getFluid()) : stack.getFluid();
        if (stack.getFluid() != fluid)
        {
            stack = new FluidStack(fluid, stack.getAmount());
        }
        return tankAction.canFill() ? super.fill(stack, action) : 0;
    }

    @Overwrite
    public int fillForced(FluidStack stack, FluidAction action)
    {
        Fluid fluid = ModList.get().isLoaded("almostfluidified") ? FluidUnification.getFluid(stack.getFluid()) : stack.getFluid();
        if (stack.getFluid() != fluid)
        {
            stack = new FluidStack(fluid, stack.getAmount());
        }
        return super.fill(stack, action);
    }

    @Overwrite
    private FluidStack drainInternal(FluidStack stack, FluidAction action)
    {
        if (!ModList.get().isLoaded("almostfluidified")) return stack;
        Fluid unifiedFluid = FluidUnification.getFluid(stack.getFluid());
        if (stack.getFluid() != unifiedFluid)
        {
            stack = new FluidStack(unifiedFluid, stack.getAmount());
        }

        if (stack.isEmpty() || !stack.isFluidEqual(fluid))
        {
            return FluidStack.EMPTY;
        }
        return drain(stack.getAmount(), action);
    }

    @Overwrite
    private FluidStack drainInternal(int maxDrain, FluidAction action)
    {
        int drained = maxDrain;
        if (fluid.getAmount() < drained)
        {
            drained = fluid.getAmount();
        }
        FluidStack stack = new FluidStack(fluid, drained);
        if (!ModList.get().isLoaded("almostfluidified")) return stack;
        if (action.execute() && drained > 0)
        {
            fluid.shrink(drained);
            onContentsChanged();
        }
        return stack;
    }

    @Overwrite
    public FluidStack drainForced(FluidStack stack, FluidAction action)
    {
        if (!ModList.get().isLoaded("almostfluidified")) return stack;
        Fluid unifiedFluid = FluidUnification.getFluid(stack.getFluid());
        if (stack.getFluid() != unifiedFluid)
        {
            stack = new FluidStack(unifiedFluid, stack.getAmount());
        }

        if (stack.isEmpty() || !stack.isFluidEqual(fluid))
        {
            return FluidStack.EMPTY;
        }
        return drainForced(stack.getAmount(), action);
    }

    @Overwrite
    public FluidStack drainForced(int maxDrain, FluidAction action)
    {
        return drainInternal(maxDrain, action);
    }
}
