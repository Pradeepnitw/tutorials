val x = new Rational(1, 3)
val y = new Rational(5, 7)
val z = new Rational(3, 2)
x.numer
x.denom
val strange = new Rational(1, 0)
strange.add(strange)

class Rational(x: Int, y: Int) {
  require(y > 0, "denominator must be positive")
  /**
   * Greatest common divider
   * @param a
   * @param b
   * @return Greatest Common Divider
   */
  private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
  private val g = gcd(x, y)
  def numer = x / g
  def denom = y / g

  def less(that: Rational) =
    this.numer * that.denom < that.numer * this.denom
  def max(that: Rational) = if (this.less(that)) this else that
  def add(that: Rational) =
    new Rational(
      numer * that.denom + that.numer * denom,
      denom * that.denom)
  def neg: Rational = new Rational(-numer, denom)
  def sub(that: Rational): Rational = add(that.neg)
  override def toString = numer + "/" + denom
}