package textEditor.core;

import java.util.Arrays;

import textEditor.core.structures.ArrayList;

/**
 * This is an adaptor to the array list exposing few of its functionality for
 * the purpose of adding and searching for words in the EWL
 */
public class EwlListAdaptor extends EwlStructureAdaptor {

	// Our structure of choice. Not exactly the ideal solution :(
	private ArrayList list = new ArrayList();

	@Override
	protected void addWord(String word) {
		list.add(list.size(), word.toLowerCase().strip());
	}

	@Override
	public boolean wordExists(String word) {
			//list.findUnsorted(list.getData());
			word = word.toLowerCase().strip();
			if (list.search(list.getStats(),0, list.size(), word)) {
				return true;
			}
			return false;
		
	}

	public void sortArray(){
		Arrays.sort(list.getStats(),0,list.size());
	}

	@Override
	public int getSize() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
