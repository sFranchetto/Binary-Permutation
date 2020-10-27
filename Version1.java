import java.util.Random;
import java.io.*;

public class Version1 {
	public static void main (String[] args) {
		final int mask = 2; //Number of masked characters we want to have in the String.
		try {
			PrintStream myconsole = new PrintStream("/Users/stevenfranchetto/Desktop/out.txt");
			//comment the next line to have the output print to the console, else it will print to the file above.
			//System.setOut(myconsole);
			
			long startTime = System.currentTimeMillis();
			
			String finalString = GenerateString(mask); //Method to generate a random String with masks at random indices.
			RevealStr("",finalString);  //Method that takes in the generated string and outputs all permutations without
										// the mask
			long endTime = System.currentTimeMillis();
			float duration = ((endTime - startTime)/1000F); //Execution time of the method
			
			System.out.println(duration + " seconds");
					
		}catch(FileNotFoundException fx) {
			System.out.println(fx);	
		}
	}
	
	/*
		RevealStr will take in an empty string as "prefix" and a string to be permutated as "remaining." The method
		recursively calls itself to build a String from the given String "remaining" into it's combinations without
		the mask.
	*/
	public static void RevealStr(String prefix, String remaining) {
		if(remaining.length() == 0) { //Base case
			System.out.println(prefix);
		}
		else {
			if(remaining.charAt(0) == '*') {
				RevealStr(prefix + "0", remaining.substring(1)); //Add a "0" to the end of prefix, shorten remaining
				RevealStr(prefix + "1", remaining.substring(1)); //Add a "1" to the end of prefix, shorten remaining
			}else {
				RevealStr(prefix + remaining.charAt(0), remaining.substring(1)); //Add the char of current index to prefix
			}                                                                    //because we didn't find a "*"
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
