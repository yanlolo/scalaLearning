/**
  * yyan 09/01/2017
  */

import day3._
import org.scalatest.FlatSpec

class day3Test extends FlatSpec {

  "findFirst: the first b's index " should "be 1 " in {
    val ss = Array("a", "b", "c", "b")
    val key = "b"
    assert(findFirst(ss, key) === 1)
    //the triple equal === method is given by scalatest, it will print out nthe values that are failing in case of error
  }

  "findFirst(arbitrary array type): the first b's index " should "be 1 " in {
    val ss = Array("a", "b", "c", "b")
    val pp = (x: String) => x == "b"
    assert(findFirst(ss, pp) === 1)
  }

  "isSorted" should "true " in {
    val ss = Array(1, 2, 3, 4)
    val pp = (x: Int, y:Int) => x < y
    assert(isSorted(ss, pp) === true)
  }

  "isSorted" should "false " in {
    val ss = Array(1, 3, 2, 4)
    val pp = (x: Int, y:Int) => x < y
    assert(isSorted(ss, pp) === false)
  }



}