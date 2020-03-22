package shell.command.catalog;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import shell.result.Result;
import util.CatalogUtil;

public class ListCommand extends CatalogCommand {
    public ListCommand(String[] args) throws BadArgumentCountException {
        super(args);
        if (args.length != 1) {
            throw new BadArgumentCountException(1, args.length);
        }
    }

    @Override
    public Result run(Catalog catalog) {
        return new Result(true, CatalogUtil.list(catalog));
    }
}
