package marsrover.model

trait Rover {
  def move(commands: List[Command]): Rover
  def position: Position
}
