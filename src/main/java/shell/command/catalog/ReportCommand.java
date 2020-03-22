package shell.command.catalog;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import freemarker.template.TemplateException;
import shell.result.Result;
import util.CatalogUtil;

import java.io.IOException;

public class ReportCommand extends CatalogCommand {

    public ReportCommand(String[] args) throws BadArgumentCountException {
        super(args);
        if (args.length != 3) {
            throw new BadArgumentCountException(3, args.length);
        }
    }

    @Override
    public Result run(Catalog catalog) {
        String reportType = args[1];
        String path = args[2];
        if (reportType.equals("html")) {
            try {
                CatalogUtil.report(catalog, path);
                return new Result(true, "Catalog report created, opening in browser...");
            } catch (TemplateException | IOException e) {
                return new Result(false, e.toString());
            }
        } else {
            return new Result(false, "Invalid report type (supported: html).");
        }
    }
}
