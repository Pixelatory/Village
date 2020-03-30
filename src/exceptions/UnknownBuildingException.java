package exceptions;

public class UnknownBuildingException extends RuntimeException {
    public UnknownBuildingException(String message) {
        super(message);
    }

    public UnknownBuildingException() {
        super();
    }
}
