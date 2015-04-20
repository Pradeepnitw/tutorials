package recfun
import common._

object Main {
  def main(args: Array[String]) {
    //    println("Pascal's Triangle")
    //    for (row <- 0 to 10) {
    //      for (col <- 0 to row)
    //        print(pascal(col, row) + " ")
    //      println()
    //    }

    //println(countChange(25, List(1, 5, 10)))
    println(countChange(25, List(5, 1, 10)))
  }





  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) 1
    else pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balance(left: Int, tail: List[Char]): Int =
      if (tail.isEmpty) 0
      else tail.head match {
        case '(' => balance(left + 1, tail.tail) + 1
        case ')' if (left <= 0) => Int.MinValue
        case ')' => balance(left - 1, tail.tail) - 1
        case _ => balance(left, tail.tail)
      }

    balance(0, chars) == 0
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if (coins.isEmpty || money < 0) 0
    else if (money == 0) {
      //println("return 1")
      1
    }
    else {
      //println("[" + money + ", " + coins + "][" + (money - coins.head) + ", " + coins + "][" + money + ", " + coins.tail + "]")
      countChange(money - coins.head, coins) + countChange(money, coins.tail)
    }
  }
}
