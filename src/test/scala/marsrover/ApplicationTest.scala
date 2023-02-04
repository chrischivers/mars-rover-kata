package marsrover

class ApplicationTest extends munit.FunSuite {

  test("Application should read in starting position and command string from arguments and output in expected format") {
    assertEquals(Application.run(Array("0", "0", "NORTH", "FFLBR")), "(1, 2) NORTH")
  }

  test("Application should output an error if no arguments are not provided") {
    assertEquals(
      Application.run(Array.empty),
      "Error: Required arguments were missing. Expected format `{x} {y} {facing} {command-string}`. E.g. `0 1 NORTH FFRBL`"
    )
  }

  test("Application should output an error if the starting position X coordinate is not an integer") {
    assertEquals(
      Application.run(Array("A", "0", "NORTH", "FFLBR")),
      "Error: Invalid starting position received: [Starting 'x' coordinate is not an integer]"
    )
  }

  test("Application should output an error if the starting position Y coordinate is not an integer") {
    assertEquals(
      Application.run(Array("0", "A", "NORTH", "FFLBR")),
      "Error: Invalid starting position received: [Starting 'y' coordinate is not an integer]"
    )
  }

  test("Application should output an error if the starting position facing string is not valid") {
    assertEquals(
      Application.run(Array("0", "0", "UP", "FFLBR")),
      "Error: Invalid starting position received: [Starting direction is not valid. Valid directions are [North, East, South, West]]"
    )
  }

  test("Application should output an error if the command string contains an invalid command") {
    assertEquals(
      Application.run(Array("0", "0", "NORTH", "A")),
      "Error: Invalid command(s) received: [A]"
    )
  }

  test("Application should output an error if the command string contains multiple invalid commands") {
    assertEquals(
      Application.run(Array("0", "0", "NORTH", "FXBTLA")),
      "Error: Invalid command(s) received: [X, T, A]"
    )
  }

}
