package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.item.ItemStack
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.world.World
import net.minecraft.item.Item

trait ItemExperienceGiver extends ItemDescription {
  def amount: Int
  addUsage("Right Click",
    "Gives ".concat(Integer.toString(amount)).concat(" levels to you"));

  override def onItemUse(stack: ItemStack, player: EntityPlayer,
    world: World, x: Int, y: Int, z: Int, size: Int, n: Float, m: Float,
    o: Float): Boolean =
    {
      world.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.levelup",
        1.0F, world.rand.nextFloat() * 0.1F + 0.9F);
      player.addExperienceLevel(amount);
      stack.stackSize -= 1;
      return true;
    }
}
class ItemExperienceGiverImpl(exp: Int) extends Item with ItemExperienceGiver {
  def amount: Int = exp
}