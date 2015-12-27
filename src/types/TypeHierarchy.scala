//package types
//
///**
// * Created by snagy on 2015.07.13..
// */
//// Type hierarchy example
//abstract class Set[A] {
//  def incl(x: A): Set[A]
//  def contains(x: A): Boolean
//}
//
//class EmptySet[A]() extends Set[A] {
//  def contains(x: A): Boolean = false
//  def incl(x: A): Set[A] = new NonEmptySet(x, new EmptySet[A], new EmptySet[A])
//}
//
//class NonEmptySet[A]
//(elem: A, left: Set[A], right: Set[A]) extends Set[A] {
//  def contains(x: A): Boolean =
//    if (x < elem) left contains x
//    else if (x > elem) right contains x
//    else true
//
//  def incl(x: A): Set[A] =
//    if (x < elem) new NonEmptySet(elem, left incl x, right)
//    else if (x > elem) new NonEmptySet(elem, left, right incl x)
//    else this
//}
//
//
//
//
//case class Num(value: Double) extends Ordered[Num] {
//  def compare(that: Num): Int =
//    if (this.value < that.value) -1
//    else if (this.value > that.value) 1
//    else 0
//}
//
//
//object TypeHierarchy extends App {
//  val set = new NonEmptySet[Num](Num(1), new EmptySet[Num], new EmptySet[Num])
//  println(set.incl(Num(2)))
//  val set2 = new NonEmptySet[Int](1, new EmptySet[Int], new EmptySet[Int])
//  println(set2.incl(2))
//
//}
