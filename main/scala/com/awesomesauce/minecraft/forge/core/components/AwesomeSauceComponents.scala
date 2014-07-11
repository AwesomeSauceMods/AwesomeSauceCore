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

@Mod(modid = "AwesomeSauceComponents", name = "AwesomeSauceComponents", version = "0.1.0", modLanguage = "scala")
object AwesomeSauceComponents extends TAwesomeSauceMod {
  var grinderRecipes: scala.collection.mutable.Map[String, ItemStack] = null

  @EventHandler
  def aspri(e: FMLPreInitializationEvent) = super.awesomesaucepreinit(e)

  @EventHandler
  def asi(e: FMLInitializationEvent) = super.awesomesauceinit(e)

  @EventHandler
  def aspoi(e: FMLPostInitializationEvent) = super.awesomesaucepostinit(e)

  noTab
  tab = CreativeTabs.tabMaterials
  var ingotAluminum: Item = null
  var ingotBronze: Item = null
  var ingotCopper: Item = null
  var ingotSilver: Item = null
  var ingotTin: Item = null
  var ingotImpureAwesomeite: Item = null
  var ingotPureAwesomeite: Item = null
  var dustCopper: Item = null
  var dustSilver: Item = null
  var dustTin: Item = null
  var dustBronze: Item = null
  var dustAluminum: Item = null
  var dustImpureAwesomeite: Item = null
  var dustPureAwesomeite: Item = null
  var dustIron: Item = null
  var dustGold: Item = null
  var flour: Item = null
  var nuggetImpureAwesomeite: Item = null
  var blockImpureAwesomeite: Block = null
  var nuggetPureAwesomeite: Item = null
  var blockPureAwesomeite: Block = null
  var awesomeCore: Block = null

  override def preInit = {}

  override def postInit = {
    grinderRecipes = scala.collection.mutable.Map(
      "oreIron" -> new ItemStack(AwesomeSauceComponents.dustIron, 2),
      "oreGold" -> new ItemStack(AwesomeSauceComponents.dustGold, 2),
      "oreAluminum" -> new ItemStack(AwesomeSauceComponents.dustAluminum, 2),
      "oreTin" -> new ItemStack(AwesomeSauceComponents.dustTin, 2),
      "oreCopper" -> new ItemStack(AwesomeSauceComponents.dustCopper, 2),
      "oreSilver" -> new ItemStack(AwesomeSauceComponents.dustSilver, 2),
      "wheat" -> new ItemStack(AwesomeSauceComponents.flour))
  }

  override def init() = {
    ingotAluminum = ItemUtil.makeItem(this, "ingotAluminum", true).addDescriptionLine(
      "awesomesaucecomponents.aluminum.desc")
    ingotBronze = ItemUtil.makeItem(this, "ingotBronze",
      true).addDescriptionLine(
        "awesomesaucecomponents.bronze.desc")
    ingotCopper = ItemUtil.makeItem(this, "ingotCopper",
      true).addDescriptionLine(
        "awesomesaucecomponents.copper.desc")
    ingotSilver = ItemUtil.makeItem(this, "ingotSilver",
      true).addDescriptionLine(
        "awesomesaucecomponents.silver.desc")
    ingotTin = ItemUtil.makeItem(this, "ingotTin", true)
      .addDescriptionLine(
        "awesomesaucecomponents.tin.desc")

    dustCopper = ItemUtil.makeItem(this, "dustCopper", true)
      .addDescriptionLine("awesomesaucecomponents.copper.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustCopper), new ItemStack(ingotCopper),
      2F)

    dustSilver = ItemUtil.makeItem(this, "dustSilver", true)
      .addDescriptionLine("awesomesaucecomponents.silver.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustSilver), new ItemStack(ingotSilver),
      2F)
    dustTin = ItemUtil.makeItem(this, "dustTin", true)
      .addDescriptionLine("awesomesaucecomponents.tin.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustTin), new ItemStack(ingotTin), 2F)

    dustAluminum = ItemUtil.makeItem(this, "dustAluminum", true)
      .addDescriptionLine(
        "awesomesaucecomponents.aluminum.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustAluminum), new ItemStack(ingotAluminum), 2F)

    dustBronze = ItemUtil.makeItem(this, "dustBronze", true)
      .addDescriptionLine("awesomesaucecomponents.bronze.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustBronze), new ItemStack(ingotBronze), 2F)

    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(dustBronze,
      2), "dustCopper", "dustCopper", "dustTin"))
    dustIron = ItemUtil.makeItem(this, "dustIron", true)
      .addDescriptionLine("awesomesaucecomponents.iron.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustIron),
      new ItemStack(Items.iron_ingot), 2F)
    dustGold = ItemUtil.makeItem(this, "dustGold", true)
      .addDescriptionLine("awesomesaucecomponents.gold.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustGold),
      new ItemStack(Items.gold_ingot), 2F)
    flour = ItemUtil.makeItem(this, "flour", true)
      .addDescriptionLine("awesomesaucecomponents.flour.desc").addUsage("Smelting", "Smelted into bread.")
    ItemUtil.addSmelting(this, new ItemStack(flour), new ItemStack(Items.bread), 2.0F)
    OreDictionary.registerOre("flour", flour)
    OreDictionary.registerOre("dustWheat", flour)
    OreDictionary.registerOre("wheat", Items.wheat)
    OreDictionary.registerOre("redstone", Items.redstone)
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
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(
      dustImpureAwesomeite, 4), "dustBronze", "dustSilver", "dustAluminum",
      "dustGold", "dustIron", "redstone", "redstone",
      "redstone", "redstone"))
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
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(awesomeCore), "xyx", "yyy", "xyx", Character.valueOf('x'), "ingotAwesomeite", Character.valueOf('y'), "redstone"))
  }

  def getTabIconItem: () => Item = () => ingotPureAwesomeite

  def getTextureDomain: String = "awesomesaucecomponents"

  def getModName: String = "AwesomeSauceComponents"

  def getModID: String = "AwesomeSauceComponents"

  @Mod.Metadata("AwesomeSauceComponents")
  var metadata:
  ModMetadata = null
}