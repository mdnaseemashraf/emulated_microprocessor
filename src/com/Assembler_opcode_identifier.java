package com;


import java.util.Scanner;

public class Assembler_opcode_identifier {
		
	/**
	 * @author MD Naseem Ashraf
	 * http://mdnaseemashraf.wordpress.com/
	 * md.naseem.ashraf@gmail.com
	 */
	 
	public static void opcode_check(int opcode, int loc) {
	
		Scanner entry = new Scanner(System.in);
		
		double var=0;
		
		switch(opcode){					
		
		case 1:													//Code Stack Dump
			Assembler.pgmDump();
			break;
		
		case 2:													//Memory Stack Dump
			Assembler.memDump();
			break;	
			
		case 8:													//RESET Accumulator
			Assembler.setAccumulator(0);
			break;	
			
		case 9:												    //LOAD user INPUT in Accumulator=LDA
			
			System.out.printf("\nEnter Variable Value: ");
			var = entry.nextDouble();
			
			System.out.print("\n");
			Assembler.setAccumulator(var);
			break;	
			
		case 10:													//STORE user INPUT in RAM
		
			System.out.printf("\nEnter Variable: ");
			var = entry.nextDouble();
			
			System.out.print("\n");
			Assembler.setDMem(loc,var);
			break;
		
		case 11:													//PRINT Saved Data from (DATA)RAM to Screen
			
			Assembler.printx(Assembler.getDMem(loc));
			break;
		
		case 20:													//LOAD data from (DATA)RAM to Accumulator	
			
			Assembler.setAccumulator(Assembler.getDMem(loc));
			break;
		
		case 21:													//STORE data from Accumulator to (DATA)RAM	
			
			Assembler.setDMem(loc,Assembler.getAccumulator());
			break;	
		 	
		case 30:													//ADD from (DATA)RAM to Accumulator	
			
			Assembler.setAccumulator(Assembler.getDMem(loc) + Assembler.getAccumulator());
			break;
		
		case 31:												    //SUBTRACT RAM from Accumulator
			
			Assembler.setAccumulator(Assembler.getDMem(loc) - Assembler.getAccumulator());
			break;
		
		case 32:													//DIVIDE Accumulator with RAM	
			
			Assembler.setAccumulator(Assembler.getAccumulator() / Assembler.getDMem(loc));
			break;
			
		case 33:													//MULTIPLY Accumulator with RAM
			
			Assembler.setAccumulator(Assembler.getDMem(loc) * Assembler.getAccumulator());
			break;
				
		case 34:								              //Compare data in Accumulator with RAM
		
			if(Assembler.getAccumulator()<Assembler.getDMem(loc)){
				Assembler.setAccumulator(-1);
			}
			else if(Assembler.getAccumulator()>Assembler.getDMem(loc)){
				Assembler.setAccumulator(1);
			}
			else{
				Assembler.setAccumulator(0);
			}
		}
		
	}
}
