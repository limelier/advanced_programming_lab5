package shell.command.init;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import shell.result.Result;
import util.CatalogUtil;

import java.io.IOException;

public class NewCommand extends InitCommand {

    public NewCommand(String[] args) throws BadArgumentCountException {
        super(args);
        if (args.length != 3) {
            throw new BadArgumentCountException(3, args.length);
        }
    }

    @Override
    public Result<Catalog> getResult() {
        Result<Catalog> result;

        String name = args[1];
        String path = args[2];

        Catalog catalog = new Catalog(name, path);

        try {
            CatalogUtil.save(catalog);
            result = new Result<Catalog>("Catalog " + name + " created successfully.", catalog);
        } catch (IOException e) {
            result = new Result<Catalog>("Failed to create catalog:\n" + e);
        }

        return result;
    }

}
