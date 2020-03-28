package model.resources;

/**
 * Class of the Iron resource.
 * @author 6177000
 *
 */
public final class Iron extends Resource {
	/**
	 * Set the initial quantity of Iron to 1000.
	 */
	public Iron() {
		this.quantity = 1000;
	}

	public Iron(int amount) {
		this.quantity = amount;
	}
}
