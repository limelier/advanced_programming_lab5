package exceptions;

public class InvalidDocumentLocationException extends Exception {
    public InvalidDocumentLocationException(String location) {
        super("Given document location '" + location + "' is not a valid URI (local or web path).");
    }
}
