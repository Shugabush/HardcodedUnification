package com.shugabrush.hardcodedunification.utils;

import com.almostreliable.unified.utils.UnifyTag;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.ModList;

import com.shugabrush.almostfluidified.AlmostFluidified;

public class FluidUnification
{

    public static Fluid getFluid(ResourceLocation resourceLocation)
    {
        if (!ModList.get().isLoaded("almostfluidified"))
            return BuiltInRegistries.FLUID.get(resourceLocation);
        ResourceLocation unifiedResourceLocation = AlmostFluidified.getRuntime().getReplacementMap()
                .getReplacementForFluid(resourceLocation);

        if (unifiedResourceLocation != null)
        {
            return BuiltInRegistries.FLUID.get(unifiedResourceLocation);
        }
        return BuiltInRegistries.FLUID.get(resourceLocation);
    }

    public static Fluid getFluid(Fluid fluid)
    {
        return getFluid(BuiltInRegistries.FLUID.getKey(fluid));
    }

    public static FluidStack getFluid(FluidStack stack)
    {
        return new FluidStack(getFluid(stack.getFluid()), stack.getAmount());
    }

    public static Fluid getPreferredFluidForTag(UnifyTag<Fluid> tag)
    {
        if (!ModList.get().isLoaded("almostfluidified"))
            return null;

        return BuiltInRegistries.FLUID.get(AlmostFluidified.getRuntime().getReplacementMap().getPreferredFluidForTag(tag, e -> true));
    }
}
