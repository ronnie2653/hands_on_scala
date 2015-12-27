package variance

/**
 * Created by snagy on 2015.07.13..
 */
sealed trait Animal
sealed trait Mammals extends Animal
case class Crocodile(gender: String) extends Animal
case class Dog(gender: String) extends Mammals
case class Cat(gender: String) extends Mammals

object VarianceExample extends App {
  val af = AnimalFarm[Dog](Dog("A"), Dog("B"))
  AnimalFarm.listAll(af)
  val af2 = AnimalFarm[Animal](Crocodile("Amazonian"), Crocodile("African"))
  AnimalFarm.listAll(af2)
  val af3 = AnimalFarm.add(Dog("Miami"), af)
  AnimalFarm.listAll(af3)
  val af4 = AnimalFarm.add[Mammals](Dog("German"), af)
  AnimalFarm.listAll(af4)
  val af5 = AnimalFarm.add[Animal](Crocodile("C"), af)
  println(af5)
//  AnimalFarm.listAll(af5)
  // AnimalFarm()
}


