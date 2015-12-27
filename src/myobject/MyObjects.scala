package myobject

/**
 * Created by snagy on 2015.07.17..
 * Testing object behaviour:
 * - companion object members cannot be called from static methods
 */
class MyObjects(val n: Int=0) {
//  def apply(): Unit = println("Apply method called from class object")
}

object MyObjects {
  def apply(): Unit = println("Apply method called from singleton object")
}

object MyObjectTest extends App {
  val mo = new MyObjects()

}