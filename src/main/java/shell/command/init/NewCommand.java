package shell.command.init;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import shell.result.ContentResult;
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
    public ContentResult<Catalog> getResult() {
        ContentResult<Catalog> result;

        String name = args[1];
        String path = args[2];

        try {
            Catalog catalog = CatalogUtil.create(name, path);
            result = new ContentResult<>("Catalog " + name + " created successfully.", catalog);
        } catch (IOException e) {
            result = new ContentResult<>("Failed to create catalog:\n" + e);
        }

        return result;
    }

}
