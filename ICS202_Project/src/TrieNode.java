import java.util.ArrayList;

public class TrieNode implements Comparable<TrieNode> {
	private char charactar;
	public ArrayList<TrieNode> subNodes = new ArrayList<TrieNode>();
	private boolean endOfWord;
	
	
	TrieNode() {
		charactar = '*';
		endOfWord = false;
	}
	
	TrieNode(char chr , boolean EOW){
		charactar = chr;
		endOfWord = EOW;
	}
	
	
	//
	//setters and getters
	//
	public String getChar() {
		return charactar + "";
	}
	
	public boolean getEndOfWord() {
		return endOfWord;
	}
	
	public void setEndOfWord(boolean set) {
		endOfWord = set;
	}
	
	
	
	//uses to check if some node contained in specific node and if return where it is, or -1 if it is not;
	public int contains(TrieNode S) {
		for(int i = 0 ; i < subNodes.size() ; i++) {
			if(subNodes.get(i).compareTo(S) == 0)
				return i;
		}
		return -1;
	}

	
	
	//overridden comparing method
	@Override
	public int compareTo(TrieNode p) {
		 if (p.getChar().compareTo(charactar + "") > 0)
	            return 1;
	        else if (p.getChar().compareTo(charactar + "") == 0)
	            return 0;
	        else
	            return -1;
	}
}
