package exceptions;

public class NotEnoughAreaException extends Exception {
	private static final long serialVersionUID = 7517745284084116178L;

	public NotEnoughAreaException(String message) {
		super(message);
	}
}
