package ru.itmo.commands;

import ru.itmo.classes.MusicBand;

import java.util.TreeMap;

public class RemoveByKeyCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: remove_key <key>" +
                    "\nDescription: Removes an element with the specified key." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument: key (Integer)\n";

    {
        setNumberOfArguments(1);
    }


    /**
     * @param collection
     * @param key
     */
    public void execute(TreeMap<Integer, MusicBand> collection, Integer key) {

        checkCollectionForEmptiness(collection);

        if (collection.remove(key) == null) {
            throw new IllegalArgumentException("Error: No element with such 'key' in the collection.");
        }
    }


    /**
     * @param collection
     * @param args
     */
    public void execute(TreeMap<Integer, MusicBand> collection, String[] args) {

        checkNumberOfArguments(args);

        try {
            execute(collection, Integer.parseInt(args[0]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Incorrect 'key' value.");
        }
    }
}
