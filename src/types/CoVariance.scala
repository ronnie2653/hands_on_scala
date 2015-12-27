package types

/**
 * Created by snagy on 2015.07.18..
 */
sealed trait Stack[+A] {
  def push[B >: A](elem: B): Stack[B] = {
    NonEmptyStack[B](elem, this)
  }
}

case object EmptyStack extends Stack[Nothing]
case class NonEmptyStack[A](topElem: A, remaining: Stack[A]) extends Stack[A]

sealed trait BaseFruit
class Fruit(name: String = "Fruit") extends BaseFruit
case class Apple(name: String = "Apple") extends Fruit


object StackTest extends App {
  println("Statck test")
  val f: Fruit = new Fruit("Some Fruit")
  val a: Apple = Apple("Jonatan")
  val sa: Stack[Apple] = NonEmptyStack[Apple](a, EmptyStack)
  val sf: Stack[Fruit] = sa
  val sf2: Stack[Fruit] = sf.push[Fruit](a) // this is working because a get subtituted in Fruit type and is it working because of subtyping
  val sf3: Stack[Fruit] = sa.push[Apple](a)
  println(sf2)
}

