package com;

import java.util.ArrayList;
import java.util.Scanner;

public class Assembler {

	/**
	 * @author MD Naseem Ashraf
	 * http://mdnaseemashraf.wordpress.com/
	 * md.naseem.ashraf@gmail.com
	 */
	
	public static int[] mem = new int [100];				//Virtual (CODE) RAM size
	public static double[] dmem = new double [100];		//Virtual (DATA) RAM size
	public static double accumulator = 0;				    //Accumulator
	public static int counter = 0;							//Program Counter
	public static int returncounter=0;    					//Return address of Program Counter	
	
		public static int getReturnCount(){					//Get Current Call Counter Address	
		return(returncounter);			
	}
	
		public static void setReturnCount(){				//Set Call Counter Address	
		returncounter = getProgramCount()+1;			
	}
	
		public static int getProgramCount(){				//Get Current Program Count	
			return(counter);			
		}
		
		public static void setProgramCount(int jumploc){	//Set New Program Counter Address	
			counter = jumploc;			
		}
		
		public static void setAccumulator(double val){			//Store Value in Accumulator			
			accumulator = val;			
		}
		
		public static double getAccumulator(){					//Get Value from Accumulator	
			return(accumulator);			
		}
		
		public static void setDMem(int count, double ins){		//Store Value in (DATA)RAM			
			dmem[count]=ins;			
		}
		
		public static double getDMem(int count){				//Get Value from (DATA)RAM
			return(dmem[count]);			
		}
		
		public static void setMem(int count, int ins){			//Store Value in (DATA)RAM			
			mem[count]=ins;			
		}
		
		public static void printx(double val){					//Print Value from (DATA)RAM
			System.out.printf("Output = %f\n",val);			
		}
	
		public static void pgmDump(){							//Program Dump
						
			System.out.printf("\nCurrent Counter Position = %d", counter);
			System.out.printf("\nReturn Counter = %d", returncounter);
			
			System.out.printf("\nProgram Memory Print:\n");
			int index = 0;
			for (int k=0; k<10; k++){
			for (int i=0; i<10; i++)
			{
				index = (k*10+i);
				System.out.printf("%d: %d\t", index, mem[index]);	
			}
			System.out.print("\n");
			}
			System.out.print("\n");
		}

		public static void memDump(){							//Memory Dump
			
			System.out.printf("\nAccumulator = %f", accumulator);			
			
			System.out.printf("\nMemory Print:\n");
			int index = 0;
			for (int k=0; k<10; k++){
			for (int i=0; i<10; i++)
			{
				index = (k*10+i);
				System.out.printf("%d: %f\t", index, dmem[index]);	
			}
			System.out.print("\n");
			}
			System.out.print("\n");
		}
	
	public static void main(String[] args) {		
		
		//get assembly instructions

        Scanner scanner = new Scanner(System.in);        
        ArrayList< String > slist = new ArrayList< String >();
        String inputline;        
        
        //ENTER ASSEMBLY INSTRUCTIONS 
        System.out.println("Enter Assembly Instructions. To Exit enter END.\n");
        
        int count=0;
        int run=0;
        do{
        	System.out.printf("%d:? ",count);
        	inputline = scanner.nextLine();
        	if(inputline.equals("END")){run=1;}
        	slist.add(inputline);
        	count++;
        }while(run!=1);				
		run=0;

		//process assembly instructions
		
		int code=0;
		int checks=0;
		int prev=0;
		int inx = 0;
		int[] runningcode = new int[100];		
		
		do{
			inputline=slist.get(run);
			run++;
			
			String[] kwords = inputline.split(" ");
			for(String currentword : kwords ){        	
	        
			if(checks == 0)	
				{
				code = mechcode(currentword);
								
				if(code<100 && code!=0)
		        {
		        	checks = 1;
		        	prev = code;
		        	code = 0;
		        }
		        else
		        {
		        	runningcode[inx]=code;				
					inx++;
		        	checks = 0;
		        }
				
			}
			else
			{
				code = ((prev*100)+ Integer.parseInt(currentword));				
				runningcode[inx]=code;				
				prev=0;
				code=0;
				inx++;
				checks = 0;
			}				        
	        }
						
		}while(run<count);						
																	
		//VIRTUAL PROCESSOR
		int i=0;
		count=0;
		int counter = getProgramCount();
		int countx = 0; //Total Number of Instructions	
		int ins = 0000;		
				
		do{													//Enter Machine Code
			ins = runningcode[i];					
			Assembler.setMem(count,ins);
			count++;
			i++;
		} while (i != inx);
			
		countx = count;
			
		System.out.println("Your entered Program in Machine OpCode:");		//Display entered Machine Code
		for (i=0; i<count; i++)
		{
			System.out.print("\n" + mem[i]);	
		}
		System.out.print("\n");
												
		int loc;
		int opcode;
		do
		{
			opcode = 0;
			loc = 0;
			counter = getProgramCount();				
			
			if(countx!=0)
			{				
				opcode = mem[counter]/100;
				loc = (mem[counter]-(opcode*100));
							
				if(opcode==40){										//JUMP
					do{										
						setReturnCount();
						counter++;					
					}while(counter!=loc);				
					
					setProgramCount(counter);
				}							
				else if(opcode==41){								//JUMP On Negative
					
					if(getAccumulator()<0){
					do{										
						setReturnCount();
						counter++;					
					}while(counter!=loc);				
					
					setProgramCount(counter);
				}
				}
				
				else if(opcode==42){								//JUMP On Zero
					
					if(getAccumulator()==0){
					do{										
						setReturnCount();
						counter++;					
					}while(counter!=loc);				
					
					setProgramCount(counter);
					}
				}			
				else if(opcode==39){								//RETURN
					setProgramCount(getReturnCount());				
				}				
				else if(opcode==43){								//HALT
					break;				
				}			
				else{
				Assembler_opcode_identifier.opcode_check(opcode,loc);			//OPCODE CHECK
				counter++;
				setProgramCount(counter);
				}
				countx--;							
			}
			else
			{
				break;
			}									
			}while((opcode!=99)||(opcode!=43)||(counter!=100)||(countx!=0));		
	}

	public static int mechcode(String word){
	
		if(word.equals("PGMDUMP")){
			return(100);
		}
		else if(word.equals("MEMDUMP")){
			return(200);
		}
		else if(word.equals("RESETA")){
			return(800);
		}
		else if(word.equals("LAD")){
			return(900);
		}
		else if(word.equals("STORE")){
			return(10);
		}
		else if(word.equals("PRINTD")){
			return(11);
		}
		else if(word.equals("LOAD")){
			return(20);
		}
		else if(word.equals("SAVE")){
			return(21);
		}
		else if(word.equals("ADD")){
			return(30);
		}
		else if(word.equals("SUB")){
			return(31);
		}
		else if(word.equals("DIV")){
			return(32);
		}
		else if(word.equals("MUL")){
			return(33);
		}
		else if(word.equals("CMP")){
			return(34);
		}
		else if(word.equals("JUMP")){
			return(40);
		}
		else if(word.equals("JUMPN")){
			return(41);
		}
		else if(word.equals("JUMPZ")){
			return(42);
		}
		else if(word.equals("RETURN")){
			return(3900);
		}
		else if(word.equals("HALT")){
			return(4300);
		}
		else if(word.equals("END")){
			return(9999);
		}
		return(0);	
	}
	
}

