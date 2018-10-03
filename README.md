# Turing machine simulator

A Turing machine simulator. \
Written for CS3052 Computational Complexity at the University of St Andrews.

## Usage

Compile the code with 

```sh
gradle build
```

To run all the unit tests, run

```sh
gradle test
```

To run the simulator with your own Turing machine description, run

```sh
gradle run --quiet --args="-d <description_file> -i <input_file>"
```

## License

MIT License (c) 2018 Ryosuke Minami. See the [`LICENSE`](./LICENSE) file for more details.
