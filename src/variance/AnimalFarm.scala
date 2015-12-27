package variance

/**
 * Created by snagy on 2015.07.15..
 * Animal farm will store animal objects
 * "A" type parameter will
 */
sealed trait AnimalFarm[+A]

case object EmptyFarm extends AnimalFarm[Nothing]

case class FarmWithAnimals[A](animal: A, animals: AnimalFarm[A]) extends AnimalFarm[A]

object AnimalFarm {
  def apply[A](animals: A*): AnimalFarm[A] = {
    if (animals.isEmpty) {
      EmptyFarm   // this is a covariant position (+A needed in AnimalFarm declaration)
    } else {
      FarmWithAnimals[A](animals.head, apply(animals.tail: _*))
    }
  }

  def add[A >:Mammals](animal: A, animalFarm: AnimalFarm[A]): AnimalFarm[A] = {
    FarmWithAnimals(animal, animalFarm)
  }

  def listAll[A](animalFarm: AnimalFarm[A]): Unit = animalFarm match {
    case EmptyFarm => println("")
    case FarmWithAnimals(a, af) => {
      println(a); listAll(af)
    }
  }
}



