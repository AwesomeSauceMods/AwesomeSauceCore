package com.awesomesauce.minecraft.forge.core.lib.util

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

trait ReadOnlyInventory {
  def getInventoryName(): String = ???
  def getInventoryStackLimit(): Int = ???
  def getSizeInventory(): Int = ???
  def getStackInSlot(i: Int): ItemStack = ???
  def hasCustomInventoryName(): Boolean = ???
}
class InventoryWrapper(inv: IInventory) extends ReadOnlyInventory {
  override def getInventoryName(): String = inv.getInventoryName()
  override def getInventoryStackLimit(): Int = inv.getInventoryStackLimit()
  override def getSizeInventory(): Int = inv.getSizeInventory()
  override def getStackInSlot(i: Int): ItemStack = inv.getStackInSlot(i)
  override def hasCustomInventoryName(): Boolean = inv.hasCustomInventoryName()
}