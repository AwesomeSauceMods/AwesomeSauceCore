package com.awesomesauce.minecraft.forge.core.lib.util

import net.minecraft.util.ChunkCoordinates
import net.minecraftforge.common.util.ForgeDirection


class SideCoordinates(coord: ChunkCoordinates, side: ForgeDirection) {
  def getOffsetChunkCoordinates: ChunkCoordinates = new ChunkCoordinates(coord.posX + side.offsetX, coord.posY + side.offsetY, coord.posZ + side.offsetZ)
}
