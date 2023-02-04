package marsrover.parser

import cats.data.NonEmptyList
import cats.syntax.all._
import marsrover.error.InvalidStartingPosition
import marsrover.model.Position
import marsrover.model.Position.Direction._
import marsrover.model.Position.{X, Y}

class StartingPositionParserTest extends munit.FunSuite {

  test("StartingPositionParser should parse a valid starting position") {

    val position         = StartingPositionParser.parse("1", "2", "NORTH")
    val expectedPosition = Position(X(1), Y(2), North)

    assertEquals(position, expectedPosition.asRight)
  }

  test("StartingPositionParser should reject a starting position if X is not an integer") {

    val position      = StartingPositionParser.parse("A", "2", "NORTH")
    val expectedError = InvalidStartingPosition(NonEmptyList.one("Starting 'x' coordinate is not an integer"))

    assertEquals(position, expectedError.asLeft)
  }

  test("StartingPositionParser should reject a starting position if Y is not an integer") {

    val position      = StartingPositionParser.parse("1", "B", "NORTH")
    val expectedError = InvalidStartingPosition(NonEmptyList.one("Starting 'y' coordinate is not an integer"))

    assertEquals(position, expectedError.asLeft)
  }

  test("StartingPositionParser should reject a starting position if the facing direction is not valid") {

    val position = StartingPositionParser.parse("1", "2", "UP")
    val expectedError = InvalidStartingPosition(
      NonEmptyList.one("Starting direction is not valid. Valid directions are [North, East, South, West]")
    )

    assertEquals(position, expectedError.asLeft)
  }

  test("StartingPositionParser should return multiple errors if there are multiple issues") {

    val position = StartingPositionParser.parse("A", "B", "UP")
    val expectedError = InvalidStartingPosition(
      NonEmptyList.of(
        "Starting 'x' coordinate is not an integer",
        "Starting 'y' coordinate is not an integer",
        "Starting direction is not valid. Valid directions are [North, East, South, West]"
      )
    )

    assertEquals(position, expectedError.asLeft)
  }

}
