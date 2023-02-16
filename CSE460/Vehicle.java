package ProducerBuyer;

public class Vehicle {

	private String vehicleCat;

	private String modelType;

	private String fuelType;
	
	private String producer;	

	Vehicle(String Prod, String Cat, String Type, String Fuel) {
		//Begin
		this.producer = Prod;
		this.vehicleCat = Cat;
		this.modelType = Type;
		this.fuelType = Fuel;
		//End
	}

	public String getCat() {
		//Begin
		return vehicleCat;
		//End
	}

	public String getType() {
		//Begin
		return modelType;
		//End
	}

	public String getFuel() {
		//Begin
		return fuelType;
		//End
	}

	public String getProducer(){
		//Begin
		return producer;
		//End
	}
}
