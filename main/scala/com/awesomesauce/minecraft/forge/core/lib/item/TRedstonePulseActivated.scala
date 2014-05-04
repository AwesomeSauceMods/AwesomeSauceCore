package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.tileentity.TileEntity

trait TRedstonePulseActivated extends TileEntity {
  var previouslyActivated = false
  def pulse()
  def checkPulse() =
    {
      if (getWorldObj().isBlockIndirectlyGettingPowered(xCoord, yCoord, zCoord)) {
        if (!previouslyActivated)
        {
          previouslyActivated = true
          pulse()
        }
      }
      else
      {
        previouslyActivated = false
      }
    }
  override def updateEntity() = {
    super.updateEntity()
    checkPulse()
  }
}