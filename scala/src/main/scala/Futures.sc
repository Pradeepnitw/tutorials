import scala.concurrent.future
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration._
import scala.util.Random

def grind(beans: String): Future[String] = Future {
  println("start grinding...")
  Thread.sleep(Random.nextInt(2000))
  if (beans == "baked beans") throw new IllegalArgumentException("are you joking?")
  println("finished grinding...")
  s"ground coffee of $beans"
}

def heatWater(water: String): Future[String] = Future {
  println("heating the water now")
  Thread.sleep(Random.nextInt(2000))
  println("hot, it's hot!")
}

//def frothMilk(milk: Milk): Future[FrothedMilk] = Future {
//  println("milk frothing system engaged!")
//  Thread.sleep(Random.nextInt(2000))
//  println("shutting down milk frothing system")
//  s"frothed $milk"
//}
//
//def brew(coffee: GroundCoffee, heatedWater: Water): Future[Espresso] = Future {
//  println("happy brewing :)")
//  Thread.sleep(Random.nextInt(2000))
//  println("it's brewed!")
//  "espresso"
//}
import scala.util.{Success, Failure}
grind("baked beans").onComplete {
  case Success(ground) => println(s"got my $ground")
  case Failure(ex) => println("This grinder needs a replacement, seriously!")
}
