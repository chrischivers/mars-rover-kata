package marsrover.error

import cats.data.NonEmptyList

trait RoverError {
  val msg: String
}
case class InvalidCommands(invalidCommands: NonEmptyList[Char]) extends RoverError {
  override val msg: String =
    s"Invalid command(s) received: [${invalidCommands.toList.mkString(", ")}]"
}

case class InvalidStartingPosition(errors: NonEmptyList[String]) extends RoverError {
  override val msg: String =
    s"Invalid starting position received: [${errors.toList.mkString(", ")}]"
}

case object MissingArguments extends RoverError {
  override val msg: String =
    "Required arguments were missing. Expected format `{x} {y} {facing} {command-string}`. E.g. `0 1 NORTH FFRBL`"
}
