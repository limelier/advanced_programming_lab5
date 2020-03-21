package shell.command;

public abstract class Command {
    protected String[] args;

    public Command(String[] args) {
        this.args = args;
    }
}
