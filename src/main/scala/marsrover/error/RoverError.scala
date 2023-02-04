package marsrover.error

import cats.data.NonEmptyList

trait RoverError {
  val msg: String
}
case class InvalidCommands(invalidCommands: NonEmptyList[Char]) extends RoverError {
  override val msg: String =
    s"Invalid command(s) received: [${invalidCommands.toList.mkString(", ")}]"
}

case object MissingCommandString extends RoverError {
  override val msg: String = "No command string argument was supplied"
}
