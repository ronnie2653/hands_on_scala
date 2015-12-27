import scala.annotation.tailrec

/**
 * Topic:
 * 1. Everything is an Object
 * 2. Everything is an Expression
 * 3. Functions (are objects, currying, partial functions)
 *    why functional parameter are use full?
 * 4. built in functions on collections
 * 5. tail recursion
 * 6. Syntactic sugar for apply method and unapply
 * 7. case classes
 * 8. partial function application example
 * 9. type hierarchy example
 * 10. non strict function
 */
class ScalaEssentials_1 {
  // 1. Everything is an Object show in console
  // There is a lot of syntactic sugar the blow code defines exactly the same function object
  def f1 = (i:Int) => i%2==0
  def f3: (Int) => Boolean = _%2==0
  def f4: (Int) => Boolean = i => i%2==0
  def f6 = (i:Int) => i%2==0
  def f7: Function1[Int,Boolean] = _%2==0
  def f8(i: Int): Int => Boolean = i => i%2==0
  def f9(i: Int) = i%2==0

  // functions are ordinary object. Example:
  def lessThen: (Int,Int)=>Boolean = (a,b) => a < b
  val lessThan2 = new Function2[Int, Int, Boolean] {
    def apply(a: Int, b: Int) = a < b
  }
  // define lessThen to have method type
  def lessThen3(a: Int, b: Int) = a < b
  // we can make a function object from it a with _ (show it in console)

  // 2. Everything is an Expression, show it in console

  // 3. Functions
  // Currying: is a process that takes a function that takes two or more arguments into a function that takes one argument.
  // That function returns a function that consumes the second argument.
  def sum(a: Int, b: Int): Int = a+b
  def sum2(a: Int): Int=>Int = (b:Int)=>a+b
  // the above is method type, lets make it function type
  def sum3: (Int)=>(Int=>Int) = (a)=>((b) => a+b)
  // we can have a more conscise syntax for this
  def sum4(a: Int)(b: Int): Int = a+b
  def multip(x: Int, y: Int): Int = x*y
  def multip2(x: Int): (Int)=>Int = (y)=>x*y

  // Task: We have a sequence of pairs, lets define a function:
  //  - that get the above functions as parameter
  //  - and return a new function that able to use our function parameters on pairs
  val pairs = (1 to 5).zip(6 to 10)
  def adjustPair(f: (Int,Int)=>Int): ((Int,Int))=>Int =
    (x: (Int,Int)) => f(x._1,x._2)
  // next example for using function as parameter:
  // Task: We want to have a pair of values for every f: Int=>Int function parameter
  // as (input, output) in a given interval
  def values(f: Int=>Int, low: Int, high: Int): List[(Int,Int)] = {
    (low to high).toList map (x=>(x,f(x)))
  }

  // 4. built in function
  // factorial with reduceLeft (if n<1 result should be 0)
  def factWithReduceLeft(n: Int): Int = {
    if (n < 1) 0
    else (1 to n).reduceLeft(_*_)
  }

  // 5. tail recursion
  def fakt(n: Int): Int = {
    if (n<1) 0
    else if (n == 1) 1
    else n*fakt(n-1)
  }
  // solve it with tail recursion
  def fakt2(n: Int): Int = {
    @tailrec
    def go(acc: Int, n: Int): Int = {
      if (n<1) 0
      else if (n==1) acc
      else go(acc*n, n-1)
    }
    go(1,n)
  }

  // 6. unapply example

  // 8. partial function application
  def partialFunc: PartialFunction[Int,Boolean] = { case i if i>0 => i%2==0 }
  // partial function application: with collect method
  val l = List("hello", 1, true, "world")
  l collect { case s: String => s }
  // lift partial functions to total functions
  val two: PartialFunction[Int, String] = { case 2 => "two" }
  two(1)
  two.lift(1)
  // 10: i is evaluated to many times
  def pair(i: => Int) = (i, i)
  pair { println("hi"); 1 + 41 }
  def pair2(i: => Int) = { lazy val j = i; (j, j) }

}


object ScalaEssentials_1 extends App {

}


