package shell.result;

public class Result<T> {
    boolean successful;
    String message;
    T contents;

    public Result(String message) {
        this.message = message;
        successful = false;
    }

    public Result(String message, T contents) {
        this.message = message;
        this.contents = contents;
        successful = true;
    }

    public boolean ok() {
        return successful;
    };

    public String getMessage() {
        return message;
    };

    public T getContents() {
        return contents;
    }
}
