

# Game of Life using `scala.collection.concurrent.TrieMap`

This demo shows how to use a Ctrie map implemenation to code an efficient Game of Life simulation.

## Usage

Run `psbt -sbt-snapshot`. This will ensure that Scala 2.10.0-SNAPSHOT version is used.
Thank you, Paul Phillips, for your excellent script.

After that:

    bench -Dgenerations=5 -Dsidelength=400 -Dparlevel=2 -Drefreshrate=100 scaladays.GameOfLifeDemo

to run the graphical demo. You will see the number of generations simulated per second.
You can experiment by changing the parallelism level with the `parlevel` option.