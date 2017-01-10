/**
  * yyan 10/01/2017
  */

import day2._
import org.scalatest.FlatSpec

class day2Test extends FlatSpec {

  "factImp " should "be 6 " in {
    assert(factImp(3) === 6)
    //the triple equal === method is given by scalatest, it will print out nthe values that are failing in case of error
  }

  "factRec1 " should "be 24 " in {
    assert(factRec1(4) === 24)
  }

  "factRec2" should "be 24 " in {
    val ss = Array(1, 2, 3, 4)
    val pp = (x: Int, y:Int) => x < y
    assert(factRec2(4) === 24)
  }

  "fibImp" should "be 8 " in {
    assert(fibImp(6) === 8)
  }

  "fibRec" should "be 8 " in {
    assert(fibRec(6) === 8)
  }

}