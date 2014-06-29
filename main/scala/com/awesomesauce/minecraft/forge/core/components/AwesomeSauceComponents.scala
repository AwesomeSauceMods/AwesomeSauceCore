package com.awesomesauce.minecraft.forge.core.components

import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.util.ItemUtil
import cpw.mods.fml.common.{ModMetadata, Mod}
import cpw.mods.fml.common.Mod.EventHandler
import cpw.mods.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
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
  var ingotAwesomeite: Item = null
  var dustCopper: Item = null
  var dustSilver: Item = null
  var dustTin: Item = null
  var dustBronze: Item = null
  var dustAluminum: Item = null
  var dustAwesomeite: Item = null
  var dustIron: Item = null
  var dustGold: Item = null
  var flour: Item = null
  var nuggetAwesomeite: Item = null
  var blockAwesomeite: Block = null

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
    ingotAwesomeite = ItemUtil.makeItem(this, "ingotAwesomeite",
      true).addDescriptionLine(
        "awesomesaucecomponents.awesomeite.desc")

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
    dustAwesomeite = ItemUtil.makeItem(this, "dustAwesomeite",
      true).addDescriptionLine(
        "awesomesaucecomponents.awesomeite.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustAwesomeite), new ItemStack(
      ingotAwesomeite), 2F)

    dustAluminum = ItemUtil.makeItem(this, "dustAluminum", true)
      .addDescriptionLine(
        "awesomesaucecomponents.aluminum.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustAluminum), new ItemStack(ingotAluminum), 2F)

    dustBronze = ItemUtil.makeItem(this, "dustBronze", true)
      .addDescriptionLine("awesomesaucecomponents.bronze.desc")
    ItemUtil.addSmelting(this, new ItemStack(dustBronze), new ItemStack(ingotBronze), 2F)

    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(
      dustAwesomeite, 4), "dustBronze", "dustSilver", "dustAluminum",
      "dustGold", "dustIron", Items.redstone, Items.redstone,
      Items.redstone, Items.redstone))
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
    nuggetAwesomeite = ItemUtil.makeItem(this, "nuggetAwesomeite", true)
      .addDescriptionLine("awesomesaucecomponents.awesomeite.desc")
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(nuggetAwesomeite, 9), ingotAwesomeite))
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(ingotAwesomeite), "xxx", "xxx", "xxx", Character.valueOf('x'), nuggetAwesomeite))
    blockAwesomeite = ItemUtil.makeBlock(this, "blockAwesomeite", Material.iron, true).setCreativeTab(CreativeTabs.tabBlock)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(ingotAwesomeite, 9), blockAwesomeite))
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(blockAwesomeite), "xxx", "xxx", "xxx", Character.valueOf('x'), ingotAwesomeite))
  }

  def getTabIconItem: () => Item = () => ingotAwesomeite

  def getTextureDomain: String = "awesomesaucecomponents"

  def getModName: String = "AwesomeSauceComponents"

  def getModID: String = "AwesomeSauceComponents"
  @Mod.Metadata("AwesomeSauceComponents")
  var metadata : ModMetadata = null
}