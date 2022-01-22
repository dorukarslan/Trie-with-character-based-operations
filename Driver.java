//-----------------------------------------------------
// Title: Driver class
// Author:Do�uhan Cumao�lu Doruk Arslan
// Description: Main class of program
//-----------------------------------------------------
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(System.in); // Scanner for inputs
		String txt = scan.next(); // Taking txt name from user 
		File input = new File(txt); // Txt location finder
		TrieST<Integer> st = new TrieST<Integer>(); // Trie object 

		
		Scanner oku = new Scanner(input);

		while (oku.hasNext()) { // Read the datas for trie object 

			String line = oku.nextLine();
			String test_Arr[] = line.split(" "); // input arr according to split by " "

			for (int i = 0; i < test_Arr.length; i++) {
				String key = test_Arr[i];
				key = key.replaceAll("\\p{Punct}", ""); // This function for delete punctuation use for (agreed;)

				if (st.contains(key)) { // This line for topK function 
					// Here we have a counter for topK this values set on value variable which is needed for tries put (key,value)
					int currentKeyValue = st.get(key); // Set current value to key 
					currentKeyValue++; 
					st.put(key, currentKeyValue); // put the values according to key and currentKeyValue
				
				} else {
					st.put(key, 0);

				}

			}
		}
		Body body = new Body(st); // Body object created for use function 
		String flag = scan.next(); // Switch triggered 

		switch (flag) { // Switch case for operations 

		// Terminate the program
		case "0":
			break;
		case "1":
			// Search function 
			String inp = scan.next();
			body.Search(inp); // calls from body function 
			break;

		case "2":
			// Autocomplete function 
			String inp1 = scan.next();
			body.Autocomplete(inp1); // calls from body function 
			break;
		case "3":
			// Reverse Auto complete function 
			String inp2 = scan.next();
			body.reverseAutoComplete(inp2); // calls from body function 
			break;
		case "4":
			// Full Auto Complete function
			String inp3 = scan.next();
			String inp4 = scan.next();
			body.FullAutoComplete(inp3, inp4); // calls from body function 
			break;
		case "5":
			// Top k function 
			int inp5 = scan.nextInt();
			body.TopK(inp5); // calls from body function 
			break; 
		case "6":
		String txt1 = scan.next(); // Taking txt name from user 
			File inputt = new File(txt1); // Txt location finder

			Scanner read = new Scanner(inputt);

			String words[][] = new String[5][5]; // 2d array for design word puzzle 
			int counter = 0;
			while (read.hasNext()) {
				// reads the data from txt and puts them to the 2d array 
				String line = read.nextLine();
				String test_Arr[] = line.split(" ");
				for (int i = 0; i < 5; i++) {

					words[counter][i] = test_Arr[i]; // 2d array implementation for puzzle 
				}
				counter++;
			}

			body.SolvePuzzle(words);  // calls from body function 
			break;
		}
	
	}

}
