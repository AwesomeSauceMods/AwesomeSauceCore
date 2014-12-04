package com.awesomesauce.minecraft.forge.core.lib.item

import java.util

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack


trait DismantleableTile {
  def canDismantle(player: EntityPlayer): Boolean

  def dismantleTile(player: EntityPlayer, returnDrops: Boolean): util.ArrayList[ItemStack]
}
