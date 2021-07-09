package errors;

public class PageLoadingError extends Error {

  public PageLoadingError(String message, Throwable cause) {
    super(message, cause);
  }

  public PageLoadingError(String message) { super(message); }
}
