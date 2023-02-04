package marsrover.parser

import cats.data.ValidatedNel
import cats.syntax.all._
import marsrover.model.Command
import marsrover.error._

object CommandStringParser {

  def parse(commandString: String): Either[RoverError, List[Command]] =
    validate(commandString).toEither.leftMap(InvalidCommands)

  private def validate(commandString: String): ValidatedNel[Char, List[Command]] =
    commandString.toList.map { commandChar =>
      Command.withValueOpt(commandChar).toValidNel(commandChar)
    }.sequence

}
