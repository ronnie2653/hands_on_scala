package collection

import scala.collection.immutable.ListMap

/**
 * Created by snagy on 2015.07.22..
 */

object CollectionExercises extends App {
  val l = List("a" -> 1, "b" -> 2, "a" -> 3, "c" -> 4, "b" -> 5)
  val m = l.toMap
  println(m.mkString(", "))
  val m2 = l.groupBy((x) => x._1)
  println(m2.mkString(", "))
  val m3 = m2.map((x) => x._1 -> x._2.map(_._2))
  println(m3.mkString(","))
  val m4 = m3.sortMap()
  println(m4.mkString(","))

  implicit class MapSort[A, B](m: Map[A, B])(implicit ev1: A => Ordered[A]) {
    def sortMap(): Map[A, B] = ListMap(m.toSeq.sortWith(_._1 > _._1): _*)
  }

  // implicit parameters and conversation
  abstract class SemiGroup[A] {
    def add(x: A, y: A): A
  }

  abstract class Monoid[A] extends SemiGroup[A] {
    def unit: A
  }

  implicit object StringMonoid extends Monoid[String] {
    override def unit: String = ""

    override def add(x: String, y: String): String = x + y
  }
  implicit object IntMonoid extends Monoid[Int] {
    override def unit: Int = 0

    override def add(x: Int, y: Int): Int = x+y
  }

  // write a sum method that will work on a List[A] and using the above monoids
  // to compute the result
  def sum[A](l: List[A])(implicit monoid: Monoid[A]): A = {
    if (l.isEmpty) monoid.unit
    else monoid.add(l.head, sum(l.tail)(monoid))
  }
  val l2 = List(1,2,3)
  println(sum[Int](l2)(IntMonoid))
  val l3 = List("1","2","3")
  println(sum[String](l3))




}
