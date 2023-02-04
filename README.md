## Mars Rover Kata (version 1.4)

This is an implementation to fulfil the requirements of the Mars Rover Kata exercise

### Requirements

This solution is written in Scala (2.13). 
Scala, SBT and Java are required to be installed on the local machine.

If these dependencies are not already installed, the local environment can be easily set up to run Scala projects by installing [Coursier](https://get-coursier.io/docs/cli-installation)

### To Run
When running from the command line, it expects four arguments:

1. starting 'x' coordinate
2. starting 'y' coordinate
3. starting 'facing' direction (`NORTH`, `SOUTH`, `EAST`, `WEST`)
4. command string (the instructions for the rover)

For example:
```
//Starting at (0,0) facing north, running command FFLFRB

sbt "run 0 0 NORTH FFLFRB"
```

When running within `sbt`, we can use `run ...` without the quotes.
