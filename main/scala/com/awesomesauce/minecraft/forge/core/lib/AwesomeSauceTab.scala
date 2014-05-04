package com.awesomesauce.minecraft.forge.core.lib

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

class AwesomeSauceTab(name:String, iconItem:() => Item) extends CreativeTabs(name) {
	def getTabIconItem():Item = iconItem()
}