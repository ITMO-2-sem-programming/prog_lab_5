package ru.itmo.commands;

import ru.itmo.InvalidCommandException;
import ru.itmo.classes.MusicBand;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

public class InsertCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: insert <key> {element}" +
                    "\nDescription: Adds new element with the following key (if the key doesn't exist)." +
                    "\nNumber of arguments: 2" +
                    "\n   First argument:  key     (Integer)" +
                    "\n   Second argument: element (MusicBand)\n";


    {
        setNumberOfArguments(1);
    }


    /**
     * @param collection
     * @param key
     * @param executionOfScript
     * @param iterator
     * @throws IOException
     */
    public void execute(TreeMap<Integer, MusicBand> collection, Integer key, boolean executionOfScript, Iterator<String> iterator)  throws IOException {

        if (collection.containsKey(key)) throw new InvalidCommandException("Error: Collection already contains musicBand with key: " + key + ". Command is impossible.");

        collection.put(key, getNewMusicBand(key, executionOfScript, iterator));
    }


    /**
     * @param collection
     * @param args
     * @param executionOfScript
     * @param iterator
     * @throws IOException
     */
    public void execute(TreeMap<Integer, MusicBand> collection, String[] args, boolean executionOfScript, Iterator<String> iterator) throws IOException {

        checkNumberOfArguments(args);

        try {
            execute(collection, Integer.parseInt(args[0]), executionOfScript, iterator);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Incorrect 'key' value.");
        }
    }
}
