package shell.command.init;

import catalog.Catalog;
import shell.command.Command;
import shell.result.Result;

public abstract class InitCommand extends Command {
    public InitCommand(String[] args) {
        super(args);
    }

    abstract public Result<Catalog> getResult();
}
