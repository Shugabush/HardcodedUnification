package com.shugabrush.hardcodedunification.mixin.avaritia;

import com.shugabrush.hardcodedunification.utils.ItemUnification;
import committee.nova.mods.avaritia.init.registry.enums.CollectorTier;
import net.minecraft.world.item.crafting.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = CollectorTier.class, remap = false)
public class CollectorTierMixin
{
    @Mutable
    @Shadow @Final public Ingredient production;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci)
    {
        production = Ingredient.of(ItemUnification.getItem(production.getItems()[0]));
    }
}
