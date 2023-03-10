package marsrover

import marsrover.model.Position.Direction._
import marsrover.model.{Command, Position, Rover}
import monocle.syntax.all._

object MarsRover {
  def init(startingPosition: Position): Rover = MarsRover(startingPosition)
}

private[marsrover] case class MarsRover(position: Position) extends Rover {

  private def moveNorth: MarsRover = this.focus(_.position.y).modify(_.increment)
  private def moveSouth: MarsRover = this.focus(_.position.y).modify(_.decrement)
  private def moveEast: MarsRover  = this.focus(_.position.x).modify(_.increment)
  private def moveWest: MarsRover  = this.focus(_.position.x).modify(_.decrement)

  private def forward: MarsRover = position.facing match {
    case North => moveNorth
    case South => moveSouth
    case East  => moveEast
    case West  => moveWest
  }

  private def backward: MarsRover = position.facing match {
    case North => moveSouth
    case South => moveNorth
    case East  => moveWest
    case West  => moveEast
  }

  private def rotateLeft: MarsRover = this.focus(_.position.facing).modify(_.left)

  private def rotateRight: MarsRover = this.focus(_.position.facing).modify(_.right)

  override def move(commands: List[Command]): MarsRover =
    commands.foldLeft(this) { (state, command) =>
      command match {
        case Command.Forward     => state.forward
        case Command.Backwards   => state.backward
        case Command.RotateLeft  => state.rotateLeft
        case Command.RotateRight => state.rotateRight
      }
    }
}
