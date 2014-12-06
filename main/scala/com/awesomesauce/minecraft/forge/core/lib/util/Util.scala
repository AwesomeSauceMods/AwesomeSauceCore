package com.awesomesauce.minecraft.forge.core.lib.util

import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.item._
import cpw.mods.fml.common.registry.GameRegistry
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.{Blocks, Items}
import net.minecraft.inventory.IInventory
import net.minecraft.item.crafting.IRecipe
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.stats.Achievement
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{ChatComponentText, ChunkCoordinates, StatCollector}
import net.minecraft.world.{IBlockAccess, World}
import net.minecraftforge.common.util.ForgeDirection
import net.minecraftforge.oredict.OreDictionary

object WorldUtil {

  def setHollowCube(world: World, veto: Block, container: Block,
                    mx: Int, my: Int, mz: Int, ox: Int, oy: Int, oz: Int) = {
    setCube(world, veto, container, mx, my, mz, ox, oy, oz)
    setCube(world, veto, Blocks.air, mx + 1, my + 1, mz + 1, ox - 1, oy - 1, oz - 1)
  }

  def setCube(world: World, veto: Block, block: Block,
              mx: Int, my: Int, mz: Int, ox: Int, oy: Int, oz: Int) = {
    for (x <- mx to ox)
      for (y <- my to oy)
        for (z <- mz to oz)
          if (world.getBlock(x, y, z) != veto)
            world.setBlock(x, y, z, block);
  }

  def scanForBlockOfType(w: IBlockAccess, loc: ChunkCoordinates,
                         block: Block): Array[ChunkCoordinates] = {
    val locations = scala.collection.mutable.Set[ChunkCoordinates]()
    for (i <- ForgeDirection.values()) {
      val l = new SideCoordinates(loc, i).getOffsetChunkCoordinates
      if (w.getBlock(l.posX, l.posY, l.posZ) == block)
        locations.add(l)
    }
    return locations.toArray
  }

  def scanForBlockTileEntityInstanceOf[te](w: IBlockAccess, loc: ChunkCoordinates): Array[ChunkCoordinates] = {
    val locations = scala.collection.mutable.Set[ChunkCoordinates]()
    for (i <- ForgeDirection.values()) {
      val l = new SideCoordinates(loc, i).getOffsetChunkCoordinates
      if (w.getTileEntity(l.posX, l.posY, l.posZ).isInstanceOf[te])
        locations.add(l)
    }
    return locations.toArray
  }
}

object PlayerUtil {

  def unlockAchievement(p: EntityPlayer, a: Achievement) = {
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
      if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).isItemEqual(stack) && inventory.getStackInSlot(i).stackSize >= stack.stackSize)
        return true
    }
    return false
  }

  def addStackToInventory(inventory: IInventory, itemStack: ItemStack): Boolean = {
    for (i: Int <- 0 to inventory.getSizeInventory()) {
      if (inventory.getStackInSlot(i) == null) {
        inventory.setInventorySlotContents(i, itemStack)
        return true
      }
      if (inventory.getStackInSlot(i).isItemEqual(itemStack))
        if (inventory.getStackInSlot(i).stackSize + itemStack.stackSize <= inventory.getStackInSlot(i).getMaxStackSize()) {
          inventory.getStackInSlot(i).stackSize += itemStack.stackSize;
          return true
        } else if (inventory.getStackInSlot(i).stackSize == inventory.getStackInSlot(i).getMaxStackSize()) {

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

  def addStackToSlotInInventory(inventory: IInventory, itemStack: ItemStack, slot: Int): Boolean = {
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
  def addSmelting(mod: TAwesomeSauceMod, item: ItemStack, result: ItemStack, exp: Float) = {
    if (mod.config.get("Disable Recipes", result.getUnlocalizedName(), true).getBoolean(true)) {
      GameRegistry.addSmelting(item, result, exp)
    }
  }

  def addRecipe(mod: TAwesomeSauceMod, recipe: IRecipe) = {
    if (mod.config.get("Disable Recipes", recipe.getRecipeOutput().getUnlocalizedName(), true).getBoolean(true)) {
      GameRegistry.addRecipe(recipe)
    }
  }

  def makeBlock(mod: TAwesomeSauceMod,
                unlocalizedName: String, mat: Material,
                te: () => TileEntity): BlockDescription = makeBlock(mod, unlocalizedName, mat, te, 0)

  def makeBlock(mod: TAwesomeSauceMod,
                unlocalizedName: String, mat: Material,
                te: () => TileEntity, extraIconCount: Int): BlockDescription = {
    GameRegistry.registerTileEntity(te().getClass(), mod.getTextureDomain + "." + unlocalizedName);
    val b: BlockSimpleContainer = makeBlock(mod, unlocalizedName, new BlockSimpleContainer(mat, te, extraIconCount + mod.config.get("Block Texture Additions", unlocalizedName, 0).getInt)).asInstanceOf[BlockSimpleContainer]
    for (i <- Range(0, 6)) {
      b.setDefaultTextureForSide(i, mod.config.get("Block Texture Additions", unlocalizedName + "_side" + i, 0).getInt)
    }
    return b
  }

  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String, block: Block): Block = {
    return makeBlock(mod, unlocalizedName, block, false)
  }

  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String, mat: Material): BlockDescription = {
    return makeBlock(mod, unlocalizedName, mat, 0)
  }

  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String, mat: Material, extraIconCount: Int): BlockDescription = {
    return makeBlock(mod, unlocalizedName, mat, false, extraIconCount)
  }

  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String, mat: Material, oredict: Boolean): BlockDescription = {
    return makeBlock(mod, unlocalizedName, mat, oredict, 0)
  }

  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String, mat: Material, oredict: Boolean, extraIconCount: Int): BlockDescription = {
    val b: BlockSimple = makeBlock(mod, unlocalizedName, new BlockSimple(mat, extraIconCount + mod.config.get("Block Texture Additions", unlocalizedName, 0).getInt), oredict).asInstanceOf[BlockSimple]
    for (i <- Range(0, 6)) {
      b.setDefaultTextureForSide(i, mod.config.get("Block Texture Additions", unlocalizedName + "_side" + i, 0).getInt)
    }
    return b
  }

  def makeBlock(mod: TAwesomeSauceMod, unlocalizedName: String,
                block: Block,
                oredict: Boolean): Block = {
    if (mod.config.get("Disable Item", unlocalizedName, true).getBoolean(true)) {
      block.setBlockName(mod.getTextureDomain concat "." concat unlocalizedName)
        .setBlockTextureName(mod.getTextureDomain concat ":" concat unlocalizedName)
        .setCreativeTab(mod.tab)
      GameRegistry.registerBlock(block, unlocalizedName)
      if (oredict)
        OreDictionary.registerOre(unlocalizedName, block)
      if (block.isInstanceOf[Description])
        addDescriptions(mod, unlocalizedName, block.asInstanceOf[Description])
      return block;
    }
    return Blocks.iron_block
  }

  def addDescriptions(mod: TAwesomeSauceMod, unlocalizedName: String, description: Description) = {
    addDescription(mod, unlocalizedName, description)
  }

  def addDescription(mod: TAwesomeSauceMod, descName: String, description: Description): Description = {
    var d = 0
    while (StatCollector.translateToLocal(mod.getTextureDomain + "." + descName + ".desc." + d) != StatCollector.translateToLocal(mod.getTextureDomain + "." + unlocalizedName + ".desc." + d)) {
      description.addDescriptionLine(mod.getTextureDomain + "." + descName + ".desc." + d)
      d += 1
    }
  }

  def makeItem(mod: TAwesomeSauceMod,
               unlocalizedName: String): ItemDescription = {
    makeItem(mod, unlocalizedName, false, 0)
  }

  def makeItem(mod: TAwesomeSauceMod,
               unlocalizedName: String, maxIconCount: Int): ItemDescription = {
    makeItem(mod, unlocalizedName, false, maxIconCount)
  }

  def makeItem(mod: TAwesomeSauceMod,
               unlocalizedName: String, oredict: Boolean): ItemDescription = makeItem(mod, unlocalizedName, oredict, 0)

  def makeItem(mod: TAwesomeSauceMod,
               unlocalizedName: String, oredict: Boolean, maxIconCount: Int): ItemDescription = {
    makeItem(mod, unlocalizedName, new ItemSimple(maxIconCount), oredict).asInstanceOf[ItemDescription]
  }

  def makeItem(mod: TAwesomeSauceMod, unlocalizedName: String,
               item: Item,
               oredict: Boolean): Item = {
    if (mod.config.get("Disable Item", unlocalizedName, true).getBoolean(true)) {
      item
        .setUnlocalizedName(mod.getTextureDomain + "." + unlocalizedName)
        .setTextureName(
          mod.getTextureDomain + ":" + unlocalizedName)
        .setCreativeTab(mod.tab);
      GameRegistry.registerItem(item, unlocalizedName);
      if (oredict)
        OreDictionary.registerOre(unlocalizedName, item);
      if (item.isInstanceOf[Description]) {
        addDescriptions(mod, unlocalizedName, item.asInstanceOf[Description])
      }
      return item;
    }
    return Items.iron_ingot
  }

  def makeItem(mod: TAwesomeSauceMod, unlocalizedName: String, item: Item): Item = {
    makeItem(mod, unlocalizedName, item, false);
  }
}
