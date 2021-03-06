package com.awesomesauce.minecraft.forge.core.components

import com.awesomesauce.minecraft.forge.core.lib.TAwesomeSauceMod
import com.awesomesauce.minecraft.forge.core.lib.item.Description
import com.awesomesauce.minecraft.forge.core.lib.util.ItemUtil

@Mod(modid = "AwesomeSauceComponents", name = "AwesomeSauceComponents", version = "0.1.0", modLanguage = "scala")
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
  var awesomeiteHammer: Item = null
  @Mod.Metadata("AwesomeSauceComponents")
  var metadata:
  ModMetadata = null

  @EventHandler
  def aspri(e: FMLPreInitializationEvent) = super.awesomesaucepreinit(e)

  @EventHandler
  def asi(e: FMLInitializationEvent) = super.awesomesauceinit(e)

  @EventHandler
  def aspoi(e: FMLPostInitializationEvent) = super.awesomesaucepostinit(e)

  def ingotBronze = try {
    OreDictionary.getOres("ingotBronze").get(0).getItem
  } catch {
    case e: Exception => null
  }

  def ingotCopper = try {
    OreDictionary.getOres("ingotCopper").get(0).getItem
  } catch {
    case e: Exception => null
  }

  def ingotSilver = try {
    OreDictionary.getOres("ingotSilver").get(0).getItem
  } catch {
    case e: Exception => null
  }

  def ingotTin = try {
    OreDictionary.getOres("ingotTin").get(0).getItem
  } catch {
    case e: Exception => null
  }

  override def preInit = {}

  override def postInit = {
    try {
      grinderRecipes = scala.collection.mutable.Map(
        "oreIron" -> new ItemStack(dustIron, 2),
        "oreGold" -> new ItemStack(dustGold, 2),
        "oreTin" -> new ItemStack(dustTin, 2),
        "oreCopper" -> new ItemStack(dustCopper, 2),
        "oreSilver" -> new ItemStack(dustSilver, 2),
        "wheat" -> new ItemStack(flour))
    }
    catch {
      case e: Exception => {}
    }
  }

  def dustCopper = try {
    OreDictionary.getOres("dustCopper").get(0).getItem
  } catch {
    case e: Exception => null
  }

  def dustSilver = try {
    OreDictionary.getOres("dustSilver").get(0).getItem
  } catch {
    case e: Exception => null
  }

  def dustTin = try {
    OreDictionary.getOres("dustTin").get(0).getItem
  } catch {
    case e: Exception => null
  }

  def dustIron = try {
    OreDictionary.getOres("dustIron").get(0).getItem
  } catch {
    case e: Exception => null
  }

  def dustGold = try {
    OreDictionary.getOres("dustGold").get(0).getItem
  } catch {
    case e: Exception => null
  }

  override def init() = {
    flour = ItemUtil.makeItem(this, "flour", true)
    flour.asInstanceOf[Description].addUsage("Smelting", "Smelted into bread.")
    ItemUtil.addSmelting(this, new ItemStack(flour), new ItemStack(Items.bread), 2.0F)
    OreDictionary.registerOre("flour", flour)
    OreDictionary.registerOre("dustWheat", flour)
    OreDictionary.registerOre("wheat", Items.wheat)
    OreDictionary.registerOre("diamond", Items.diamond)
    OreDictionary.registerOre("flint", Items.flint)
    ingotImpureAwesomeite = ItemUtil.makeItem(this, "ingotImpureAwesomeite",
      true)
    ItemUtil.addDescription(this, "awesomeite", ingotImpureAwesomeite.asInstanceOf[Description])
    OreDictionary.registerOre("ingotAwesomeite", ingotImpureAwesomeite)
    dustImpureAwesomeite = ItemUtil.makeItem(this, "dustImpureAwesomeite",
      true)
    ItemUtil.addDescription(this, "awesomeite", dustImpureAwesomeite.asInstanceOf[Description])
    OreDictionary.registerOre("dustAwesomeite", dustImpureAwesomeite)
    ItemUtil.addSmelting(this, new ItemStack(dustImpureAwesomeite), new ItemStack(
      ingotImpureAwesomeite), 2F)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(dustImpureAwesomeite, 6),
      "dustBronze", "dustElectrum", "dustInvar", "dustLead",
      "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone"))
    nuggetImpureAwesomeite = ItemUtil.makeItem(this, "nuggetImpureAwesomeite", true)
    ItemUtil.addDescription(this, "awesomeite", nuggetImpureAwesomeite.asInstanceOf[Description])
    OreDictionary.registerOre("nuggetAwesomeite", nuggetImpureAwesomeite)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(nuggetImpureAwesomeite, 9), "ingotImpureAwesomeite"))
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(ingotImpureAwesomeite), "xxx", "xxx", "xxx", Character.valueOf('x'), "nuggetImpureAwesomeite"))
    blockImpureAwesomeite = ItemUtil.makeBlock(this, "blockImpureAwesomeite", Material.iron, true).setCreativeTab(CreativeTabs.tabBlock)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(ingotImpureAwesomeite, 9), "blockImpureAwesomeite"))
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(blockImpureAwesomeite), "xxx", "xxx", "xxx", Character.valueOf('x'), "ingotImpureAwesomeite"))
    awesomeiteHammer = ItemUtil.makeItem(this, "awesomeiteHammer", true)
    ItemUtil.addRecipe(this, new ShapedOreRecipe(new ItemStack(awesomeiteHammer), "xx", "yx", Character.valueOf('x'), "nuggetAwesomeite", Character.valueOf('y'), "stickWood"))
    if (OreDictionary.getOres("ingotBronze").size() > 0 && OreDictionary.getOres("ingotElectrum").size() > 0 && OreDictionary.getOres("ingotInvar").size() > 0 && OreDictionary.getOres("ingotLead").size() > 0) {
      ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(dustImpureAwesomeite, 6),
        "dustBronze", "dustElectrum", "dustInvar", "dustLead",
        "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone"))
      ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(dustImpureAwesomeite, 6), "ingotBronze", "ingotElectrum", "ingotInvar", "ingotLead",
        "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone", "awesomeiteHammer"))
    }
    else {
      ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(dustImpureAwesomeite, 2), "ingotIron", "ingotGold", "dustRedstone", "dustRedstone", "dustRedstone", "dustRedstone"))
    }
    ingotPureAwesomeite = ItemUtil.makeItem(this, "ingotPureAwesomeite", true)
    ItemUtil.addDescription(this, "pureAwesomeite", ItemUtil.addDescription(this, "awesomeite", ingotPureAwesomeite.asInstanceOf[Description]))
    OreDictionary.registerOre("ingotAwesomeite", ingotPureAwesomeite)
    dustPureAwesomeite = ItemUtil.makeItem(this, "dustPureAwesomeite", true)
    ItemUtil.addDescription(this, "pureAwesomeite", ItemUtil.addDescription(this, "awesomeite", dustPureAwesomeite.asInstanceOf[Description]))
    ItemUtil.addSmelting(this, new ItemStack(dustPureAwesomeite), new ItemStack(ingotPureAwesomeite), 2F)
    ItemUtil.addRecipe(this, new ShapelessOreRecipe(new ItemStack(dustPureAwesomeite, 6), "dustImpureAwesomeite", "dustImpureAwesomeite", "dustImpureAwesomeite", "diamond", "flint"))
    OreDictionary.registerOre("dustAwesomeite", dustPureAwesomeite)
    nuggetPureAwesomeite = ItemUtil.makeItem(this, "nuggetPureAwesomeite", true)
    OreDictionary.registerOre("nuggetAwesomeite", nuggetPureAwesomeite)
    ItemUtil.addDescription(this, "pureAwesomeite", ItemUtil.addDescription(this, "awesomeite", nuggetPureAwesomeite.asInstanceOf[Description]))
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
