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

    /**
     * Get a command from stdin, and execute it.
     *
     * @return true if the command ran was "quit"
     */
    public boolean doCommand() {
        String[] args = readArgs();
        InitCommand command;

        if (args[0].equals("?")) {
            System.out.println("The available commands are:");
            System.out.println("quit: quits the program"); // todo
        } else if (args[0].equals("quit")) {
            System.out.println("Quitting application, goodbye!");
            return true;
        } else {
            System.out.println("Bad command. Use '?' to see available commands.");
        }

        return false;
    }

    public void printCommandPrompt() {
        System.out.println("Input commands, or use '?' to get available commands.");
    }
}
