package marsrover

import marsrover.model.Command._
import marsrover.model.Position
import marsrover.model.Position._

class MarsRoverTest extends munit.FunSuite {

  private val startingPosition = Position(X(0), Y(0), Direction.North)

  test("MarsRover should move forwards one space") {
    val commands         = List(Forward)
    val expectedPosition = startingPosition.copy(y = Y(1))

    assertEquals(
      MarsRover(startingPosition).move(commands),
      MarsRover(expectedPosition)
    )
  }

  test("MarsRover should move backwards two spaces") {
    val commands         = List(Backwards, Backwards)
    val expectedPosition = startingPosition.copy(y = Y(-2))

    assertEquals(
      MarsRover(startingPosition).move(commands),
      MarsRover(expectedPosition)
    )
  }

  test("MarsRover should rotate right") {
    val commands         = List(RotateRight)
    val expectedPosition = startingPosition.copy(facing = Direction.East)

    assertEquals(
      MarsRover(startingPosition).move(commands),
      MarsRover(expectedPosition)
    )
  }

  test("MarsRover should rotate left") {
    val commands         = List(RotateLeft)
    val expectedPosition = startingPosition.copy(facing = Direction.West)

    assertEquals(
      MarsRover(startingPosition).move(commands),
      MarsRover(expectedPosition)
    )
  }

  test("MarsRover should perform a sequence of move commands") {
    val startingPosition = Position(X(0), Y(0), Direction.North)
    val commands = List(Forward, RotateLeft, Forward, Forward, Forward, RotateRight, Forward, RotateLeft, Backwards)
    val expectedPosition = Position(X(-2), Y(2), Direction.West)

    assertEquals(
      MarsRover(startingPosition).move(commands),
      MarsRover(expectedPosition)
    )
  }
}
