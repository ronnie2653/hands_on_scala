/**
 * Created by snagy on 2015.07.09..
 */
object AlgorithmsInScala extends App {

  /* Problem:
   * P26 (**) Generate the combinations of K distinct objects chosen from the N elements of a list.
   * In how many ways can a committee of 3 be chosen from a group of 12 people?
   * We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient).
   * For pure mathematicians, this result may be great. But we want to really generate all the possibilities.
   * Example:
   * scala> combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
   * res0: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...
   */

  val l = List(1,2,3)
  println("With 99s solution: " + P26.combinations(2,l))
  println("With our implementation solution: " + combWithMatcher(2,l))


  // solution with scala mather mechanism
  def combWithMatcher[T](n: Int, l: List[T]): List[List[T]] = l match {
    case Nil => Nil
    case k if n == 1 => l map (List(_))
    case h :: t => combWithMatcher(n-1,t).map(h::_) ++ combWithMatcher(n,t)
  }

}

// solution from the 99 scala problems page
object P26 {
  // flatMapSublists is like list.flatMap, but instead of passing each element
  // to the function, it passes successive sublists of L.
  def flatMapSublists[A,B](ls: List[A])(f: (List[A]) => List[B]): List[B] =
    ls match {
      case Nil => Nil
      case sublist@(_ :: tail) => f(sublist) ::: flatMapSublists(tail)(f)
    }

  def combinations[A](n: Int, ls: List[A]): List[List[A]] =
    if (n == 0) List(Nil)
    else flatMapSublists(ls) { sl =>
      combinations(n - 1, sl.tail) map {sl.head :: _}
    }
}
