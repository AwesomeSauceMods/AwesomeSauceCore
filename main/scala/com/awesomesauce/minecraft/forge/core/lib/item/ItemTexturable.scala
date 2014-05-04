package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.item.Item
import net.minecraft.client.renderer.texture.IIconRegister

trait ItemTexturable extends Item {
	override def registerIcons(r:IIconRegister) = {
	}
}