package model.resources;

/**
 * Class of the Gold resource.
 * @author 6177000
 *
 */
public final class Gold extends Resource {
	/**
	 * Set the initial quantity of Gold to 1000.
	 */
	public Gold() {
		this.quantity = 1000;
	}

	public Gold(int amount) {
		this.quantity = amount;
	}
}
