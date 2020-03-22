package shell.result;

public class Result {
    boolean successful;
    String message;

    public Result(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean ok() {
        return successful;
    };

    public String getMessage() {
        return message;
    };
}
