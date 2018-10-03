# Turing machine simulator

A simulator for Turing machines.

## Usage

Compile the code with 

```sh
gradle build
```

Run all unit tests with

```sh
gradle test
```

To run the simulator with your own Turing machine description, run

```sh
gradle run --args="-d <description_file> -i <input_file>"
```