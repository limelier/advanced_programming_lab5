package shell.command.catalog;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import shell.result.Result;
import util.CatalogUtil;

import java.io.IOException;

public class SaveCommand extends CatalogCommand {

    public SaveCommand(String[] args) throws BadArgumentCountException {
        super(args);
        if (args.length != 1) {
            throw new BadArgumentCountException(1, args.length);
        }
    }

    @Override
    public Result run(Catalog catalog) {
        try {
            CatalogUtil.save(catalog);
            return new Result(true, "Successfully saved catalog.");
        } catch (IOException e) {
            return new Result(false, "Saving failed:" + e.toString());
        }

    }
}
