import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class Main {
	
	//The main Class
	public static void main(String[] args) throws FileNotFoundException {
		//Initialize 
		Trie Trie = new Trie();

		
				int mainInput = 0;
				do {
					//Show menu interface to the user
					System.out.println(
							"\n-------------------------------------------------"
							+ "\n1. Create an empty trie"
							+ "\n2. Create a trie with initial letters "
							+ "\n3. Insert a word"
							+ "\n4. Delete a word "
							+ "\n5. List all words that begin with a prefix"
							+ "\n6. Size of the trie"
							+ "\n7. End"
							+ "\nChoose one of the above oparations : "
							);
					
				Scanner initialInput = new Scanner(System.in);
				File File = new File("dictionary.txt");
				Scanner inFile = new Scanner(File);
				Scanner input = new Scanner(System.in);
				if(!initialInput.hasNextInt()) {
					System.out.println("\n-------------------------------------------------");
					System.out.println("\nWrong input : Please Try again");
				} else {
				mainInput = initialInput.nextInt();
				System.out.println("\n-------------------------------------------------");
				if(mainInput < 1 || mainInput > 7)
					System.out.println("\nWrong input : Please Try again (Enter INTEGER between 0-7)");
				
				if(mainInput == 1) {
					//Option 1 - Empty Trie 
					Trie = new Trie();
					System.out.println("\n - Trie created succecfully");
				}
				
				else if(mainInput == 2) {
					//Option 2 - Trie with letters
					System.out.println("\n - Enter your list of letters : ");
					String inputCharsIN = input.nextLine();
					String[] inputChars = new String[inputCharsIN.length()];
					
					inputChars = inputCharsIN.split(" ");
					//Check if the input valid
					if (inputChars[0].length() > 1) {
						System.out.println("Wrong input : Please Enter valid letters with spaces between each letter");
						
					} else {
				
					//Creating the array of accepted arrangement
					ArrayList<String> wordsList = getMatches(inFile , inputChars);
					if(wordsList.size() != 0) {
					 for(String word : wordsList)
						 Trie.insert(word); 
					System.out.println("\n - Trie created succecfully");
					} else 
						System.out.println("\n - No word found");
				}
			}
				
				else if(mainInput == 3) {
					//Option 3 - Insert 
					System.out.println("\n - Enter your word : ");
					String inputInsert = input.nextLine();
					Trie.insert(inputInsert);
					System.out.println("\n - Done");
				}
				
				else if(mainInput == 4) {
					//Option 4 - Delete 
					System.out.println("\n - Enter your word : ");
					String inputDelete = input.nextLine();
					Trie.delete(inputDelete);
				}
				
				else if(mainInput == 5) {
					//Option 5 - Prefixes 
					String word = "";
					System.out.println("\n - Enter your Prefix : ");
					String inputPrefix = input.nextLine();
					String[] array = Trie.allWordsPrefix(inputPrefix);
					if(array[0].compareTo(" - No prefixes found") == 0)
						System.out.println(array[0]); 
					else {
					 for(String i : array)
						 word += i + ", "; 
					System.out.println(" - Found the following words : " + word); 
					}
				}
				
				else if(mainInput == 6) {
					//Option 6 - Size 
					System.out.println("\n - Trie size is " + Trie.size());
				}
				
				}}while(mainInput != 7);
					//Option 7 - end the program (by exiting the while loop)
				System.out.println("\n\n - Program was terminated , Thank you!\n\n");
				System.out.println("_______________________________________");
				System.out.println("__________________________________");
				System.out.println("____________________________");
				System.out.println("_____________________");
				System.out.println("____________");
	}

	
		
	//Method to get possible words from the dictionary from user collection of characters
	public static ArrayList<String> getMatches(Scanner input , String[]  chars) {
		ArrayList<String> arr = new ArrayList<String>();
		
		
		while(input.hasNextLine()) {
			//whole dictionary
		ArrayList<String>  charsTmp = copyArray(chars);
			String word = input.nextLine();
			boolean cond = false;
			
			
			if(word.length() <= charsTmp.size()) {
				//word by word
			for(int i = 0 ; i < word.length() ; i++) {
				cond = false;
				for(int j = 0 ; j < charsTmp.size() ; j++) {
					//char by char
					if((word.charAt(i)+"").equals(charsTmp.get(j).toUpperCase())) {
						cond = true;
						
						//remove the char to avoid char iteration 
						//charsTmp.remove(j);
						
						break;
					}
				}
				//check for matches
				if(!cond)
					break;
			}
			
			//add if it matches
			if(cond)
				arr.add(word);
		}
	}	
		return arr;	
		}
	
	
	//Deep copying for array
	public static ArrayList<String> copyArray(String[] arr){
		ArrayList<String> arr2 = new ArrayList<String>();
		for(int i = 0 ; i < arr.length ; i++)
			arr2.add(arr[i]);
		return arr2;
		}
}

	
