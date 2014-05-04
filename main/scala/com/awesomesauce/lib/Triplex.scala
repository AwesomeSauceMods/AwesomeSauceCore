/**
 *
 */
package com.awesomesauce.lib

object Triplex {
  def apply[A, B, C](o1: A, o2: B, o3: C) = { new Triplex(o1, o2, o3) }
}
class Triplex[A, B, C](o1: A, o2: B, o3:C) extends Product3[A, B, C]
{
  def _1 = o1
  def _2 = o2
  def _3 = o3
  def canEqual(that:Any):Boolean = {that.isInstanceOf[Duplex[A, B]]}}