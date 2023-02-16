package ProducerBuyer;

import java.util.List;

/**
 * This is the class that processes input commands and collects and returns output notifications. 
 */
public class ProducerBuyer {
	
	private Broker broker;
	
	/**
	 * Constructor which will be called by the test cases to create the instance of this class.
	 */
	
	public ProducerBuyer() {
		broker = new Broker();
	}

	/**
	 * This method parses the input. Based on the input command, it will call the appropriate publisher or subscriber.
	 */
	public void processInput(String command) {
		//Begin
		String[] text = command.split(",");
		if(text.length == 3){
			broker.forwardToBuyer(command);
		} else if(text.length == 5 && text[0].equals("publish")){
			broker.forwardToProducer(command);
		}
		//End
	}

	/**
	 * This method is responsible for returning the consolidated notifications when the getAggregatedOutput( ) is called.
	 */
	public List<String> getAggregatedOutput() {
		//Begin
		List<String> array = broker.getAggregated();
		for (int i = 0; i < array.size(); i++) {
			System.out.println(array.get(i));
		}
		return broker.getAggregated();
		//End
	}

	/**
	 * This method is responsible for removing all the stored published and subscribed events when the getAggregatedOutput( ) method is called.
	 */
	public void reset() {
		//Begin
		broker = new Broker();
		//End
	}

}
