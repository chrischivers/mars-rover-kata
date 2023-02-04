package marsrover.model

import enumeratum.values._
import marsrover.model.Position._
import monix.newtypes._

final case class Position(x: XCoordinate, y: YCoordinate, facing: Direction)

object Position {
  type XCoordinate = XCoordinate.Type
  object XCoordinate extends NewtypeWrapped[Int]
  type YCoordinate = YCoordinate.Type
  object YCoordinate extends NewtypeWrapped[Int]

  sealed abstract class Direction(override val value: String) extends StringEnumEntry

  object Direction extends StringEnum[Direction] {
    case object North extends Direction("NORTH")
    case object East extends Direction("EAST")
    case object South extends Direction("SOUTH")
    case object West extends Direction("WEST")

    override def values: IndexedSeq[Direction] = findValues
  }
}


