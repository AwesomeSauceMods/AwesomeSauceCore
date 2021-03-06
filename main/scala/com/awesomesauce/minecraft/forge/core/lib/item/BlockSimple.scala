package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.block.Block
import net.minecraft.block.material.Material

class BlockSimple(mat: Material, val extraIconCount: Int) extends Block(mat) with TCustomTexturedBlock with BlockDescription {
  def this(mat: Material) = this(mat, 0)

  def getBlockIcon = this.blockIcon

  def getTheTextureName = this.getTextureName
}