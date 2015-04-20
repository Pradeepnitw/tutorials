def secondLast[A](list: List[A]): A =
  list.foldLeft(list.head, list.tail.head)((r, c) => (r._2, c))._1

def last[A](list: List[A]): A =
  list.foldLeft[A](list.head)((_, c) => c)

def get[A](list: List[A], idx: Int): A =
  list.tail.foldLeft((list.head, 0)) {
    (r, c) => if (r._2 == idx) r else (c, r._2 + 1)
  } match {
    case (result, index) if (idx == index) => result
    case _ => throw new Exception("Bad index")
  }
val l = List(1,2,3,4,5)
last(l)
get(l, 0)
secondLast(l)
