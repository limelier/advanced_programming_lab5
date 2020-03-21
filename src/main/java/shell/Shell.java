package shell;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import shell.command.catalog.CatalogCommand;
import shell.command.catalog.ViewCommand;
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
                                    "quit: quit the application" +
                                    "view <doc_id>: view a document");
                    break;
                case "quit":
                    result = new Result(true, "Goodbye!");
                    return true;
                case "view":
                    command = new ViewCommand(args);
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

    public void printCommandPrompt() {
        System.out.println("Input commands, or use '?' to get available commands.");
    }
}
