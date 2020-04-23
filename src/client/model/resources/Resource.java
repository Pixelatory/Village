package client.model.resources;

/**
 * Abstract class that has functionality for what a resource should be able to do.
 * 
 * @author 6177000
 *
 */
public abstract class Resource {
	protected int quantity;
	
	/**
	 * Increase the amount of a resource by an amount
	 * @param amount int value
	 */
	public void increase(int amount) {
		if((long) this.quantity + amount < Integer.MAX_VALUE) // can't go above max int value
			this.quantity += amount;
		else
			this.quantity = Integer.MAX_VALUE;
	}
	
	/**
	 * Decrease the amount of a resource by an amount
	 * @param amount int value
	 */
	public void decrease(int amount) {
		this.quantity -= amount;
	}
	
	/**
	 * Get the current amount of a resource.
	 * @return int value
	 */
	public int getQuantity() {
		return this.quantity;
	}
}
