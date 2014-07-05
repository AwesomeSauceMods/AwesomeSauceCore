package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.block.Block
import net.minecraft.client.renderer.texture.IIconRegister
import net.minecraft.util.IIcon
import net.minecraft.world.IBlockAccess

trait TCustomTexturedBlock extends Block {
  val extraIconCount : Int
  val textures = new Array[IIcon](extraIconCount+1)
  val defaultTextures = new Array[IIcon](6)
  def setDefaultTextureForSide(side:Int, textureNo:Int) = defaultTextures(side) = textures(textureNo)
  override def registerBlockIcons(reg: IIconRegister) = {
    super.registerBlockIcons(reg)
    textures(0) = this.blockIcon
    for (i <- Range.inclusive(1, extraIconCount))
      textures(i) = reg.registerIcon(this.getTextureName() + "_" + i)
    for (i <- Range.inclusive(0, 5))
      defaultTextures(i) = textures(0)
  }
  override def getIcon(side:Int, meta:Int) = defaultTextures(side)
}
