package marsrover.model

import enumeratum.values._

sealed abstract class Command(override val value: Char) extends CharEnumEntry

object Command extends CharEnum[Command] {
  case object Forward     extends Command('F')
  case object Backwards   extends Command('B')
  case object RotateLeft  extends Command('L')
  case object RotateRight extends Command('R')

  override def values: IndexedSeq[Command] = findValues
}
