package ProducerBuyer;

public interface ISubscriber {

	public abstract boolean subscribe(String prodCat);

	public abstract boolean unsubscribe(String prodCat);

}
