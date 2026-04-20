package com.shugabrush.hardcodedunification.utils;

import com.almostreliable.unified.AlmostUnified;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;

public class ItemUnification
{

    public static Item getItem(ResourceLocation resourceLocation)
    {
        if (!ModList.get().isLoaded("almostunified")) return BuiltInRegistries.ITEM.get(resourceLocation);

        ResourceLocation unifiedResourceLocation = AlmostUnified.getRuntime().getReplacementMap().get()
                .getReplacementForItem(resourceLocation);
        if (unifiedResourceLocation != null)
        {
            return BuiltInRegistries.ITEM.get(unifiedResourceLocation);
        }
        return BuiltInRegistries.ITEM.get(resourceLocation);
    }

    public static Item getItem(String resourceLocation)
    {
        return getItem(new ResourceLocation(resourceLocation));
    }

    public static Item getItem(Item originalItem)
    {
        return getItem(getItemLocation(originalItem));
    }

    public static Item getItem(ItemStack originalItem)
    {
        return getItem(originalItem.getItem());
    }

    public static ResourceLocation getItemLocation(Item item)
    {
        return BuiltInRegistries.ITEM.getKey(item);
    }
}
