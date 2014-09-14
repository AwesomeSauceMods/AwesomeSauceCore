package com.awesomesauce.minecraft.forge.core.lib

import net.minecraftforge.fluids.{FluidStack, FluidTank}

class MultiFluidTank(tankAmount: Int, tankStorage: Int) {
  val fluidTanks = scala.collection.mutable.Set[FluidTank]()
  for (i <- Range(0, tankAmount)) {
    fluidTanks.add(new FluidTank(tankStorage))
  }

  //Comment
  def insertFluid(fluid: FluidStack, simulate: Boolean) = {
    var nfluid = fluid
    for (tank <- fluidTanks) {
      nfluid = new FluidStack(nfluid.getFluid, tank.fill(nfluid, simulate))
    }
    nfluid
  }

  def drainFluid(fluid: FluidStack, simulate: Boolean): FluidStack = {
    var nfluid = fluid
    for (tank <- fluidTanks) {
      if (tank.getFluid.isFluidEqual(nfluid))
        nfluid = tank.drain(nfluid.amount, simulate)
    }
    nfluid
  }
}
