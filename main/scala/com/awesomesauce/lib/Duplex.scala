package com.awesomesauce.lib

object Duplex {
  def apply[A, B](o1: A, o2: B) = { new Duplex(o1, o2) }
}
class Duplex[A, B](o1: A, o2: B) extends Product2[A, B] {
  def _1 = o1
  def _2 = o2
  def canEqual(that:Any):Boolean = {that.isInstanceOf[Duplex[A, B]]}
}