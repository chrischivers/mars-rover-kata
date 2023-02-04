package marsrover

import marsrover.model.{Command, Position}

final case class Rover(position: Position) {
  def move(commands: List[Command]): Rover = ???
}