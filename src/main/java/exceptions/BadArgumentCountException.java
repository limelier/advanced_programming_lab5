package exceptions;

public class BadArgumentCountException extends Exception {
    public BadArgumentCountException(int expected, int actual) {
        super("Expected " + (expected - 1) + " arguments but got " + (actual - 1));
    }
}
