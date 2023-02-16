package ProducerBuyer;

public interface IPublisher {

	public abstract boolean publish(String prodCat, String modelType, String fuelType);

}
