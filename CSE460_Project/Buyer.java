package ProducerBuyer;

import java.util.ArrayList;
import java.util.List;

public class Buyer implements ISubscriber {

	private String name;

	private List<String> subscribtion = new ArrayList<String>();

	/* 
	private Broker broker;
	*/

	public Buyer(String name) {
		//Begin
		this.name = name;
		//End
	}

	public boolean subscribe(String prodCat) {
		//Begin 
		for (int i = 0; i < subscribtion.size(); i++) {
			if(subscribtion.get(i).toUpperCase().equals(prodCat.toUpperCase()))
			return false;
		}
		subscribtion.add(prodCat);
		return true;
		//End
	}

	public boolean unsubscribe(String prodCat) {
		//Begin
		for(int i = 0 ; i < subscribtion.size() ; i++){
			if(subscribtion.get(i).toUpperCase().equals(prodCat.toUpperCase())){
				subscribtion.remove(i);
				return true;
			}
		}
		return false;
		//End
	}

	public List<String> getSubscribtion() {
		//Begin
		return subscribtion;
		//End
	}

	public String getName(){
		//Begin
		return name;
		//End
	}
}
