package ProducerBuyer;

import java.util.ArrayList;
import java.util.List;

public class Producer implements IPublisher {

	private String name;

	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	/* 
	private Collection<Vehicle> vehicle;

	private Broker broker;
	*/

	public Producer(String name) {
		//Begin 
		this.name = name;
		//End
	}

	public boolean publish(String prodCat, String modelType, String fuelType) {
		//Begin
		if(!(prodCat.toUpperCase().equals("CAR") || prodCat.toUpperCase().equals("TRUCK") || prodCat.toUpperCase().equals("SPORT UTILITY"))){
			return false;}
		if(!(fuelType.toUpperCase().equals("ELECTRIC") || fuelType.toUpperCase().equals("GASOLINE") || fuelType.toUpperCase().equals("HYBRID"))){
			return false;}

		for (int i = 0; i < vehicles.size(); i++) {
			if(vehicles.get(i).getCat().toUpperCase().equals(prodCat.toUpperCase()))
			if(vehicles.get(i).getType().toUpperCase().equals(modelType.toUpperCase()))
			if(vehicles.get(i).getFuel().toUpperCase().equals(fuelType.toUpperCase()))
			return false;
		}

		Vehicle v = new Vehicle(name , prodCat, modelType , fuelType);
		vehicles.add(v);
		return true;
		//End
	}

	public List<Vehicle> getVehicles() {
		//Begin
		return vehicles;
		///End
	}

	public String getName(){
		//Begin
		return name;
		//End
	}
}
