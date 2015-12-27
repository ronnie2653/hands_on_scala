package json

/**
 * Created by snagy on 2015.07.13..
 */
import scala.util.parsing.json._

object JsonProblem extends App {
  // we have a json string and have to find the biggest value
  val result = JSON.parseFull( """
    {
    "users": [
        {
            "name": "Example 1",
            "age": 20
        },
        {
            "name": "Example 2",
            "age": 42
        }
    ]
    }
                               """)

    // First transform the collection into a Map.
    val representedAsMap:Map[String,Any] = result.get.asInstanceOf[Map[String, Any]]
    val users:List[Any] = representedAsMap.get("users").get.asInstanceOf[List[Any]]

//    users.foreach( userMap => {
//        val user:Map[String,Any] = userMap.asInstanceOf[Map[String,Any]]
//        val name:String = user.get("name").get.asInstanceOf[String]
//        val age:Double = user.get("age").get.asInstanceOf[Double]
//        print(user + "\n" + name + ": " + age + "\n")
//
//    }
//    )
    val nameAgePairs = users.map( userMap => {
        val user:Map[String,Any] = userMap.asInstanceOf[Map[String,Any]]
        val name:String = user.get("name").get.asInstanceOf[String]
        val age:Double = user.get("age").get.asInstanceOf[Double]
        Tuple2(name, age)
    }
    )
    print(nameAgePairs)
    val max = nameAgePairs.foldLeft(("",0.0))((t1,t2)=>{if (t1._2>t2._2) t1 else t2})
    println(s"\nMax: ${max._2}")

}
