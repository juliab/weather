package iseroshtan.weather.service.exception;

public class UnavailableServiceException extends Exception {
    public UnavailableServiceException(Exception exception) {
        super();
        initCause(exception);
    }
}
