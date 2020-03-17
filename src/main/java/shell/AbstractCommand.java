package shell;

public abstract class AbstractCommand implements Command {
    String[] args;

    public AbstractCommand(String[] args) {
        this.args = args;
    }
}
