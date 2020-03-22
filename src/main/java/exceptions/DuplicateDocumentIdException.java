package exceptions;

public class DuplicateDocumentIdException extends Exception {
    public DuplicateDocumentIdException (String id) {
        super("Cannot insert document with id " + id + " into catalog; catalog already has document with given id.");
    }
}
