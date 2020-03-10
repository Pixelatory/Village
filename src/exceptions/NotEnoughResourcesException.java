package exceptions;

public class NotEnoughResourcesException extends Exception {
	private static final long serialVersionUID = 1578582627222400525L;
	
	public NotEnoughResourcesException(String message) {
		super(message);
	}
}
