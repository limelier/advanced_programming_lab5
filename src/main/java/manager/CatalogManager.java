package manager;

import catalog.Catalog;
import shell.Shell;

public class CatalogManager {
    Catalog catalog;
    Shell shell;

    public CatalogManager() {
        this.shell = new Shell();
    }

    public void start() {
        while (true) {
            if (catalog == null) {
                catalog = shell.init();
                shell.printCommandPrompt();
            } else {
                if (shell.doCommand(catalog)) {
                    break;
                }
            }
        }
    }
}
