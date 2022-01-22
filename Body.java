//-----------------------------------------------------
// Title: Body class
// Author:Do�uhan Cumao�lu Doruk Arslan
// Description: This class has a all function of program.
//-----------------------------------------------------
import java.util.ArrayList;
import java.util.Collections;

public class Body {
	// Arraylist for trie values and keys
	TrieST<Integer> trie;
	ArrayList<Integer> Values;
	ArrayList<String> Keys;

	Body(TrieST triestruct) { // Tries constructor 
		this.trie = triestruct;
		Keys = new ArrayList<String>();
		Values = new ArrayList<Integer>();
	}

	public void Autocomplete(String prefix) {
		//--------------------------------------------------------
		 // Summary: In here we did Autocomplete function for use it we used keysWithPrefix which is comes from trieST class
		 // Precondition: flag is a boolean , coma is String 
		 // Postcondition: The value of the variable is set and print it according the value of function 
		 //--------------------------------------------------------
		boolean flag = false;
		String comma = "";
		for (String i : trie.keysWithPrefix(prefix)) { // keysWithPrefix is a Iterable function cause of that we used for each  

			System.out.print(comma + i.toLowerCase());
			comma = ",";
			flag = true;

		}
		if (flag == false)
			System.out.println("No word");  // If function does not found word execute this line 
	}

	public void FullAutoComplete(String prefix, String suffix) {
		//--------------------------------------------------------
		 // Summary: In here we did FullAutoComplete function for use it we used keysThatMatch which is comes from trieST class
		 // Precondition: flag is a boolean , comma is String 
		 // Postcondition: The value of the variable is set and print it according the value of function 
		 //--------------------------------------------------------
		boolean flag = false;
		String comma = "";
		String pattern = prefix + "." + suffix; // This line for use keysThatMatch function. For use it we need to put dot according to space 
		for (String i : trie.keysThatMatch(pattern)) {// keysThatMatch is a Iterable function cause of that we used for each  
			System.out.print(comma + i);
			comma = ",";
			flag = true;
		}
		if (flag == false)
			System.out.println("No word"); // If function does not found word execute this line 
	}

	public void reverseAutoComplete(String suffix) {
		//--------------------------------------------------------
		 // Summary: In here we did reverseAutoComplete function for use it we used keysWithSuffix which is comes from trieST class
		 // Precondition: flag is a boolean , comma is String 
		 // Postcondition: The value of the variable is set and print it according the value of function 
		 //--------------------------------------------------------
		boolean flag = false;
		String comma = "";
		for (String i : trie.keysWithSuffix(suffix)) { // keysWithSuffix is a Iterable function cause of that we used for each  
			System.out.print(comma + i.toLowerCase()); // We need to print words in a lower case for it we use lowercase function which is comes from java libary
			comma = ",";
			flag = true;
		}
		if (flag == false)
			System.out.println("No word"); // If function does not found word execute this line 
	}

	public boolean Search(String element) {
		//--------------------------------------------------------
		 // Summary: In here we did Search  function for use it we used contains which is comes from trieST class
		 // Precondition: element is a string.
		 // Postcondition: The value of the variable is set and print it according the value of function 
		 //----
		element = element.toLowerCase(); // We need to print words in a lower case for it we use lowercase function which is comes from java libary
		System.out.println(trie.contains(element));
		return trie.contains(element);

	}

	public void TopK(int K) {
		//--------------------------------------------------------
		 // Summary: In here we did topK function for use it we have a variable in driver class which is currentKeyValue. In this variable we kept the data which is occurrences. 
		 // Precondition: tempValues,maxElements,output is a arraylist ,maxIndex is integer.
		 // Postcondition: The value of the variable is set and print it according the value of function 
		 //----
		for (String i : trie.keys()) { // First we get value of the data which is comes from trie and add it to the keys and values array 
			int value = trie.get(i); 
			Keys.add(i);
			Values.add(value);
		}
		ArrayList<Integer> tempValues = Values;
		ArrayList<String> maxElements = new ArrayList<String>();
		ArrayList<String> output = new ArrayList<String>();

		for (int i = 0; i < K; i++) {
			// We find a max index 
			int maxIndex = max(tempValues); 
			tempValues.set(maxIndex, -1);
			// System.out.println(Keys.get(maxIndex));
			maxElements.add(Keys.get(maxIndex)); // Add it to the array 

		}
		Collections.sort(maxElements); // For lexicographically order we use sort function which is comes from arraylist libary 
		for (String key : maxElements) 
			output.add(key);
		int m = output.size();
		String coma = "";
		while (m != 0) {
			System.out.print(coma + output.get(m - 1).toLowerCase()); // For the correct input print.
			m--;
			coma = ",";
		}
		System.out.println();
	}

	public int max(ArrayList<Integer> array) {
		//--------------------------------------------------------
		 // Summary: In here we did max  function for find maximum.
		 // Precondition: max, maxIndex is an integer 
		 // Postcondition: The value of the variable is set and print it according the value of function 
		 //----
		int max = array.get(0);
		int maxIndex = 0;

		for (int i = 0; i < array.size(); i++) {
			if (array.get(i) > max) {
				max = array.get(i);
				maxIndex = i;
			}
		}

		return maxIndex; // Gives maximum value of array 
	}

	public void SolvePuzzle(String[][] words) {
		//--------------------------------------------------------
		 // Summary: In here we did solve puzzle function for solve the puzzle. For solve it we move to the 3 directions Horizontal Diagonal Vertical. Also we use contains function which is comes from TrieST.
		 // Precondition: markedWords is arraylist. Temp is a string for words.
		 // Postcondition: The value of the variable is set and print it according the value of function 
		 //----
		ArrayList<String> markedWords = new ArrayList<String>();
		for (int i = 0; i < words.length; i++) {

			for (int j = 0; j < words[i].length; j++) {

				String temp = "";

				// Horizontal move 
				for (int x = 0; x < words[i].length; x++) {

					temp += words[i][x]; // For move it we use x variable which maximum length is equal to words[i].length 

					if (trie.contains(temp)) { 
						if (!markedWords.contains(temp)) { // Check word is on the trie or not 
							markedWords.add(temp);
						}
					}
				}

				temp = "";
				// Vertical

				for (int x = 0; x < words.length; x++) {

					temp += words[x][i]; // For move it we use x variable which maximum length is equal to words.length 

					if (trie.contains(temp)) {
						if (!markedWords.contains(temp)) { // Check word is on the trie or not 
							markedWords.add(temp);
						}
					}
				}
				// Diagonal
				temp = "";
				int currentRow = i; // Row keeper 
				int currentCol = j; // Col keeper 
				for (int x = 0; x < words.length; x++) {

					currentRow++;
					currentCol++;
					if (currentRow >= words.length || currentCol >= words[i].length) { // For move it we use x variable which maximum length is equal to words.length and words[i].length.
						break; 
					}
				

					temp += words[currentRow][currentCol]; 
					if (trie.contains(temp)) {
						if (!markedWords.contains(temp)) {
							markedWords.add(temp);
						}
					}
				}
				temp = "";
				for (int x = words.length - 1; x >= 0; x--) {

					temp += words[x][i];

					if (trie.contains(temp)) {
						if (!markedWords.contains(temp)) { // Check word is on the trie or not 
							markedWords.add(temp);
						}
					}
				}

			}
		}
		Collections.sort(markedWords);
		String comma = "";
		for (String name : markedWords) {

			System.out.print(comma + name.toLowerCase());
			comma = ",";
		}
	}

}