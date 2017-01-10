/**
  * yyan 09/01/2017
  */


object day2 {
  def main(args: Array[String]): Unit = {

    println("haha")

    //0, 1, 2, 3, 4, 5
    //0, 1, 1, 2, 3, 5
    println(fibRec(6))

  }

  //Exercise 1
  //Write a function for Factorial in an imperative way
  def factImp(n: Int): Int = {
    //Require imposes a pre-condition to the input parameters, if non compliant will launch an Exception
    require(n >= 0)

    n match {
      case 0 => 1
      case _ => (1 to n).foldLeft(1)((x, y) => x * y)
    }
  }

  //Exercise 2.a
  //Write a function for Factorial in a recursive way, try to make it recursive
  def factRec1(n: Int): Int = {
    require(n >= 0)
    n match {
      case 0 => 1
      case x => x * factRec1(x - 1) // not tail recursive
    }
  }

  //Exercise 2.b
  //Write a function for Factorial in a recursive way, try to make it tail recursive
  def factRec2(n: Int): Int = {
    require(n >= 0)

    @annotation.tailrec // tail recursion enforced
    def fact(n: Int, acc: Int): Int = {
      n match {
        case 1 => acc
        case _ => fact(n - 1, acc * n)
      }
    }

    fact(n, 1)
  }

  //Exercise 3
  //Write a Fibonacci function in an imperative way
  //The function must find the Nth Fibonacci Element
  def fibImp(n: Int): Int = {
    require(n >= 0)
    var tmp0 = 0
    var tmp1 = 1
    for (i <- 2 to n) {
      val tmp = tmp0 + tmp1
      tmp0 = tmp1
      tmp1 = tmp
    }
    tmp1
  }

  //Exercise 4
  //Write a Fibonacci function in a recursive way, try to make it tail recursive
  def fibRec(n: Int): Int = {
    require(n >= 0)
    @annotation.tailrec // tail recursion enforced
    def fib(tmp0: Int, tmp1:Int, index: Int): Int = {
      index match {
        case 0 => 0
        case 1 => tmp1
        case _ => fib(tmp1,tmp0+tmp1, index-1)
      }
    }
    fib(0, 1, n)
  }
}


