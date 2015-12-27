/**
 * Topic:
 * 1. apply function for object craation and higher order function example
 * 2. Error handling without exceptions
 * 3. ADT
 * 4. Decoupling parts of code
 * 5. Partial function and application (collect)
 */
class ScalaEssentials_2 {

  // 1. We want to have a pair of values for f: Int=>Int function parameter as (input, output)
  // in a given interval
  def values(f: Int=>Int, low: Int, high: Int): List[(Int,Int)] = {
    (low to high).map(v=>(v,f(v))).toList
  }

}


object ScalaEssentials_2 extends App {
  // 1.
  val se2_1: ScalaEssentials_2 = new ScalaEssentials_2()
  print(s"Running se2_1 ${se2_1.getClass}\n")
  // apply function
  def apply(): ScalaEssentials_2 = {
    new ScalaEssentials_2()
  }
  val se2_2: ScalaEssentials_2 = ScalaEssentials_2()
  print(s"Running se2_2 ${se2_2.getClass}\n")
  // using function as parameter
  def duplicate(n: Int): Int = n*2
  print(se2_2.values(duplicate, 5,10))
  // 5.
  def partialFunc: PartialFunction[Int,Boolean] = { case i if i>0 => i%2==0 }
  // partial function application: with collect method
  val l = List("hello", 1, true, "world")
  l collect { case s: String => s }

}
