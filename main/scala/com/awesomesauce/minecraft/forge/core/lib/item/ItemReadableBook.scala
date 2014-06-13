package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreenBook
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemEditableBook
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.nbt.NBTTagList
import net.minecraft.nbt.NBTTagString
import net.minecraft.world.World
import net.minecraft.util.StatCollector
import net.minecraft.util.EnumChatFormatting

class ItemReadableBook(title: String, author: String) extends ItemEditableBook with ItemDescription {
  this.setShowUsage(false)
  val pages = scala.collection.mutable.ArrayBuffer[String]()
  def addPage(par1ItemStack: ItemStack, str: String) =
    {
      val pages = par1ItemStack.stackTagCompound.getTagList("pages", 8);
      pages.appendTag(new NBTTagString(str));

    }
  def addPage(page: String): ItemReadableBook =
    {
      pages += page;
      return this;
    }
  override def onCreated(book: ItemStack, world: World, player: EntityPlayer) =
    {
      book.setTagCompound(new NBTTagCompound());
      book.stackTagCompound.setString("author", author);
      book.stackTagCompound.setString("title", title);
      if (author == "You")
        book.stackTagCompound.setString("author", player.getDisplayName())
      val pages = new NBTTagList();
      for (i <- 0 to pages.tagCount()) {
        pages.appendTag(new NBTTagString(this.pages(i)));
      }
      book.stackTagCompound.setTag("pages", pages);
    }

  /**
   * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
   */
  override def onItemRightClick(par1ItemStack: ItemStack, par2World: World, par3EntityPlayer: EntityPlayer): ItemStack =
    {
      if (!par1ItemStack.hasTagCompound())
        par1ItemStack.setTagCompound(new NBTTagCompound());
      onCreated(par1ItemStack, par2World, par3EntityPlayer);
      Minecraft.getMinecraft().displayGuiScreen(new GuiScreenBook(par3EntityPlayer, par1ItemStack, false));
      return par1ItemStack;
    }
  def getItemDisplayName(par1ItemStack: ItemStack) = title

  /**
   * allows items to add custom lines of information to the mouseover description
   */
  override def addInformation(par1ItemStack: ItemStack,
    par2EntityPlayer: EntityPlayer, list: java.util.List[_], par4: Boolean) {
   // val par3List = list.asInstanceOf[java.util.List[String]]
   // par3List.add(EnumChatFormatting.GRAY + String.format(StatCollector.translateToLocalFormatted("book.byAuthor", author, null)))

    super.addInformation(par1ItemStack, par2EntityPlayer, list, par4)
  }
}