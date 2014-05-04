package com.awesomesauce.minecraft.forge.core.lib.util.vec;

import net.minecraft.dispenser.IPosition;
import net.minecraft.dispenser.PositionImpl;
import net.minecraftforge.common.util.ForgeDirection;
/**
 * Position in the style of the vanilla classes.
 * @author gjgfuj
 *
 */
public class SidePositionImpl implements ISidePosition
{
	
	public IPosition			position;
	public ForgeDirection	direction;
	
	public SidePositionImpl(int x, int y, int z, ForgeDirection dir)
	{
		this(new PositionImpl(x, y, z), dir);
	}
	
	public SidePositionImpl(int x, int y, int z, int dir)
	{
		this(new PositionImpl(x, y, z), ForgeDirection.getOrientation(dir));
	}
	
	public SidePositionImpl(IPosition loc, ForgeDirection dir)
	{
		position = loc;
		direction = dir;
	}
	
	public SidePositionImpl(IPosition loc, int dir)
	{
		this(loc, ForgeDirection.getOrientation(dir));
	}
	
	public PositionImpl getOffsetPositionImpl()
	{
		return new PositionImpl(position.getX() + direction.offsetX, position.getY()
				+ direction.offsetY, position.getZ() + direction.offsetZ);
	}
	public static ForgeDirection getSide(PositionImpl from, PositionImpl to)
	{
		if (to.getX() > from.getX())
		{
			return ForgeDirection.EAST;
		}
		if (to.getX() < from.getX())
		{
			return ForgeDirection.WEST;
		}
		if (to.getY() > from.getY())
		{
			return ForgeDirection.UP;
		}
		if (to.getY() < from.getY())
		{
			return ForgeDirection.DOWN;
		}
		if (to.getZ() > from.getZ())
		{
			return ForgeDirection.SOUTH;
		}
		if (to.getZ() < from.getZ())
		{
			return ForgeDirection.NORTH;
		}
		return ForgeDirection.UNKNOWN;
	}
	public ForgeDirection getDirection()
	{
		return direction;
	}
	@Override
	public double getX() {
		return position.getX();
	}
	@Override
	public double getY() {
		return position.getX();
	}
	@Override
	public double getZ() {
		return position.getX();
	}
	public String toString() {
		return ""+Double.toString(getX())+","+Double.toString(getY())+","+Double.toString(getZ())+","+getDirection().toString();
	}
}
