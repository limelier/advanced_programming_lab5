package shell.command.catalog;

import catalog.Catalog;
import document.Document;
import exceptions.BadArgumentCountException;
import exceptions.DuplicateDocumentIdException;
import shell.result.Result;

public class AddCommand extends CatalogCommand {
    public AddCommand(String[] args) throws BadArgumentCountException {
        super(args);
        if (args.length != 4) {
            throw new BadArgumentCountException(4, args.length);
        }
    }

    @Override
    public Result run(Catalog catalog) {
        String id = args[1];
        String name = args[2];
        String path = args[3];
        Document document = new Document(id, name, path);
        try {
            catalog.add(document);
            return new Result(true, "Added document '" + name + "' to catalog, at ID '" + id + "'.");
        } catch (DuplicateDocumentIdException e) {
            return new Result(false, e.getMessage());
        }

    }
}
