package iseroshtan.weather.service.exception;

/**
 * This is a checked exception indicating that weather service is unavailable.
 *
 * @author Julia Seroshtan
 */

public class UnavailableServiceException extends Exception {

    /**
     * Create exception
     *
     * @param exception exception that is a cause for UnavailableServiceException
     */
    public UnavailableServiceException(Throwable exception) {
        super();
        initCause(exception);
    }
}
