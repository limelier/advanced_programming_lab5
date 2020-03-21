package shell.command.catalog;

import catalog.Catalog;
import shell.command.Command;
import shell.result.Result;

public abstract class CatalogCommand extends Command {
    public CatalogCommand(String[] args) {
        super(args);
    }

    /**
     * Run the command, returning a message detailing the result.
     *
     * @param catalog the catalog to run the command on
     * @return a message detailing the result and success of the operation
     */
    abstract public Result run(Catalog catalog);
}
