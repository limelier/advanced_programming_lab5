package shell.command.catalog;

import catalog.Catalog;
import document.Document;
import exceptions.BadArgumentCountException;
import shell.result.Result;
import util.CatalogUtil;

import java.io.IOException;
import java.net.URISyntaxException;

public class ViewCommand extends CatalogCommand {
    public ViewCommand(String[] args) throws BadArgumentCountException {
        super(args);
        if (args.length != 2) {
            throw new BadArgumentCountException(2, args.length);
        }
    }

    @Override
    public Result run(Catalog catalog) {
        String documentId = args[1];

        Document document = catalog.findById(documentId);

        if (document == null) {
            return new Result(false, "The requested document was not found.");
        }

        try {
            CatalogUtil.view(document);
            return new Result(true, "Viewing document...");
        } catch (IOException | URISyntaxException e) {
            return new Result(false, "Viewing the document failed:\n" + e);
        }
    }


}
