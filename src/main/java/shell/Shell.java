package shell;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import shell.command.catalog.*;
import shell.command.init.InitCommand;
import shell.command.init.LoadCommand;
import shell.command.init.NewCommand;
import shell.result.ContentResult;
import shell.result.Result;

import java.util.Scanner;

public class Shell {
    Scanner scan;

    public Shell() {
        this.scan = new Scanner(System.in);
    }

    private String[] readArgs() {
        return scan.nextLine().split(" ");
    }

    public Catalog init() {
        System.out.println("Use 'new <name> <path>' to create a new catalog or 'load <path>' to load one.");
        String[] args = readArgs();
        InitCommand command;
        ContentResult<Catalog> result;
        try {
            if (args[0].equals("new")) {
                command = new NewCommand(args);
                result = command.getResult();
            } else if (args[0].equals("load")) {
                command = new LoadCommand(args);
                result = command.getResult();
            } else {
                result = new ContentResult<>("Bad command, please try again.");
            }
        } catch (BadArgumentCountException e) {
            result = new ContentResult<>(e.getMessage());
        }

        System.out.println(result.getMessage());
        return result.getContents();
    }

    /**
     * Get a command from stdin, and execute it.
     *
     * @return true if the command run was "quit"
     */
    public boolean doCommand(Catalog catalog) {
        String[] args = readArgs();
        CatalogCommand command;
        Result result;

        try {
            switch (args[0]) {
                case "?":
                    result = new Result(true,
                            "Available commands:\n" +
                                    "quit: quit the application\n" +
                                    "view <doc_id>: view a document\n" +
                                    "add <doc_id> <doc_name> <doc_path>: add a document with the given local or web path\n" +
                                    "save: save the changes you've made to the catalog\n" +
                                    "list: list the documents in the "
                    );
                    break;
                case "quit":
                    return true;
                case "list":
                    command = new ListCommand(args);
                    result = command.run(catalog);
                    break;
                case "view":
                    command = new ViewCommand(args);
                    result = command.run(catalog);
                    break;
                case "add":
                    command = new AddCommand(args);
                    result = command.run(catalog);
                    break;
                case "save":
                    command = new SaveCommand(args);
                    result = command.run(catalog);
                    break;
                case "report":
                    command = new ReportCommand(args);
                    result = command.run(catalog);
                    break;
                default:
                    result = new Result(false, "Bad command. Use ? to view available commands.");
                    break;
            }
        } catch (BadArgumentCountException e) {
            result = new Result(false, e.getMessage());
        }

        System.out.println(result.getMessage());
        return false;
    }
}
