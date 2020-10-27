import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Random;
import java.util.Stack;



public class Version2 {
	public static void main(String [] args) {
		final int mask = 10; //Number of masked characters we want to have in the String.
		
		try {
			PrintStream myconsole = new PrintStream("/Users/stevenfranchetto/Desktop/outV2.txt");
			//comment the next line to have the output print to the console, else it will print to the file above.
			//System.setOut(myconsole);
			
			long startTime = System.currentTimeMillis();
			
			String finalString = GenerateString(mask); //Method to generate a random String with masks at random indices.
			RevealStr(finalString);  //Method that takes in the generated string and outputs all permutations without
									 //the mask
			
			long endTime = System.currentTimeMillis();
			float duration = ((endTime - startTime)/1000F); //Execution time of the method
			
			System.out.println(duration + " seconds");
					
		}catch(FileNotFoundException fx) {
			System.out.println(fx);	
		}
	}
	
	
	/* Using a Stack to print out permutations, same idea as Version 1 but using a Linear data structure
	 * instead of recursion.
	 */
	public static void RevealStr(String str) {
	       Stack<String> myStack = new Stack<String>();

	       myStack.push(str); //Start with adding our string to the stack.
	       
	       int found; //The index where the masked character is found.
	       
	       //Will continue to push and pop combinations until myStack is empty
	       while(myStack.isEmpty() == false)
	       {
	           String finalString = myStack.pop(); //String to be identified
	           
	           /* Checks if the string has a *, and if it does saved the index as "found", if not 
	            * it sets the value of found to -1, and the algorithm stops because there
	              is no masked character.
	            */
	           if((found = finalString.indexOf('*')) != -1) {
	        	   
	               /* For loop that builds the String in the same sense as the recursion version, it splits the
	                * String from the start index to the index before the mask, assigns 0 & 1 and concatenates
	                * the rest of the String.
	                */
	        	   for(char wildcard = '0'; wildcard <= '1'; wildcard++){
	            	   finalString = finalString.substring(0, found) + wildcard + finalString.substring(found + 1);      	            	   
	            	   myStack.push(finalString);
	               }
	           }
	           else{
	               System.out.println(finalString);
	           }
	       }
	}
	
	/*
 		GenerateString will take in an integer for how many masked (*) characters we want to have randomly in our
 		String. It will also generate a binary string of Random length and then it will 
    	randomly place the number of masks we sent to the method in the String and then return the binary string with
 		masked characters along random indices.
	 */
	
	public static String GenerateString(int mask) {
		Random rg = new Random();
		int randomSize = rg.nextInt(Integer.MAX_VALUE);
		int n = rg.nextInt(randomSize);
		String str = Integer.toBinaryString(n);
		
		StringBuffer buffer = new StringBuffer(str);
		
		for(int i = 0; i < mask; i++) {
			int randomIndex = rg.nextInt(str.length());
			buffer.insert(randomIndex, "*");
		}
		System.out.println(buffer); //This will display the final string to be sent to RevealStr
		System.out.println();
		String finalString = buffer.toString();
		return finalString;
	}
}
