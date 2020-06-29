package ru.itmo.commands;

import ru.itmo.InvalidCommandException;
import ru.itmo.classes.MusicBand;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

/**
 *
 */
public class ClearCommand extends Command {

    public static String syntaxDescription =
            "\nCommand: clear" +
            "\nDescription: Deletes all the keys and values (clears he collection)." +
            "\nNumber of arguments: 0\n";

    {
        setNumberOfArguments(0);
    }


    private void execute(TreeMap<Integer, MusicBand> collection, boolean safeMode) {
        if (!safeMode) {
            collection.clear();
        } else {
            System.out.println("Warning: Command will clear the collection. It will be impossible to backup it.\n" +
                    "Enter 'Yes' or 'Y' to confirm, other input will reject this command:");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String confirmation = reader.readLine().toLowerCase();
                if (confirmation.equals("yes") || confirmation.equals("y")) {
                    collection.clear();
                    System.out.println("The collection was cleared.");
                } else System.out.println("Command was rejected. Collection wasn't cleared.");
            } catch (Exception e) {
                throw new InvalidCommandException("Error: Unresolved error was occurred. Command was rejected.");
            }
        }
    }


    public void execute(TreeMap<Integer, MusicBand> collection, String[] args, boolean safeMode) {
        checkNumberOfArguments(args);
        execute(collection, safeMode);
    }
}
