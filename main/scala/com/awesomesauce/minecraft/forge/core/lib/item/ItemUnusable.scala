package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.item.Item
import net.minecraft.item.ItemStack

trait ItemUnusable extends Item with ItemDescription {
  override def doesContainerItemLeaveCraftingGrid(stack: ItemStack) = false
  setContainerItem(this);
  setMaxStackSize(1);
  addUsage("Crafting", "Unable to be destroyed.");
}