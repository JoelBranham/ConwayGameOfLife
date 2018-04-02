# ConwayGameOfLife
 Adaptation of Conway's Game of Life simulation using Java and JavaFX

![Pulsar-Shape Animation](https://imgur.com/eKv4GUj.gif)


Conway's Game of Life is a "zero-player" game. The game consists of a grid filled with units. Units are either alive or dead. The following
four rules determine the evolution of the units:
1. An alive unit that has one or fewer live neighbors dies.
2. An alive unit that has two or three live neighbors lives.
3. An alive unit that has four or more live neighbors dies.
4. A dead unit with three live neighbors comes to life.
These four simple rules can generate some truly beautiful and mesmerizing pictures. Some states create infinite loops of patterns. 
One of these is the "pulsar" shown above. It rotates between three states.

The GUI for this project was developed using JavaFX. Currently, the initial state of the units is determined by passing parameters to the GridModel object
in the GameOfLife.java. These parameters can either be a filename or a desired number of rows, columns, and units. 

![Spaceships-Shape Animation](https://imgur.com/pU7BHqv.gif)

This pattern begins with two "spaceships" flying towards eachother and ends with four infinite bar-oscillation shapes.
