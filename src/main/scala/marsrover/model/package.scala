package marsrover

import monix.newtypes._

package object model {
  type CommandString = CommandString.Type
  object CommandString extends NewtypeWrapped[String]
}
