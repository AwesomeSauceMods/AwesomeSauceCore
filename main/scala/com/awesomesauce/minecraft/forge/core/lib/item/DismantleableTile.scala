package com.awesomesauce.minecraft.forge.core.lib.item

import java.util

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity


trait DismantleableTile extends TileEntity {
  def canDismantle(player: EntityPlayer): Boolean

  def dismantleTile(player: EntityPlayer, returnDrops: Boolean): util.ArrayList[ItemStack]
}

trait BasicDismantleableTile extends DismantleableTile {
  def canDismantle(player: EntityPlayer) = true

  def dismantleTile(player: EntityPlayer, returnDrops: Boolean): util.ArrayList[ItemStack] = {
    val list = new util.ArrayList[ItemStack]()
    list.add(new ItemStack(this.blockType))
    list
  }
}