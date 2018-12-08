# Turing machine simulator

[![Build Status](https://travis-ci.com/rminami/tm-simulator.svg?branch=master)](https://travis-ci.com/rminami/tm-simulator)
[![Known Vulnerabilities](https://snyk.io/test/github/rminami/tm-simulator/badge.svg?targetFile=build.gradle)](https://snyk.io/test/github/rminami/tm-simulator?targetFile=build.gradle)
[![Maintainability](https://api.codeclimate.com/v1/badges/6aa69bb53a1b62193cee/maintainability)](https://codeclimate.com/github/rminami/tm-simulator/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/6aa69bb53a1b62193cee/test_coverage)](https://codeclimate.com/github/rminami/tm-simulator/test_coverage)

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

Add the `-c` flag to see how many steps were needed for each input to reach an accepting state. Examples of a source file and an input file can be found in `tms/example.tm` and `input/example.in`.

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
