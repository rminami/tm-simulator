# Turing machine simulator

A Turing machine simulator. \
Written for CS3052 Computational Complexity at the University of St Andrews.

## Usage

Compile the code with 

```sh
gradle build
```

Then, to quickly run the simulator with your own Turing machine source file, run

```sh
gradle run --args="-s <source_file> -i <input_file>"
```

Or, to see how many steps were needed for each input to reach an accepting state, run

```sh
gradle run --args="-s <source_file> -i <input_file> -c"
```

Examples of what a Turing machine source file and an input file should look like can be found in `tms/example.tm` and `input/example.in`. To test the CLI out, try running

```sh
gradle run --args="-s tms/example.tm -i input/example.in -c"
```

Alternatively, to generate a JAR file, simply run

```sh 
gradle jar
```

## Testing

To run unit tests for this simulator, run

```bash
gradle test
```

## License

MIT License (c) 2018 Ryosuke Minami. See the [`LICENSE`](./LICENSE) file for more details.
