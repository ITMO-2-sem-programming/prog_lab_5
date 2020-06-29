package ru.itmo.commands;

import ru.itmo.InvalidCommandException;
import ru.itmo.classes.MusicBand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class ExitCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: exit" +
                    "\nDescription: Stops the program (doesn't save changes)." +
                    "\nNumber of arguments: 0\n";

    {
        setNumberOfArguments(0);

    }


    /**
     * @param collection
     * @param safeMode
     */
    private void execute(TreeMap<Integer, MusicBand> collection, boolean safeMode) {
        if (!safeMode) {
            System.exit(2);
        } else {
            System.out.println("Warning: Command will stop the program. All unsaved changes will be lost.\n" +
                    "Enter 'Yes' or 'Y' to confirm, other input will reject this command:");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String confirmation = reader.readLine().toLowerCase();
                if (confirmation.equals("yes") || confirmation.equals("y")) {
                    System.out.println("The program stops...");
                    System.exit(2);
                } else System.out.println("Command was rejected. Program continues working.");
            } catch (Exception e) {
                throw new InvalidCommandException("Error: Unresolved error was occurred. Command was rejected.");
            }
        }
    }


    /**
     * @param collection
     * @param args
     * @param safeMode
     */
    public void execute(TreeMap<Integer, MusicBand> collection, String[] args, boolean safeMode) {

        checkNumberOfArguments(args);

        execute(collection, safeMode);
    }
}
