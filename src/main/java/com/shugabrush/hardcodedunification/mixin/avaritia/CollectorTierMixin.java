package com.shugabrush.hardcodedunification.mixin.avaritia;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import com.shugabrush.hardcodedunification.ConfigData;
import committee.nova.mods.avaritia.init.registry.enums.CollectorTier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(value = CollectorTier.class, remap = false)
public class CollectorTierMixin
{

    // @ModifyArg(method = "<clinit>",
    // at = @At(value = "INVOKE",
    // target =
    // "Lnet/minecraft/world/item/crafting/Ingredient;of(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/world/item/crafting/Ingredient;"),
    // remap = true)
    // private static TagKey< Item> getUnifiedProductionTag(TagKey< Item> tag)
    // {
    // String tagStr = tag.location().toString();
    // if (tagStr.contains("neutronium"))
    // {
    // return ItemTags.create(
    // new ResourceLocation(tagStr.replaceAll("neutronium", ConfigData.INSTANCE.neutroniumMaterial)));
    // }
    // return tag;
    // }

    // @ModifyVariable(method = "<init>",
    // at = @At(value = "HEAD"),
    // remap = false)
    // private static Ingredient getUnifiedProductionIngredient(Ingredient original)
    // {
    // ItemStack[] items = original.getItems();
    // for (ItemStack item : items)
    // {
    // List<TagKey<Item>> tags = item.getTags().toList();
    // for (TagKey<Item> tag : tags)
    // {
    // String tagStr = tag.location().toString();
    // if (tagStr.contains("neutronium"))
    // {
    // return Ingredient.of(ItemTags.create(
    // new ResourceLocation(tagStr.replaceAll("neutronium", ConfigData.INSTANCE.neutroniumMaterial))));
    // }
    // }
    // }
    // return original;
    // }

    @Shadow
    @Final
    @Mutable
    public Ingredient production;

    @Inject(method = "<init>", at = @At("RETURN"))
    private void init(String enumName, int ordinal, String name, Ingredient production, int production_ticks,
                      CallbackInfo ci)
    {
        ItemStack[] items = production.getItems();
        for (ItemStack item : items)
        {
            List< TagKey< Item>> tags = item.getTags().toList();
            CompoundTag compoundTag = item.getTag();
            if (compoundTag != null)
            {
                // Parse the tag out of the compound
                String tagStr = compoundTag.toString();
                tagStr = tagStr.substring(tagStr.indexOf("forge"), tagStr.indexOf("\"}"));
                this.production = Ingredient.of(ItemTags.create(
                        new ResourceLocation(tagStr.replaceAll("neutronium", ConfigData.INSTANCE.neutroniumMaterial))));
            }
            else
            {
                for (TagKey< Item> tag : tags)
                {
                    String tagStr = tag.location().toString();
                    if (tagStr.contains("neutronium"))
                    {
                        this.production = Ingredient.of(ItemTags.create(
                                new ResourceLocation(
                                        tagStr.replaceAll("neutronium", ConfigData.INSTANCE.neutroniumMaterial))));
                    }
                }
            }
        }
    }
}
