# Martian robots - code challenge

A team of scientists has decided to explore Mars, a very strange planet, to do so, they proposed you to
develop a tool that provided the dimensions of the exploration grid and the position and instructions assigned to
every rover, simulates the result of the expedition and also returns some statistics about it

### Why is Mars an strange planet?
The scientists say that according to their investigations, the planet is flat and hence, if a rover crosses
it's border, it's lost forever. Taking this into account, they equipped the rovers with a sophisticated system of
"warning markers" called scent, that the rovers put on the border on Mars just before falling off and when another
rover of the same mission senses it, it pays more attention to the instructions given and discard those which would
make it fall.

### How is the input encoded?
The first row is the furthest explorable position of Mars in X Y coordinates, assuming that the opposite corner
of the area is (0,0).

The next lines codify the initial position and instructions given to each rover in order. The initial position
is also a coordinate X Y followed by the cardinal point the rover is facing initially (N/E/S/W). The second line
are the instructions, coded as uppercase characters and without whitespaces in between (between 1 and 50)

They provided a list with the instructions that should be simulated:

- **F** - "Forward": moves forward one position in the direction the rover is looking.
    - Moving towards north means increase the Y coordinate in one unit
- **R** - "Right": rotates the rover 90 deg to the right without changing it's position
- **L** - "Left": rotates the rover 90 deg to the left without changing it's position

# About this solution

In this repository I provide a possible solution to this problem, I've used Springboot to create a REST service, but
the core logic of the program is intended to be easily extensible and modified to provide other kind of interfaces,
like a CLI application.

While diving in the code, it might surprise you the complexity of the internal structure of the `movement` class,
I've programmed it that way to allow the addition of new kind of movements without having to change the code. This class
has three attributes:
- **dx**: it's a lookup table that represents how much the robot will move on the East-West axis depending on it's orientation
- **dy**: another lookup table, same purpose than `dx` but applied to the North-South axis
- **newOrientation**: this table encodes which will be the next orientation based on the current one. (Useful
  when coding movements that imply rotations.

# How to run it

The repository is a InteliJ project that uses Maven to manage the dependencies and build process, this means that you have two options to run it:

1. Clone the repo and open it as a InteliJ project and run it from the IDE selecting `app.Application` as main class (recommended in case you want to debug and see how it works in detail)
2. Clone the repo and package the proyect with `mvn clean install`, after that, execute the resulting .jar with `java -jar ./target/MartianRobots-0.1.jar`

After executing the program with any of the two previous options, open a web browser and type `http://localhost:8080/`
to see a simple webpage that will allow you to test the `http://localhost:8080/solve` endpoint, which receives the input of
the problem encapsulated on a JSON object like this:
```json
{
	"text": "5 3\n1 1 E\nRFRFRFRF\n3 2 N\nFRRFLLFFRRFLL\n"
}
```
And returns another JSON object like this:
```json
{
    "success": true,
    "stats": {
        "robotStatsList": [
            {
                "pathFollowed": [ ],
                "wasLost": false,
                "leavedScent": false
            },
            {
                "pathFollowed": [ ],
                "wasLost": true,
                "leavedScent": true
            }
        ],
        "totalRobotsLost": 1,
        "totalScentsLeaved": 1,
        "totalAreaCovered": 6
    },
    "output": "1 1 E\n3 3 N LOST\n"
}
```
