package se.pbt.dinoauction.exception.handler;

/**
 * Represents the error response returned by the API in case of an error.
 *
 * @param <T> The type of additional details provided in the error response.
 */
public class ErrorResponse<T> {

    private int errorCode;
    private String errorMessage;
    private long timestamp;
    private T details;

    /**
     * Default constructor for ErrorResponse.
     */
    public ErrorResponse() {
    }

    /**
     * Constructs an ErrorResponse with the specified error code, error message, timestamp, and details.
     *
     * @param errorCode    The error code associated with the error.
     * @param errorMessage The error message describing the error.
     * @param timestamp    The timestamp when the error occurred.
     * @param details      Additional details related to the error.
     */
    public ErrorResponse(int errorCode, String errorMessage, long timestamp, T details) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
        this.details = details;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public T getDetails() {
        return details;
    }

    public void setDetails(T details) {
        this.details = details;
    }
}
