# emulated_microprocessor
A simple microprocessor simulator with custom assembly instructions and virtual machine code.

## Background
This program implements a simple microprocessor inspired from a programming assignment in Deitel & Deitel's "Java How to Program". More about the project is on my [blog](https://mdnaseemashraf.wordpress.com/2013/06/30/simpletron/).

## About the Simulated Architecture

It takes input of a sequence of serial instructions in mnemonic form and processess it to opcode format. This opcode is then run step by step performing the instructions.

    1. The Opcode is a 4 digit integer. The first two represent the actual Opcode/instruction and the next two are the location index (0 to 99).
    2. The program supports 19 instructions currently and can be extended to support 99 instructions.
    3. The program can handle a maximum of 100 lines of commands/instructions.
    4. The programs can also handle a maximum of 100 data items (limited to double array of 100 size) storage.
    
## Note
Refer to MNEMONIC CODEX for a list of instructions, there opcodes and functions.
