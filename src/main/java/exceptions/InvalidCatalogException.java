package exceptions;

public class InvalidCatalogException extends Exception {
    public InvalidCatalogException() {
        super("Attempted to load invalid catalog.");
    }
}
