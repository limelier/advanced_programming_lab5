package shell.command.init;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import exceptions.InvalidCatalogException;
import shell.result.ContentResult;
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
    public ContentResult<Catalog> getResult() {
        ContentResult<Catalog> result;

        String path = args[1];

        try {
            Catalog catalog = CatalogUtil.load(path);
            result = new ContentResult<>("Catalog " + catalog.getName() + " loaded successfully.", catalog);
        } catch (IOException e) {
            result = new ContentResult<>("Failed to load catalog:\n" + e.toString());
        } catch (InvalidCatalogException e) {
            result = new ContentResult<>(e.getMessage());
        }

        return result;
    }
}
