package com.shugabrush.hardcodedunification.mixin.avaritia;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.ModList;

import com.shugabrush.hardcodedunification.utils.ItemUnification;
import committee.nova.mods.avaritia.common.tile.NeutronCollectorTile;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = NeutronCollectorTile.class, remap = false)
public class NeutronCollectorTileMixin
{

    @Inject(method = "serverTick", at = @At(value = "HEAD"), cancellable = true)
    private static void safeServerTick(Level level, BlockPos pos, BlockState state, NeutronCollectorTile tile,
                                       CallbackInfo ci)
    {
        // Prevent an out of bounds exception
        if (tile.getTier().production.getItems().length == 0)
        {
            ci.cancel();
        }
    }

    @Inject(method = "getProduction", at = @At(value = "RETURN"), cancellable = true)
    private void getUnifiedProduction(CallbackInfoReturnable< Item> cir)
    {
        if (ModList.get().isLoaded("almostunified"))
        {
            cir.setReturnValue(ItemUnification.getItem(cir.getReturnValue()));
        }
    }
}
