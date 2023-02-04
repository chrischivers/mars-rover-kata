package marsrover

import marsrover.model.{Command, Position}
import marsrover.model.Position._

class RoverTest extends munit.FunSuite {
  test("Rover should move forward") {
    val startingPosition = Position(XCoordinate(0), YCoordinate(0), Direction.North)
    val commands         = List(Command.Forward)
    val expectedPosition = startingPosition.copy(y = YCoordinate(1))

    assertEquals(Rover(startingPosition).move(commands), Rover(expectedPosition))
  }
}
