package by.runets.travelagency.exception;

public class ResourceNotFoundException extends RuntimeException {
  public ResourceNotFoundException() {
  }

  public ResourceNotFoundException(String message) {
	super(message);
  }

  public ResourceNotFoundException(Throwable cause) {
	super(cause);
  }
}
