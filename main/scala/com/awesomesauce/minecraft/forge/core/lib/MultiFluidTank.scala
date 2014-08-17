package com.awesomesauce.minecraft.forge.core.lib

import net.minecraftforge.fluids.{FluidStack, FluidTank}

class MultiFluidTank(tankAmount: Int, tankStorage: Int) {
  val fluidTanks = scala.collection.mutable.Set[FluidTank]()
  for (i <- Range(tankAmount)) {
    fluidTanks.add(new FluidTank(tankStorage))
  }

  def insertFluid(fluid: FluidStack, simulate: Boolean) = {
    var nfluid = fluid
    for (tank <- machineTanks) {
      nfluid = new FluidStack(nfluid.getFluid, tank.fill(nfluid, simulate))
    }
    nfluid
  }

  def drainFluid(fluid: FluidStack, simulate: Boolean): FluidStack = {
    var nfluid = fluid
    for (tank <- machineTanks) {
      if (tank.getFluid.isFluidEqual(nfluid))
        nfluid = tank.drain(nfluid.amount, simulate)
    }
    nfluid
  }
}
