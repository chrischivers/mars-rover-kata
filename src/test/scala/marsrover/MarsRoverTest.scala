package marsrover

import marsrover.model.Command._
import marsrover.model.Position
import marsrover.model.Position.Direction.West
import marsrover.model.Position.{Y, _}

class MarsRoverTest extends munit.FunSuite {

  private val defaultStartingPosition = Position(X(0), Y(0), Direction.North)

  test("MarsRover should move forwards one space") {
    val commands         = List(Forward)
    val expectedPosition = defaultStartingPosition.copy(y = Y(1))

    assertEquals(
      MarsRover(defaultStartingPosition).move(commands).position,
      expectedPosition
    )
  }

  test("MarsRover should move backwards two spaces") {
    val commands         = List(Backwards, Backwards)
    val expectedPosition = defaultStartingPosition.copy(y = Y(-2))

    assertEquals(
      MarsRover(defaultStartingPosition).move(commands).position,
      expectedPosition
    )
  }

  test("MarsRover should move forwards from a different starting position") {
    val startingPosition = Position(X(-5), Y(-5), West)
    val commands         = List(Forward)
    val expectedPosition = startingPosition.copy(x = X(-6))

    assertEquals(
      MarsRover(startingPosition).move(commands).position,
      expectedPosition
    )
  }

  test("MarsRover should rotate right") {
    val commands         = List(RotateRight)
    val expectedPosition = defaultStartingPosition.copy(facing = Direction.East)

    assertEquals(
      MarsRover(defaultStartingPosition).move(commands).position,
      expectedPosition
    )
  }

  test("MarsRover should rotate left") {
    val commands         = List(RotateLeft)
    val expectedPosition = defaultStartingPosition.copy(facing = Direction.West)

    assertEquals(
      MarsRover(defaultStartingPosition).move(commands).position,
      expectedPosition
    )
  }

  test("MarsRover should rotate 360") {
    val commands         = List(RotateLeft, RotateLeft, RotateLeft, RotateLeft)
    val expectedPosition = defaultStartingPosition

    assertEquals(
      MarsRover(defaultStartingPosition).move(commands).position,
      expectedPosition
    )
  }

  test("MarsRover should perform a sequence of move commands") {
    val commands = List(Forward, RotateLeft, Forward, Forward, Forward, RotateRight, Forward, RotateLeft, Backwards)
    val expectedPosition = Position(X(-2), Y(2), Direction.West)

    assertEquals(
      MarsRover(defaultStartingPosition).move(commands).position,
      expectedPosition
    )
  }
}
