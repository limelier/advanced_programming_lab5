package shell;

import catalog.Catalog;
import exceptions.BadArgumentCountException;
import shell.command.init.InitCommand;
import shell.command.init.LoadCommand;
import shell.command.init.NewCommand;
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
        Result<Catalog> result;
        try {
            if (args[0].equals("new")) {
                command = new NewCommand(args);
                result = command.getResult();
            } else if (args[0].equals("load")) {
                command = new LoadCommand(args);
                result = command.getResult();
            } else {
                result = new Result<>("Bad command, please try again.");
            }
        } catch (BadArgumentCountException e) {
            result = new Result<>(e.getMessage());
        }

        System.out.println(result.getMessage());
        return result.getContents();
    }
}
