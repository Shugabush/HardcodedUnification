package com.shugabrush.hardcodedunification.mixin.adastra;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import com.shugabrush.hardcodedunification.utils.FluidUnification;
import earth.terrarium.adastra.common.entities.vehicles.Rocket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(value = Rocket.class, remap = false)
public class RocketMixin
{

    @ModifyArg(method = "fluid",
               at = @At(value = "INVOKE",
                        target = "Lnet/minecraft/core/DefaultedRegistry;get(Lnet/minecraft/resources/ResourceLocation;)Ljava/lang/Object;"))
    public ResourceLocation fluid(ResourceLocation resourceLocation)
    {
        return BuiltInRegistries.FLUID.getKey(FluidUnification.getFluid(resourceLocation));
    }
}
