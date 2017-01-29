import java.util

/**
  * yyan 09/01/2017
  */


object day4 {
  def main(args: Array[String]): Unit = {

    println("haha")

  }

  //Exercise 1: Define and discuss what is a Sealed Trait
  sealed trait List[+A]

  case class Cons[+A](head: A, tail: List[A]) extends List[A]

  case object Nil extends List[Nothing]

  //  变性 Variance
  //    Scala的类型系统必须同时解释类层次和多态性。类层次结构可以表达子类关系。
  //  在混合OO和多态性时，一个核心问题是：如果T’是T一个子类，Container[T’]应该被看做是Container[T]的子类吗？变性（Variance）注解允许你表达类层次结构和多态类型之间的关系：
  //
  //  含义	Scala 标记
  //  协变covariant	C[T’]是 C[T] 的子类	[+T]
  //  逆变contravariant	C[T] 是 C[T’]的子类	[-T]
  //  不变invariant	C[T] 和 C[T’]无关	[T]
  //https://twitter.github.io/scala_school/zh_cn/type-basics.html

  //Exercise define sum of an entire list for Int and Double
  def sum(ints: List[Int]): Int = {
    ints match {
      case Nil => 0
      case Cons(x, xs) => x + sum(xs)
    }
  }

  def product(ds: List[Double]): Double = {
    ds match {
      case Nil => 1.0
      case Cons(0.0, _) => 0.0
      case Cons(x, xs) => x * product(xs)
    }
  }

  //Exercise: Implement function tail, takes a list and returns a list.
  def tail[A](l: List[A]): List[A] = {
    l match {
      case Nil => sys.error("tail of empty list")
      case Cons(x, xs) => xs
    }
  }

  //Exercise: Implement function that takes a list and an element of type A and returns a list.
  def setHead[A](l: List[A], newHead: A): List[A] = {
    l match {
      case Nil => sys.error("setHead on empty list")
      case Cons(_, x) => Cons(newHead, x)
    }
  }

  //Exercise: Implement function head, takes a list and returns an Object of type A,
  // being this object the first of the given list.
  def head[A](l: List[A]): A = {
    l match {
      case Nil => sys.error("head of empty list")
      case Cons(x, _) => x
    }
  }

  //Exercise: Implement a function that returns a list of the last n elements of a list
  def drop[A](l: List[A], n: Int): List[A] = {
    if (n <= 0) l
    else l match {
      case Nil => Nil
      case Cons(_, x) => drop(x, n - 1)
    }
  }

  //Exercise: Implement a function that returns a list of the first n elements of a list
  def init[A](l: List[A], n: Int): List[A] = {
    @annotation.tailrec
    def rec[A](l: List[A], recL: List[A], n: Int): List[A] = {
      if (n == 0) recL
      else l match {
        case Nil => recL
        case Cons(x, xs) => rec(xs, Cons(x, recL), n - 1)
      }
    }

    rec(l, Nil, n)
  }

  //Exercise: Implement dropWhile, which removes elements from the List prefix as long as they match a predicate.
  def dropWhile1[A](l: List[A], f: A => Boolean): List[A] = {
    l match {
      case Nil => Nil
      // else ?? how to define
      case Cons(x, xs) if (f(x)) => dropWhile1(xs, f)
      case _ => l
    }
  }


  //implementing it as a curried function improves the type inference as the comparison function gives the hint to the compiler
  def dropWhile2[A](l: List[A])(f: A => Boolean): List[A] = {
    l match {
      case Cons(x, xs) if (f(x)) => dropWhile2(xs)(f)
      case _ => l
    }
  }

  //Exercise: Implement an append function that appends an element to the end of a list
  def append[A](l: List[A], newHead: A): List[A] = {
    l match {
      case Nil => Cons(newHead, Nil)
      case Cons(x, xs) => Cons(x, append(xs, newHead))
    }
  }

  def foldRight[A, B](l: List[A], z: B)(f: (A, B) => B): B = {
    l match {
      case Nil => z
      case Cons(x, xs) => f(x, foldRight(xs, z)(f))
    }
  }

  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => foldLeft(xs, f(z, x))(f)
  }

  def length[A](l: List[A]): Int = foldLeft(l, 0)((acc, _) => acc + 1)

  //Exercise: Implement the function map that applies a given transformation to every
  // element of a given list and returns a new list
  def map[A, B](as: List[A])(f: A => B): List[B] = {
    foldRight(as, Nil: List[B])((h, t) => Cons(f(h), t))
  }

  //Exercise: Answer: Can product , implemented using foldRight , immediately halt the recursion and
  //return 0.0 if it encounters a 0.0 ? Why or why not? Consider how any short-circuiting
  //might work if you call foldRight with a large list.

  //  //Exercise: Implement a filter function that returns a new list where the elements are the ones
  //  // from the input one that satisfy the a given predicate
  //  def filter[A](as: List[A])(f: A => Boolean): List[A] = ???
  //
  //  //Exercise Implement a function that returns the length of the given list
  //
  //  //Exercise: Implement
  //  //Returns a list consisting of the first n elements of the list
  //  def take(n: Int): List[A] = ???
  //
  //  //Exercise: Implement
  //
  //  //Exercise: Implement sum function with foldLeft
  //  //Exercise: Implement product function with foldLeft
  //
  //  //Exercise: Implement append function with foldLeft
  //  //Exercise: Implement concat function with foldLeft
  //
  //  //Exercise: Implement a function that concatenates an arbitrary number lists into a new list
  //
  //  //Exercise: Implement a function thatReturns a list consisting of the longest valid prefix of this whose elements all pass the predicate f
  //  def takeWhile(f: A => Boolean): List[A] = ???
  //
  //  //Exercise: Implement a function that Returns true if and only if all elements of this pass the predicate f
  //  def forall(f: A => Boolean): Boolean = ???
  //
  //  //Exercise: Implement a zip function that takes two lists and returns a list of tuples.
  //  //
  //
  //  //Exercise: Implement a function that Returns true if any element of this passes the predicate f
  //  def exists(f: A => Boolean): Boolean = ???
  //
  //  // Exercise: Implement a function that checks whether a list contains a sub-sequence or not
  //  def hasSubsequence[A](sup: List[A], sub: List[A]): Boolean = ???

  //Factory Constructor
  def apply[A](args: A*): List[A] = // Variadic function definition, takes any number of arguments
    if (args.isEmpty) Nil
    else Cons(args.head, apply(args.tail: _*))

  // _* does an argument unrolling for passing as varargs


}


