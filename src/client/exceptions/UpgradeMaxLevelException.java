package client.exceptions;

public class UpgradeMaxLevelException extends Exception {
	private static final long serialVersionUID = 8598323475994082951L;
	
	public UpgradeMaxLevelException(String message) {
		super(message);
	}

	public UpgradeMaxLevelException() {
		super();
	}
}
