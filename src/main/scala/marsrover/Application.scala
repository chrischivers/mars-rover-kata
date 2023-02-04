package marsrover

import marsrover.model.{Command, Position, Rover}
import marsrover.model.Position.{Direction, X, Y}
import cats.syntax.all._
import marsrover.error._

object Application {

  // Starting position could be passed in as an argument if required
  private val startingPosition = Position(X(0), Y(0), Direction.North)

  private def renderOutput(result: Either[RoverError, Rover]): String =
    result
      .bimap(err => s"Error: ${err.msg}", _.position.show)
      .merge

  private def extractCommands(
      args: Array[String]
  ): Either[RoverError, List[Command]] = for {
    commandString <- args.headOption.toRight(MissingCommandString)
    commands <- CommandStringParser
      .parse(commandString)
      .toEither
      .leftMap(InvalidCommands)
  } yield commands

  def run(args: Array[String]): String = {
    val result =
      extractCommands(args).map(MarsRover(startingPosition).move)
    renderOutput(result)

  }
}
