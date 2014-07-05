package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.IIcon

trait ItemTexturable extends Item {
  val extraIconCount: Int

  def getItemIcon: IIcon

  def getIconStr: String
  val textures = new Array[IIcon](extraIconCount + 1)
	override def registerIcons(r:IIconRegister) = {
    super.registerIcons(r)
    textures(0) = this.getItemIcon
    for (i <- Range.inclusive(1, extraIconCount))
      textures(i) = r.registerIcon(this.getIconStr + "_" + i)
	}

  override def getIcon(stack: ItemStack, pass: Int): IIcon = {
    if (stack.hasTagCompound() && stack.getTagCompound.hasKey("texture"))
      return textures(stack.getTagCompound.getInteger("texture"))
    return textures(0)
  }
}