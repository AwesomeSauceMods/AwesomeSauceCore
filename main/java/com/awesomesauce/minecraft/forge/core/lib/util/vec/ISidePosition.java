package com.awesomesauce.minecraft.forge.core.lib.util.vec;

import net.minecraft.dispenser.IPosition;
import net.minecraftforge.common.util.ForgeDirection;

public interface ISidePosition extends IPosition {
	public ForgeDirection getDirection();

}
