package shell.command.init;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import shell.result.Result;
import util.CatalogUtil;

import java.io.IOException;

public class LoadCommand extends InitCommand {
    public LoadCommand(String[] args) throws BadArgumentCountException {
        super(args);
        if (args.length != 2) {
            throw new BadArgumentCountException(2, args.length);
        }
    }

    @Override
    public Result<Catalog> getResult() {
        Result<Catalog> result;

        String path = args[1];

        try {
            Catalog catalog = CatalogUtil.load(path);
            result = new Result<>("Catalog " + catalog.getName() + " loaded successfully.", catalog);
        } catch (IOException | ClassNotFoundException e) {
            result = new Result<>("Failed to load catalog:\n" + e.toString());
        }

        return result;
    }
}
