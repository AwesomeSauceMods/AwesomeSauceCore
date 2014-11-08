package com.awesomesauce.minecraft.forge.core.lib

import cpw.mods.fml.common.ModMetadata
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.common.MinecraftForge
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
   * @return The { @link Item} with the icon that you wish to represent
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

  def getAuthor: String = "gjgfuj"

  def getDescription = "An Awesome Sauce Mod."

  def getVersion = "0.1.0"

  def getCredits = "Created by gjgfuj, with help from #minecraftforge"

  def noTab = hasTab = false

  var hasTab = true
  var tab: CreativeTabs = null
  var config: Configuration = null
  var metadata: ModMetadata

  def init()

  def preInit()

  def postInit()

  def awesomesaucepreinit(e: FMLPreInitializationEvent) = {
    config = new Configuration(e.getSuggestedConfigurationFile())
    config.load()
    if (hasTab)
      tab = new AwesomeSauceTab(getModID, getTabIconItem)
    if (metadata != null) {
      metadata.modId = getModID
      metadata.name = getModName
      metadata.version = getVersion
      metadata.authorList.add(getAuthor)
      metadata.description = getDescription
      metadata.credits = getCredits
    }
    MinecraftForge.EVENT_BUS.register(this)
    preInit()
  }

  def awesomesauceinit(e: FMLInitializationEvent) = {
    init()
  }

  def awesomesaucepostinit(e: FMLPostInitializationEvent) = {
    config.save()
  }
}