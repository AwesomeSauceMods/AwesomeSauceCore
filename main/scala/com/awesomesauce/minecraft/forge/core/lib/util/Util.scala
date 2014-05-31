package com.awesomesauce.minecraft.forge.core.lib.util

import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.item.BlockSimple
import com.awesomesauce.minecraft.forge.core.lib.item.BlockSimpleContainer
import com.awesomesauce.minecraft.forge.core.lib.item.ItemDescription
import com.awesomesauce.minecraft.forge.core.lib.item.ItemDescriptionImpl
import com.awesomesauce.minecraft.forge.core.lib.util.vec.SidePositionImpl

import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.dispenser.IPosition
import net.minecraft.dispenser.PositionImpl
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Blocks
import net.minecraft.inventory.IInventory
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.stats.Achievement
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ChatComponentText
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.oredict.OreDictionary

object WorldUtil {

  def setHollowCube(world: World, veto: Block, container: Block,
    mx: Int, my: Int, mz: Int, ox: Int, oy: Int, oz: Int) =
    {
      setCube(world, veto, container, mx, my, mz, ox, oy, oz)
      setCube(world, veto, Blocks.air, mx + 1, my + 1, mz + 1, ox - 1, oy - 1, oz - 1)
    }

  def setCube(world: World, veto: Block, block: Block,
    mx: Int, my: Int, mz: Int, ox: Int, oy: Int, oz: Int) =
    {
      for (x <- mx to ox)
        for (y <- my to oy)
          for (z <- mz to oz)
            if (world.getBlock(x, y, z) != veto)
              world.setBlock(x, y, z, block);
    }
  def scanForBlockOfType(w: IBlockAccess, loc: IPosition,
    block: Block): Array[PositionImpl] =
    {
      val locations = scala.collection.mutable.Set[PositionImpl]();
      for (i <- ForgeDirection.values()) {
        val l = new SidePositionImpl(loc, i)
          .getOffsetPositionImpl();
        if (w.getBlock(l.getX().asInstanceOf[Int], l.getY().asInstanceOf[Int], l.getZ().asInstanceOf[Int]) == block)
          locations.add(l);
      }
      return locations.toArray
    }
  def scanForBlockTileEntityInstanceOf[te](w: IBlockAccess, loc: IPosition): Array[PositionImpl] =
    {
      val locations = scala.collection.mutable.Set[PositionImpl]();
      for (i <- ForgeDirection.values()) {
        val l = new SidePositionImpl(loc, i)
          .getOffsetPositionImpl();
        if (w.getTileEntity(l.getX().toInt, l.getY().toInt, l.getZ().toInt).isInstanceOf[te])
          locations.add(l);
      }
      return locations.toArray;
    }
}
object PlayerUtil {

  def unlockAchievement(p: EntityPlayer, a: Achievement) =
    {
      p.addStat(a, 1)
    }

  def sendChatMessage(player: EntityPlayer, string: String) = {
    if (!player.worldObj.isRemote)
      player.addChatComponentMessage(new ChatComponentText(string));

  }
}
object InventoryUtil {
  def scanInventoryForItems(inventory: IInventory, stack: ItemStack): Boolean = scanInventoryForItems(new InventoryWrapper(inventory), stack)
  def scanInventoryForItems(inventory: ReadOnlyInventory, stack: ItemStack): Boolean = {
    for (i <- Range(0, inventory.getSizeInventory)) {
      if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).isItemEqual(stack))
        return true
    }
    return false
  }
  def addStackToInventory(inventory: IInventory, itemStack: ItemStack): Boolean =
    {
      for (i: Int <- 0 to inventory.getSizeInventory()) {
        if (inventory.getStackInSlot(i) == null) {
          inventory.setInventorySlotContents(i, itemStack)
          return true
        }
        if (inventory.getStackInSlot(i).isItemEqual(itemStack))
          if (inventory.getStackInSlot(i).stackSize + itemStack.stackSize <= inventory.getStackInSlot(i).getMaxStackSize()) {
            inventory.getStackInSlot(i).stackSize += itemStack.stackSize;
            return true
          } else {
            val stackSize = inventory.getStackInSlot(i).stackSize + itemStack.stackSize - inventory.getStackInSlot(i).getMaxStackSize()
            inventory.getStackInSlot(i).stackSize = inventory.getStackInSlot(i).getMaxStackSize()
            itemStack.stackSize = stackSize
            addStackToInventory(inventory, itemStack)
            return true
          }
      }
      return false;
    }
  def addStackToSlotInInventory(inventory: IInventory, itemStack: ItemStack, slot: Int): Boolean =
    {
      if (slot == -1)
        return addStackToInventory(inventory, itemStack)
      if (inventory.getStackInSlot(slot) == null) {
        inventory.setInventorySlotContents(slot, itemStack)
        return true
      }
      if (inventory.getStackInSlot(slot).isItemEqual(itemStack))
        if (inventory.getStackInSlot(slot).stackSize + itemStack.stackSize <= inventory.getStackInSlot(slot).getMaxStackSize()) {
          inventory.getStackInSlot(slot).stackSize += itemStack.stackSize;
          return true
        } else {
          val stackSize = inventory.getStackInSlot(slot).stackSize + itemStack.stackSize - inventory.getStackInSlot(slot).getMaxStackSize()
          inventory.getStackInSlot(slot).stackSize = inventory.getStackInSlot(slot).getMaxStackSize()
          itemStack.stackSize = stackSize
          return addStackToInventory(inventory, itemStack)
        }
      return false;
    }
}
object ItemUtil {

  def makeItem(mod: TAwesomeSauceMod,
    unlocalizedName: String): ItemDescription = {
    makeItem(mod, unlocalizedName, false)
  }
  def makeItem(mod: TAwesomeSauceMod,
    unlocalizedName: String, oredict: Boolean): ItemDescription = {
    makeItem(mod, unlocalizedName, new ItemDescriptionImpl(), oredict).asInstanceOf[ItemDescription]
  }

  def makeBlock(mod: TAwesomeSauceMod,
    unlocalizedName: String, mat: Material,
    te: () => TileEntity): Block = {
    GameRegistry.registerTileEntity(te().getClass(), mod.getTextureDomain + "." + unlocalizedName);
    return makeBlock(mod, unlocalizedName, new BlockSimpleContainer(mat, te))
  }

  def makeItem(mod: TAwesomeSauceMod, unlocalizedName: String, item: Item): Item = {
    makeItem(mod, unlocalizedName, item, false);
  }
  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String, mat: Material): Block =
    {
      return makeBlock(mod, unlocalizedName, new BlockSimple(mat))
    }
  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String, mat: Material, oredict: Boolean): Block =
    {
      return makeBlock(mod, unlocalizedName, new BlockSimple(mat), oredict)
    }
  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String,
    block: Block,
    oredict: Boolean): Block = {
    block.setBlockName(mod.getTextureDomain concat "." concat unlocalizedName)
      .setBlockTextureName(mod.getTextureDomain concat ":" concat unlocalizedName)
      .setCreativeTab(mod.tab)
    GameRegistry.registerBlock(block, unlocalizedName)
    if (oredict)
      OreDictionary.registerOre(unlocalizedName, block)
    return block;
  }
  def makeItem(mod: TAwesomeSauceMod, unlocalizedName: String,
    item: Item,
    oredict: Boolean): Item = {
    item
      .setUnlocalizedName(mod.getTextureDomain + "." + unlocalizedName)
      .setTextureName(
        mod.getTextureDomain + ":" + unlocalizedName)
      .setCreativeTab(mod.tab);
    GameRegistry.registerItem(item, unlocalizedName);
    if (oredict)
      OreDictionary.registerOre(unlocalizedName, item);
    return item;
  }

  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String, block: Block): Block = {
    return makeBlock(mod, unlocalizedName, block, false)
  }
}