package marsrover

import cats.syntax.all._
import marsrover.error._
import marsrover.model.{Command, Position, Rover}
import marsrover.parser.{CommandStringParser, StartingPositionParser}

object Application {

  private def extractParameters(args: Array[String]): Either[RoverError, (Position, List[Command])] = {
    for {
      (x, y, facing, cmdString) <- extractStringArguments(args)
      startingPosition          <- StartingPositionParser.parse(x, y, facing)
      commands                  <- CommandStringParser.parse(cmdString)
    } yield (startingPosition, commands)
  }

  private def extractStringArguments(args: Array[String]): Either[RoverError, (String, String, String, String)] = {
    args.toList match {
      case a :: b :: c :: d :: _ => (a, b, c, d).asRight
      case _                     => MissingArguments.asLeft
    }
  }

  private def renderOutput(result: Either[RoverError, Rover]): String = {
    result
      .bimap(err => s"Error: ${err.msg}", _.position.show) // Report position if successful and report error if not
      .merge
  }

  def run(args: Array[String]): String = {
    val result =
      extractParameters(args)
        .map { case (startingPosition, commands) =>
          val rover = MarsRover.init(startingPosition)
          rover.move(commands)
        }
    renderOutput(result)

  }
}
