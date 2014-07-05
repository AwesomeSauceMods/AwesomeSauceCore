package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.block.BlockContainer
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.IIcon
import net.minecraft.world.{IBlockAccess, World}

class BlockSimpleContainer(mat: Material, tile: () => TileEntity, val extraIconCount: Int) extends BlockContainer(mat) with TCustomTexturedBlock {
  def this(mat: Material, tile: () => TileEntity) = this(mat, tile, 0)

  def getBlockIcon = blockIcon

  def getTheTextureName = getTextureName

  override def getIcon(w: IBlockAccess, x: Int, y: Int, z: Int, side: Int): IIcon = {
    val te = w.getTileEntity(x, y, z)
    if (te.isInstanceOf[TCustomTexture]) {
      val texturer = te.asInstanceOf[TCustomTexture]
      if (texturer.getTextureForSide(side) != 0)
        return textures(texturer.getTextureForSide(side))
    }
    return defaultTextures(side)
  }

  /**
   * Called upon block activation (right click on the block.)
   */
  override def onBlockActivated(par1World: World, x: Int, y: Int, z: Int,
                                par5EntityPlayer: EntityPlayer, par6: Int, par7: Float, par8: Float,
                                par9: Float): Boolean = {
    if (par1World.getTileEntity(x, y, z).isInstanceOf[TActivatedTileEntity])
      return par1World
        .getTileEntity(x, y, z).asInstanceOf[TActivatedTileEntity].activate(par5EntityPlayer, par6,
          par7, par8, par9)
    else
      return false;
  }

  def createNewTileEntity(world: World, int: Int): TileEntity = tile()

  override def isProvidingStrongPower(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Int = {
    if (world.getTileEntity(x, y, z).isInstanceOf[TRedstone]) {
      return world.getTileEntity(x, y, z).asInstanceOf[TRedstone].getStrongPower
    }
    return 0
  }

  override def isProvidingWeakPower(world: IBlockAccess, x: Int, y: Int, z: Int, side: Int): Int = {
    if (world.getTileEntity(x, y, z).isInstanceOf[TRedstone]) {
      return world.getTileEntity(x, y, z).asInstanceOf[TRedstone].getWeakPower
    }
    return 0
  }

  override def canProvidePower() = true
}