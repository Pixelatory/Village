package client.exceptions;

public class UnknownCombatantException extends RuntimeException {
    public UnknownCombatantException(String message) {
        super(message);
    }

    public UnknownCombatantException() {
        super();
    }
}
