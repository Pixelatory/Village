package client.model.resources;

public class Food extends Resource {
    /**
    * Set the initial quantity of Food to 1000.
    */
    public Food() {
        this.quantity = 1000;
    }

    public Food(int amount) {
        this.quantity = amount;
    }
}
