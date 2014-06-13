package com.awesomesauce.minecraft.forge.core.lib

import cpw.mods.fml.common.registry.LanguageRegistry
import net.minecraft.item.Item
import net.minecraft.creativetab.CreativeTabs
import cpw.mods.fml.common.event.FMLPreInitializationEvent
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLPostInitializationEvent
import scala.collection.mutable.ListBuffer
import net.minecraftforge.common.config.Configuration

/*
@EventHandler
def aspri(e: FMLPreInitializationEvent) = super.awesomesaucepreinit(e)
@EventHandler
def asi(e: FMLInitializationEvent) = super.awesomesauceinit(e)
@EventHandler
def aspoi(e: FMLPostInitializationEvent) = super.awesomesaucepostinit(e)
 */
trait TAwesomeSauceMod {
  def aspri(e: FMLPreInitializationEvent)
  def asi(e: FMLInitializationEvent)
  def aspoi(e: FMLPostInitializationEvent)
  /**
   * @return The {@link Item} with the icon that you wish to represent
   *         this mod.
   */
  def getTabIconItem: () => Item

  /**
   * @return The folder in which the mod's textures reside.
   */
  def getTextureDomain: String

  /**
   * @return The human readable name of the mod.
   */
  def getModName: String

  def getModID: String
  def noTab = hasTab = false
  var hasTab = true
  var tab: CreativeTabs = null
  var config: Configuration = null
  def init()
  def preInit()
  def postInit()

  def awesomesaucepreinit(e: FMLPreInitializationEvent) = {
    config = new Configuration(e.getSuggestedConfigurationFile())
    config.load()
    if (hasTab)
      tab = new AwesomeSauceTab(getModID, getTabIconItem)
    preInit()
  }
  
  def awesomesauceinit(e: FMLInitializationEvent) = {
    init()
  }

  def awesomesaucepostinit(e: FMLPostInitializationEvent) = {
    config.save()
  }
}