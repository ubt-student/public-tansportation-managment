package project.transportation.publictransportationmanager.exception;

public class UnauthorizedUserException extends RuntimeException {
    private static final long serialVersionUID = 1083620595565776251L;

    public UnauthorizedUserException() {
        super();
    }

    public UnauthorizedUserException(String message) {
        super(message);
    }
}
