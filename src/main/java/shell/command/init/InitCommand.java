package shell.command.init;

import catalog.Catalog;
import shell.command.Command;
import shell.result.ContentResult;

public abstract class InitCommand extends Command {
    public InitCommand(String[] args) {
        super(args);
    }

    abstract public ContentResult<Catalog> getResult();
}
