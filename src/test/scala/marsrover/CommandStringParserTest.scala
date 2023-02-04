package marsrover

import cats.data.NonEmptyList
import cats.syntax.all._
import marsrover.model.Command
import marsrover.model.Command._

class CommandStringParserTest extends munit.FunSuite {
  test("CommandStringParser should parse a valid CommandString") {
    val commandString = "FBRLFF"
    val parsedString  = CommandStringParser.parse(commandString)
    val expectedCommands: List[Command] =
      List(Forward, Backwards, RotateRight, RotateLeft, Forward, Forward)

    assertEquals(parsedString, expectedCommands.validNel)
  }

  test("CommandStringParser should reject invalid commands") {
    val commandString = "FBRLXFT"
    val parsedString  = CommandStringParser.parse(commandString)

    assertEquals(parsedString, NonEmptyList.of('X', 'T').invalid)
  }

}
