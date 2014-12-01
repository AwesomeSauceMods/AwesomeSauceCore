package com.awesomesauce.minecraft.forge.core.components

import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.util.ItemUtil
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import cpw.mods.fml.common.{Mod, ModMetadata}
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.{Item, ItemStack}
import net.minecraftforge.oredict.{OreDictionary, ShapedOreRecipe, ShapelessOreRecipe}

@Mod(modid = "AwesomeSauceComponents", name = "AwesomeSauceComponents", version = "0.1.0", modLanguage = "scala", dependencies = "required-after:ThermalFoundation")
object AwesomeSauceComponents extends TAwesomeSauceMod {
  var grinderRecipes: scala.collection.mutable.Map[String, ItemStack] = null
  var ingotImpureAwesomeite: Item = null
  var ingotPureAwesomeite: Item = null
  var dustImpureAwesomeite: Item = null

  noTab
  tab = CreativeTabs.tabMaterials
  var dustPureAwesomeite: Item = null
  var flour: Item = null
  var nuggetImpureAwesomeite: Item = null
  var blockImpureAwesomeite: Block = null
  var nuggetPureAwesomeite: Item = null
  var blockPureAwesomeite: Block = null
  var awesomeCore: Block = null
  @Mod.Metadata("AwesomeSauceComponents")
  var metadata:
  ModMetadata = null

  @EventHandler
  def aspri(e: FMLPreInitializationEvent) = super.awesomesaucepreinit(e)

  @EventHandler
  def asi(e: FMLInitializationEvent) = super.awesomesauceinit(e)

  @EventHandler
  def aspoi(e: FMLPostInitializationEvent) = super.awesomesaucepostinit(e)

  def ingotBronze = OreDictionary.getOres("ingotBronze").get(0).getItem

  def ingotCopper = OreDictionary.getOres("ingotCopper").get(0).getItem

  def ingotSilver = OreDictionary.getOres("ingotSilver").get(0).getItem

  def ingotTin = OreDictionary.getOres("ingotTin").get(0).getItem

  override def preInit = {}

  override def postInit = {
    grinderRecipes = scala.collection.mutable.Map(
      "oreIron" -> new ItemStack(dustIron, 2),
      "oreGold" -> new ItemStack(dustGold, 2),
      "oreTin" -> new ItemStack(dustTin, 2),
      "oreCopper" -> new ItemStack(dustCopper, 2),
      "oreSilver" -> new ItemStack(dustSilver, 2),
      "wheat" -> new ItemStack(flour))
  }

  def dustCopper = OreDictionary.getOres("dustCopper").get(0).getItem

  def dustSilver = OreDictionary.getOres("dustSilver").get(0).getItem

  def dustTin = OreDictionary.getOres("dustTin").get(0).getItem

  def dustIron = OreDictionary.getOres("dustIron").get(0).getItem

  def dustGold = OreDictionary.getOres("dustGold").get(0).getItem

  override def init() = {
    flour = ItemUtil.makeItem(this, "flour", true)
      .addDescriptionLine("awesomesaucecomponents.flour.desc").addUsage("Smelting", "Smelted into bread.")
    ItemUtil.addSmelting(this, new ItemStack(flour), new ItemStack(Items.bread), 2.0F)
    OreDictionary.registerOre("flour", flour)
    OreDictionary.registerOre("dustWheat", flour)
    OreDictionary.registerOre("wheat", Items.wheat)
    OreDictionary.registerOre("diamond", Items.diamond)
    OreDictionary.registerOre("flint", Items.flint)
    ingotImpureAwesomeite = ItemUtil.makeItem(this, "ingotImpureAwesomeite",
      true).addDescriptionLine(
        "awesomesaucecomponents.awesomeite.desc")
    OreDictionary.registerOre("ingotAwesomeite", ingotImpureAwesomeite)
    dustImpureAwesomeite = ItemUtil.makeItem(this, "dustImpureAwesomeite",
      true).addDescriptionLine(
        "awesomesaucecomponents.awesomeite.desc")
    OreDictionary.registerOre("dustAwesomeite", dustImpureAwesomeite)
    ItemUtil.addSmelting(this, new ItemStack(dustImpureAwesomeite), new ItemStack(
      ingotImpureAwesomeite), 2F)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(dustImpureAwesomeite, 6),
      "dustBronze", "dustElectrum", "dustInvar", "dustLead",
      "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone"))
    nuggetImpureAwesomeite = ItemUtil.makeItem(this, "nuggetImpureAwesomeite", true)
      .addDescriptionLine("awesomesaucecomponents.awesomeite.desc")
    OreDictionary.registerOre("nuggetAwesomeite", nuggetImpureAwesomeite)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(nuggetImpureAwesomeite, 9), "ingotImpureAwesomeite"))
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(ingotImpureAwesomeite), "xxx", "xxx", "xxx", Character.valueOf('x'), "nuggetImpureAwesomeite"))
    blockImpureAwesomeite = ItemUtil.makeBlock(this, "blockImpureAwesomeite", Material.iron, true).setCreativeTab(CreativeTabs.tabBlock)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(ingotImpureAwesomeite, 9), "blockImpureAwesomeite"))
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(blockImpureAwesomeite), "xxx", "xxx", "xxx", Character.valueOf('x'), "ingotImpureAwesomeite"))
    ingotPureAwesomeite = ItemUtil.makeItem(this, "ingotPureAwesomeite", true).addDescriptionLine("awesomesaucecomponents.awesomeite.desc").addDescriptionLine("awesomesaucecomponents.pureAwesomeite.desc")
    OreDictionary.registerOre("ingotAwesomeite", ingotPureAwesomeite)
    dustPureAwesomeite = ItemUtil.makeItem(this, "dustPureAwesomeite", true).addDescriptionLine("awesomesaucecomponents.awesomeite.desc").addDescriptionLine("awesomesaucecomponents.pureAwesomeite.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustPureAwesomeite), new ItemStack(ingotPureAwesomeite), 2F)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(dustPureAwesomeite, 6), "dustImpureAwesomeite", "dustImpureAwesomeite", "dustImpureAwesomeite", "diamond", "flint"))
    OreDictionary.registerOre("dustAwesomeite", dustPureAwesomeite)
    nuggetPureAwesomeite = ItemUtil.makeItem(this, "nuggetPureAwesomeite", true)
      .addDescriptionLine("awesomesaucecomponents.awesomeite.desc").addDescriptionLine("awesomesaucecomponents.pureAwesomeite.desc")
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(nuggetPureAwesomeite, 9), "ingotPureAwesomeite"))
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(ingotPureAwesomeite), "xxx", "xxx", "xxx", Character.valueOf('x'), "nuggetPureAwesomeite"))
    blockPureAwesomeite = ItemUtil.makeBlock(this, "blockPureAwesomeite", Material.iron, true).setCreativeTab(CreativeTabs.tabBlock)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(ingotPureAwesomeite, 9), "blockAwesomeite"))
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(blockPureAwesomeite), "xxx", "xxx", "xxx", Character.valueOf('x'), "ingotPureAwesomeite"))
    awesomeCore = ItemUtil.makeBlock(this, "awesomeCore", Material.iron, true).setCreativeTab(CreativeTabs.tabBlock)
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(awesomeCore), "xyx", "yyy", "xyx", Character.valueOf('x'), "ingotAwesomeite", Character.valueOf('y'), "dustRedstone"))
  }

  def dustBronze = OreDictionary.getOres("dustBronze").get(0).getItem

  def getTabIconItem: () => Item = () => ingotPureAwesomeite

  def getTextureDomain: String = "awesomesaucecomponents"

  def getModName: String = "AwesomeSauceComponents"

  def getModID: String = "AwesomeSauceComponents"
}
