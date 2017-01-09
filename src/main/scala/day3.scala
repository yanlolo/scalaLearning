/**
  * yyan 09/01/2017
  */


object day3 {
  def main(args: Array[String]): Unit = {

    val pp = (x: String) => x == "a"

    def p(x: String): Boolean = {
      x == "a"
    }

    def f[T](v: T): String = v match {
      case _: Int => "Int"
      case _: String => "String"
      case _ => "Uknown"
    }

    //TODO: how to define val for polymorphic?
    //    val tt = (v: A) => v match {
    //      case _: Int => "Int"
    //      case _: String => "String"
    //      case _ => "Uknown"
    //    }

  }

  //Exercise 1:
  //Write a function that finds the first occurrence of a letter in the array and returns its position
  //signature:
  def findFirst(ss: Array[String], key: String): Int = {
    (for {
      x <- (0 to ss.length - 1).toIterator
      if ss(x) == key
    } yield x).next // avoid loop through the whole array
  }

  //Exercise 2:
  //Write a function that finds the first occurrence of an object  in the array and returns its position
  // The function takes an arbitrary array type and an input comparison test function
  //signature:
  def findFirst[A](ss: Array[A], p: A => Boolean): Int = {
    def loop(n: Int): Int = {
      val res = p(ss(0))
      if (n >= ss.length) 1
      else if (p(ss(n))) n
      else loop(n + 1)
    }

    loop(0)
  }

  //Exercise 3:
  //Write a function that decides if an array of arbitrary data type is sorted or not in a given order
  //signature
  def isSorted[A](arr: Array[A], ordered: (A, A) => Boolean): Boolean = {
    for (i <- 0 to arr.length - 2) {
      if (!ordered(arr(i), arr(i + 1))) return false
    }
    return true
  }

  //Exercise 4:
  // Write a function that converts a function f of two arguments into a function of one argument that partially applies f
  def curry[A, B, C](f: (A, B) => C): A => (B => C) = {
    (a: A) => {
      (b: B) => f(a, b)
    }
  }

  //Exercise 5:
  // Write a function that reverts the operation from the previous one
  def uncurry[A, B, C](f: A => B => C): (A, B) => C = {
    (a: A, b: B) => f(a)(b)
  }

  //Exercise 6:
  //Function composition
  def compose[A, B, C](f: B => C, g: A => B): A => C = {
    (a: A) => f(g(a))
  }

}


