package marsrover

class ApplicationTest extends munit.FunSuite {

  test("Application should read in command string from first argument and output in expected format") {
    assertEquals(Application.run(Array("FFLBR")), "(1, 2) NORTH")
  }

  test("Application should output an error if the command string is not provided") {
    assertEquals(
      Application.run(Array.empty),
      "Error: No command string argument was supplied"
    )
  }

  test("Application should output an error if the command string contains an invalid command") {
    assertEquals(
      Application.run(Array("A")),
      "Error: Invalid command(s) received: [A]"
    )
  }

  test("Application should output an error if the command string contains multiple invalid commands") {
    assertEquals(
      Application.run(Array("FXBTLA")),
      "Error: Invalid command(s) received: [X, T, A]"
    )
  }

}
