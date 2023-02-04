package marsrover.model

import cats.Show
import enumeratum.values.{StringEnum, StringEnumEntry}
import marsrover.model.Position._

final case class Position(x: X, y: Y, facing: Direction)

object Position {

  implicit val showPosition: Show[Position] =
    Show.show(pos => s"(${pos.x.value}, ${pos.y.value}) ${pos.facing.value}")

  final case class X(value: Int) extends AnyVal {
    def increment: X = this.copy(value = value + 1)
    def decrement: X = this.copy(value = value - 1)
  }

  final case class Y(value: Int) extends AnyVal {
    def increment: Y = this.copy(value = value + 1)
    def decrement: Y = this.copy(value = value - 1)
  }

  sealed abstract class Direction(override val value: String) extends StringEnumEntry {
    val left: Direction
    val right: Direction
  }

  object Direction extends StringEnum[Direction] {
    case object North extends Direction("NORTH") {
      override val left: Direction  = West
      override val right: Direction = East
    }
    case object East extends Direction("EAST") {
      override val left: Direction  = North
      override val right: Direction = South
    }
    case object South extends Direction("SOUTH") {
      override val left: Direction  = East
      override val right: Direction = West
    }
    case object West extends Direction("WEST") {
      override val left: Direction  = South
      override val right: Direction = North
    }

    override def values: IndexedSeq[Direction] = findValues
  }
}
