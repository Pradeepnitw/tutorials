class ChecksumAccumulator {

  private var sum = 0

  def add(b: Int): Unit = {
    sum += b
  }

  def checksum(): Int = {
    return ~(sum & 0xFF) + 1
  }
}

var c = new ChecksumAccumulator

c.add(123)
print(c.checksum())
c.add(345)
print(c.checksum())


abstract class Graph {
  type Edge
  type Node <: NodeIntf
  abstract class NodeIntf {
    def connectWith(node: Node): Edge
  }
  def nodes: List[Node]
  def edges: List[Edge]
  def addNode: Node
}