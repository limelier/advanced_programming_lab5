package shell.result;

public class ContentResult<T> extends Result {
    boolean successful;
    String message;
    T contents;

    public ContentResult(String message) {
        super(false, message);
    }

    public ContentResult(String message, T contents) {
        super(true, message);
        this.contents = contents;
    }

    public T getContents() {
        return contents;
    }
}
