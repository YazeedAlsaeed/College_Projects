package ProducerBuyer;

import java.util.ArrayList;
import java.util.List;

public class Broker {

	private List<Buyer> Buyers = new ArrayList<Buyer>();

	private List<Producer> Prodecuers = new ArrayList<Producer>();

	/*
	private ProducerBuyer producerBuyer;

	private Buyer buyer;

	private Producer producer;
	 */

	private List<String> notificaiton = new ArrayList<String>(); 

	public Broker() {
		
	}

	public boolean forwardToProducer(String text) {
		//Begin 
		
		String[] arrayText = text.split(",");
		Producer producer = new Producer(arrayText[1].trim());

		for (int i = 0; i < Prodecuers.size(); i++) {
			if(Prodecuers.get(i).getName().toUpperCase().equals(producer.getName().toUpperCase())){
				if(Prodecuers.get(i).publish(arrayText[2].trim(), arrayText[3].trim(), arrayText[4].trim())){
					Notify(arrayText);
			} 
					return true;
		}
	}  
			if(producer.publish(arrayText[2].trim(), arrayText[3].trim(), arrayText[4].trim())){
				Prodecuers.add(producer);
				Notify(arrayText);
		}
		
		return true;
		//End
	}

	public boolean forwardToBuyer(String text) {
		//Begin
		String[] arrayText = text.split(",");
		Buyer buyer = new Buyer(arrayText[1].trim());
		if(arrayText[0].equals("subscribe")){

			int check = check(buyer);
			if(check >= 0){
				Buyers.get(check).subscribe(arrayText[2].trim());
				return true;
			}
			else {
				buyer.subscribe(arrayText[2].trim());
				Buyers.add(buyer);
				return true;
			}

		} else if(arrayText[0].equals("unsubscribe")){
			int check = check(buyer);
			if(check >= 0){
				Buyers.get(check).unsubscribe(arrayText[2].trim());
				return true;
			}
			else {
				return false;
			}
			
		} else {
			return false;
		}
		//End
	}

	public void Notify(String[] text) {
		//Begin

		for(int i = 0 ; i < Buyers.size() ; i++){
			if(checkSubs(text[2].trim() , Buyers.get(i))){
				notificaiton.add(Buyers.get(i).getName() + " notified " + text[2].trim() + ": " + text[1].trim() + " " + text[3].trim() + ", " + text[4].trim() + " fuel");
			}
		}
	}

	public boolean checkSubs(String prodCat, Buyer buyer) {
		//Begin
		List<String> subs = buyer.getSubscribtion();
		for(int i = 0; i < subs.size(); i++){
			if(prodCat.toUpperCase().equals(subs.get(i).toUpperCase()))
			return true;
		}
		return false;
		//End
	}

	public List<String> getAggregated(){
		//Begin
		return notificaiton;
		//End
	}

	//Begin
	private int check(Buyer buyer){
		for(int i = 0 ; i < Buyers.size() ; i++){
			if(Buyers.get(i).getName().toUpperCase().equals(buyer.getName().toUpperCase()))
				return i;
		}
		return -1;
	}
	//End
}
