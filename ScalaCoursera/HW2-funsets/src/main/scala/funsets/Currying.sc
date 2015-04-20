//Find a fixed point of a function

def fixedPoint(f: Double => Double)(firstGuess: Double) = {
  def isCloseEnough(a: Double, b: Double) = {
    Math.abs(a-b)/b < 0.05
  }

  def iterate(guess: Double): Double = {
    println(guess)
    if (isCloseEnough(f(guess), guess)) f(guess)
    else iterate(f(guess))
  }

  iterate(firstGuess)
}
println(fixedPoint(x => 1 + x/2)(1))
// iterate(1)
// if (isCloseEnough(f(1), 1)) f(1)
// if (isCloseEnough(1.5, 1)) 1.5
// else iterate(1.5)
//      if (isCloseEnough(f(1.5), 1.5) f(1.5)
//      if (isCloseEnough(1.75, 1.5) 1.75
//      else iterate(1.75)
//           if (isCloseEnough(f(1.75), 1.75) f(1.75)
//           if (isCloseEnough(1.875, 1.75) 1.875
//           else iterate(1.875)
//                if (isCloseEnough(1.9375, 1.875) 1.9375
//                else iterate(1.9375)


def sqrt(x: Double) = fixedPoint(y => x/y)(1)