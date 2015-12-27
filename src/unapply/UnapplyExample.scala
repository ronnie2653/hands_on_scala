package unapply


/**
 * Created by snagy on 2015.07.13..
 */

// Task: we want make Twice that only match when the constructor parameter
// is an even number

class Twice(val x: Int = 0) {

}

object Twice {
  def apply(n: Int) = new Twice(n)
  def unapply(t: Twice): Option[String] = {
    if (t.x%2 == 0)
      Some(s"Value is: ${t.x}")
    else
      None
  }
}


object TwiceTest extends App {

  val tw = Twice(6)
  val tw2 = Twice(5)

  println(tw)

  println("Unaply value: " + Twice.unapply(tw2))

  tw2 match {
    case Twice(n) => println(s"Value: ${n}")
    case _ => println("No match")
  }


}


