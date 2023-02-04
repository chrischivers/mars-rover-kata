package marsrover

import cats.data.ValidatedNel
import cats.syntax.all._
import marsrover.model.Command

object CommandStringParser {

  def parse(commandString: String): ValidatedNel[Char, List[Command]] =
    commandString.toList.map { commandChar =>
      Command.withValueOpt(commandChar).toValidNel(commandChar)
    }.sequence

}
