package com.awesomesauce.minecraft.forge.core.lib.item

import net.minecraft.entity.player.EntityPlayer

trait TActivatedTileEntity {
	def activate(player:EntityPlayer, side:Int, partx:Float, party:Float, partz:Float):Boolean
}