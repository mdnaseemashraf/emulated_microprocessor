#MNEMONIC CODEX

The Opcode is a 4 digit integer. The first two represent the actual Opcode/instruction and the next two are the location index (0 to 99).

#INSTRUCTIONS

##PGMDUMP = 0100 or 100

Print Stack Trace of all data currently in memory(mem). It is used to print entire program in machine code currently loaded. Expected Output:

    Current Counter Position = 3
    Return Counter = 3
    Program Memory Print:
    0: 1001	1: 2001	2: 4009	3: 100	4: 2103	5: 1103	6: 4300	7: 0	8: 0	9: 200	
    10: 1002	11: 3002	12: 200	13: 3900	14: 9999	15: 0	16: 0	17: 0	18: 0	19: 0	
    20: 0	21: 0	22: 0	23: 0	24: 0	25: 0	26: 0	27: 0	28: 0	29: 0	
    ..............................................................................
    ..............................................................................
    ..............................................................................
    170: 0	171: 0	172: 0	173: 0	174: 0	175: 0	176: 0	177: 0	178: 0	179: 0	
    180: 0	181: 0	182: 0	183: 0	184: 0	185: 0	186: 0	187: 0	188: 0	189: 0	
    190: 0	191: 0	192: 0	193: 0	194: 0	195: 0	196: 0	197: 0	198: 0	199: 0	

The first line denotes Current Program Counter Position or current instruction being run. The second line denotes the return counter target if any jump takes place. The rest is a 2D tabular representation of Virtual RAM with indexed adresses.

##MEMDUMP = 0200 or 200

Print Stack Trace of all data currently in data memory(dmem). It is used to print all data/variable values currently loaded by the program/user. Expected Output:

    Accumulator = 12.000000
    Memory Print:
    0: 0.000000	1: 5.000000	2: 7.000000	3: 0.000000	4: 0.000000	5: 0.000000	6: 0.000000	7: 0.000000	8: 0.000000	9: 0.000000	
    10: 0.000000	11: 0.000000	12: 0.000000	13: 0.000000	14: 0.000000	15: 0.000000	16: 0.000000	17: 0.000000	18: 0.000000	19: 0.000000	
    .............................................................................................................................................................
    .............................................................................................................................................................
    .............................................................................................................................................................
    150: 0.000000	151: 0.000000	152: 0.000000	153: 0.000000	154: 0.000000	155: 0.000000	156: 0.000000	157: 0.000000	158: 0.000000	159: 0.000000	
    160: 0.000000	161: 0.000000	162: 0.000000	163: 0.000000	164: 0.000000	165: 0.000000	166: 0.000000	167: 0.000000	168: 0.000000	169: 0.000000	
    170: 0.000000	171: 0.000000	172: 0.000000	173: 0.000000	174: 0.000000	175: 0.000000	176: 0.000000	177: 0.000000	178: 0.000000	179: 0.000000	
    180: 0.000000	181: 0.000000	182: 0.000000	183: 0.000000	184: 0.000000	185: 0.000000	186: 0.000000	187: 0.000000	188: 0.000000	189: 0.000000	
    190: 0.000000	191: 0.000000	192: 0.000000	193: 0.000000	194: 0.000000	195: 0.000000	196: 0.000000	197: 0.000000	198: 0.000000	199: 0.000000	

The first line prints current Accumulator value in Double. The rest is 2D tabular representation of data values stored currently in the data Virtual RAM with indexed addresses.

##RESETA = 0800 or 800

Reset Accumulator to Zero.

##LAD = 0900 or 900

Load Accumulator Direct - Loads a value in accumulator after asking it from the user while execution. Expected Output:

    Enter Variable Value: 

##STORE x = 10x

Store data in RAM - Stores a value in RAM at location/index 'x' after asking for it from the user while execution. Expected Output:

    Enter Variable: 

##PRINTD x = 11x

Prints data from RAM location/index 'x' to the screen. Expected Output:

    Output = 

##LOAD x = 20x

Load data from data RAM(dmem) at location/index 'x' to Accumulator.

##SAVE x = 21x

Save data from Accumulator to data RAM(dmem) at loaction/index 'x'.

##ADD x = 30x

Adds data from location/index 'x' of data RAM(dmem) to Value in Accumulator and leaves the output in the accumulator.

##SUB x = 31x

Subtracts data from location/index 'x' of data RAM(dmem) from Value in Accumulator and leaves the output in the accumulator (with the negative sign if need be).

##DIV x = 32x

Divides Value in Accumulator with data of location/index 'x' from RAM(dmem) and leaves the output in the accumulator. NOTE: Does not check for division by zero error.

##MUL x = 33x

Multiplies value in Accumulator with value at location/index 'x' from RAM(dmem) and leaves the product in the accumulator.

##CMP x = 34x

Compares value at 'x' with value in Accumulator. Returns '0' when equal, '-1' when Accumulator value is smaller and '1' when Accumulator value is greater.


#CONTROL FLOW INSTRUCTIONS

##JUMP x = 40x

Jump in Program RAM(mem) to location 'x' for next instruction. The Return Location is automatically set to the next statement to the jump-line.

##JUMPN x = 41x

Jump in Program RAM(mem) to location 'x' for next instruction when Accumulator is Negative. The Return Location is automatically set to the next statement to the jump-line.

##JUMPZ x = 42x

Jump in Program RAM(mem) to location 'x' for next instruction when Accumulator is Zero. The Return Location is automatically set to the next statement to the jump-line.

##RETURN = 3900

Return to Return Counter location in Program RAM(mem) to location 'x' for next instruction.

##HALT = 4300

Ends execution of Program.

##END = 9999

End entering program/instructions to the system. 
