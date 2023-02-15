import java.util.ArrayList;
import java.util.Arrays;

public class Trie {
	TrieNode root = new TrieNode();
	
	Trie() {
	}
	
	public boolean isEmpty() {
		if(root.subNodes.isEmpty())
			return true;
		else 
			return false;
	}
	
	//insert a word to the Trie
	public void insert(String word) {
		TrieNode tmp = root;
		TrieNode newNode;
		for(int i = 0 ; i < word.length() ; i++) {
			newNode = new TrieNode(word.toUpperCase().charAt(i), false);
			if(i == word.length()-1)
				 newNode = new TrieNode(word.toUpperCase().charAt(i), true);
			if(tmp.subNodes.isEmpty()) {
				tmp.subNodes.add(newNode);
				tmp = newNode;
			} else if(tmp.contains(newNode) >= 0) {
				if(newNode.getEndOfWord()) {
						tmp.subNodes.get(tmp.contains(newNode)).setEndOfWord(true);
						}
				tmp = tmp.subNodes.get(tmp.contains(newNode));
			} else {
				tmp.subNodes.add(newNode);
				tmp = newNode;
			}
		}
	}
	
	//check if substring is a prefix
	public boolean isPrefix(String S) {
		TrieNode tmp = root;
		TrieNode newNode;
		for(int i = 0 ; i < S.length() ; i++) {
		newNode = new TrieNode(S.toUpperCase().charAt(i) , false);
		if(tmp.contains(newNode)== -1)
			return false;
		else {
			tmp = tmp.subNodes.get(tmp.contains(newNode));
			}
		}
			return true;
	}
	
	//check if substring contained in string
	public boolean contains(String S) {
		TrieNode tmp = root;
		TrieNode newNode;
		for(int i = 0 ; i < S.length() ; i++) {
		newNode = new TrieNode(S.toUpperCase().charAt(i) , false);
		if(tmp.contains(newNode) == -1)
			return false;
		else {
			tmp = tmp.subNodes.get(tmp.contains(newNode));
			}
		}
		return tmp.getEndOfWord();
	}
	
	//deleting  method
	public void delete(String S) {
		if(!this.contains(S))
			System.out.print(" - The trie has no such a word , Please Try again.");
		else {
			TrieNode tmp = root;
			TrieNode tmp2 = root;
			TrieNode newNode = new TrieNode(S.toUpperCase().charAt(0) , false) ;
			TrieNode newNode2 = new TrieNode(S.toUpperCase().charAt(0) , false) ;
		
			for(int i = 0 ; i < S.length() ; i++) {
				newNode = new TrieNode(S.toUpperCase().charAt(i) , false);
				
				//delete from this place if last
				if(tmp.getEndOfWord()) {
					tmp2 = tmp;
					newNode2 =  new TrieNode(S.toUpperCase().charAt(i) , false);
				}
				tmp = tmp.subNodes.get(tmp.contains(newNode));
			}
			
			//check if it last word or not
			if(tmp.subNodes.isEmpty())
				tmp2.subNodes.remove(tmp2.contains(newNode2));
			else {
				tmp.setEndOfWord(false);
			}
			System.out.print(" - Done");
			return;
		}
	}
	
	//clear the Trie 
	public void clear() {
		root.subNodes.clear();
	}
	
	//prefixes helper method
	public String[] allWordsPrefix(String p) {
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		
		String word = "";
		if(this.isPrefix(p)) {
			TrieNode tmp = root;
			TrieNode newNode;
			for(int i = 0 ; i < p.length() ; i++) {
			newNode = new TrieNode(p.toUpperCase().charAt(i) , false);
				tmp = tmp.subNodes.get(tmp.contains(newNode));
				word += p.charAt(i)+"".toUpperCase();
			}
			//by end of this line we reach the last character of the user prefix
			
			String[] stringArray;
			list2 = allWordsPrefix(p.toUpperCase() , tmp , list);
			//check if the some words does not included
			if(tmp.getEndOfWord() && !tmp.subNodes.isEmpty()) {
				list2.add(p.toUpperCase());
				stringArray = (String[]) list2.toArray(new String[0]);
			}
			else 
				stringArray = (String[]) list2.toArray(new String[0]);
			return stringArray;
		} else 
			return new String[]{" - No prefixes found"};
	}
	
	//prefixes recursive method
	public ArrayList<String> allWordsPrefix(String word , TrieNode newNode , ArrayList<String> list) {
		//Base case to exit
		if(newNode.subNodes.isEmpty()) {
			list.add(word);
			return list;
		} else {
			for(int i = 0 ; i < newNode.subNodes.size() ; i++) {
				if(newNode.subNodes.get(i).getEndOfWord() && !newNode.subNodes.get(i).subNodes.isEmpty())
					list.add(word.toUpperCase() + newNode.subNodes.get(i).getChar());
				list = allWordsPrefix(word.toUpperCase() + newNode.subNodes.get(i).getChar() , newNode.subNodes.get(i) , list);
			}
			return list;
		}
	}
	
	//size helper method
	public int size() {
		TrieNode tmp = root;
		return size(tmp);
	}
	
	//size recursive method
	public int size(TrieNode tmp) {
		int n = 0; 
		if(tmp.subNodes.isEmpty())
			return 0;
		else {
			for(int i = 0 ; i < tmp.subNodes.size() ; i++)
				n += 1 + size(tmp.subNodes.get(i));
		}
		return n;
	}
}
