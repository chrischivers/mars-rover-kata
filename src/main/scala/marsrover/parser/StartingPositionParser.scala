package marsrover.parser

import cats.data.ValidatedNel
import cats.syntax.all._
import marsrover.error.{InvalidStartingPosition, RoverError}
import marsrover.model.Position
import marsrover.model.Position.{Direction, _}

object StartingPositionParser {

  def parse(x: String, y: String, facing: String): Either[RoverError, Position] = {
    validate(x, y, facing).toEither.leftMap(InvalidStartingPosition)
  }

  private def validate(x: String, y: String, facing: String): ValidatedNel[String, Position] = {
    (
      x.toIntOption.map(X).toValidNel("Starting 'x' coordinate is not an integer"),
      y.toIntOption.map(Y).toValidNel("Starting 'y' coordinate is not an integer"),
      Direction
        .withValueOpt(facing.toUpperCase)
        .toValidNel(s"Starting direction is not valid. Valid directions are [${Direction.values.mkString(", ")}]")
    ).mapN(Position(_, _, _))
  }

}
