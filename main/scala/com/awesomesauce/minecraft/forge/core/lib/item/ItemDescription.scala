package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.{Item, ItemStack}
import net.minecraft.util.{EnumChatFormatting, StatCollector}

import scala.collection.mutable.{ArrayBuffer, Map}

trait ItemDescription extends Item {
  val description = ArrayBuffer[String]();
  val usage = Map[String, String]("Crafting" -> "Nothing Special");
  var showUsage = true;

  def addDescriptionLine(string: String): ItemDescription = {
    description.append(string);
    return this;
  }
  var indevt : Boolean = false
  def indev : ItemDescription = {indevt = true; return this}
  override def addInformation(par1ItemStack: ItemStack,
    par2EntityPlayer: EntityPlayer, list: java.util.List[_], par4: Boolean) {
    val par3List = list.asInstanceOf[java.util.List[String]]
    if (indevt) par3List.add(EnumChatFormatting.RED+""+EnumChatFormatting.BOLD+ StatCollector.translateToLocal("awesomesauce.indev"))
    description.foreach(a => par3List.add(EnumChatFormatting.BLUE +""+ EnumChatFormatting.ITALIC + StatCollector.translateToLocal(a)))
    if (!showUsage)
      return ;
    /*
		 * if () {
		 * par3List.add("Press "+EnumChatFormatting.GREEN+""+EnumChatFormatting
		 * .BOLD+"Shift"+EnumChatFormatting.RESET+" to view usage."); return; }
		 */
    par3List.add("");
    par3List.add(EnumChatFormatting.YELLOW + StatCollector.translateToLocal("awesomesauce.usage"));
    usage.foreach(a => par3List.add(EnumChatFormatting.AQUA+StatCollector.translateToLocal(a._1) +EnumChatFormatting.BLUE +": " +EnumChatFormatting.GREEN+ StatCollector.translateToLocal(a._2)))

  }

  def addUsage(t: String, usage: String): ItemDescription = {
    this.usage.put(t, usage);
    return this;
  }

  def setShowUsage(hi: Boolean): ItemDescription = {
    showUsage = hi;
    return this;
  }
}

class ItemDescriptionImpl(val maxIconCount: Int) extends Item with ItemDescription with ItemTexturable {
  def this() = this(0)
}