package com.awesomesauce.minecraft.forge.core.lib

import com.awesomesauce.minecraft.forge.core.lib.item.Description
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import net.minecraft.item.ItemBlock
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.entity.player.ItemTooltipEvent

@Mod(modid = "AwesomeSauceCore", name = "AwesomeSauceCore", version = "0.1.0", modLanguage = "scala")
object AwesomeSauceLib {
  @EventHandler
  def preInit(e: FMLPreInitializationEvent) = {
    MinecraftForge.EVENT_BUS.register(this)
  }

  @SubscribeEvent
  def tooltip(e: ItemTooltipEvent) = {
    val item = e.itemStack.getItem
    if (item.isInstanceOf[Description]) {
      item.asInstanceOf[Description].doTooltip(e.itemStack, e.toolTip)
    }
    else if (item.isInstanceOf[ItemBlock]) {
      val block = item.asInstanceOf[ItemBlock].field_150939_a
      if (block.isInstanceOf[Description]) {
        block.asInstanceOf[Description].doTooltip(e.itemStack, e.toolTip)
      }
    }
  }
}