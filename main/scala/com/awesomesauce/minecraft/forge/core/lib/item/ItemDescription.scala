package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.block.Block
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.{EnumChatFormatting, StatCollector}

import scala.collection.mutable.ArrayBuffer

trait Description {
  val description = ArrayBuffer[String]()
  val usage = scala.collection.mutable.Map[String, String]("Crafting" -> "Nothing Special")
  var showUsage = false
  var indevt: Boolean = false

  def addDescriptionLine(string: String): Description = {
    description.append(string)
    this
  }

  def indev: Description = {
    indevt = true
    this
  }

  def doTooltip(stack: ItemStack, list: java.util.List[String]) {
    val par3List = list.asInstanceOf[java.util.List[String]]
    if (indevt) par3List.add(EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + StatCollector.translateToLocal("awesomesauce.indev"))
    description.foreach(a => par3List.add(EnumChatFormatting.BLUE + "" + EnumChatFormatting.ITALIC + StatCollector.translateToLocal(a)))
    if (!showUsage)
      return
    /*
		 * if () {
		 * par3List.add("Press "+EnumChatFormatting.GREEN+""+EnumChatFormatting
		 * .BOLD+"Shift"+EnumChatFormatting.RESET+" to view usage."); return; }
		 */
    par3List.add("")
    par3List.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("awesomesauce.usage"))
    usage.foreach(a => par3List.add(EnumChatFormatting.AQUA + StatCollector.translateToLocal(a._1) + EnumChatFormatting.BLUE + ": " + EnumChatFormatting.GREEN + StatCollector.translateToLocal(a._2)))

  }

  def addUsage(t: String, usage: String): Description = {
    showUsage = true
    this.usage.put(t, usage)
    this
  }

  def setShowUsage(hi: Boolean): Description = {
    showUsage = hi
    this
  }
}

trait ItemDescription extends Item with Description {

}

trait BlockDescription extends Block with Description {

}

class ItemSimple(val extraIconCount: Int) extends Item with ItemDescription with ItemTexturable {
  def this() = this(0)

  def getIconStr = this.getIconString

  def getItemIcon = this.itemIcon
}