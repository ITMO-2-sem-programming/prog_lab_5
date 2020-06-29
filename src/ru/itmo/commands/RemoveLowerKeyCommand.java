package ru.itmo.commands;

import ru.itmo.classes.MusicBand;

import java.util.ArrayList;
import java.util.TreeMap;

public class RemoveLowerKeyCommand extends Command {

    public static String syntaxDescription =
            "Command: remove_lower_key <key>" +
                    "\nDescription: Removes all the elements, which key is less than the specified one." +
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

        ArrayList<Integer> keysToRemove = new ArrayList<>();

        for (Integer collectionKey : collection.keySet()) {
            if (collectionKey < key) keysToRemove.add(collectionKey);
        }

        for (Integer k : keysToRemove) {
            collection.remove(k);
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
