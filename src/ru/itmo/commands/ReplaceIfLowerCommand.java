package ru.itmo.commands;

import ru.itmo.classes.MusicBand;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

public class ReplaceIfLowerCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: replace_if_lower <key> {element}" +
                    "\nDescription: Replaces the element of specified key with the specified element if the new one is less." +
                    "\nNumber of arguments 2: " +
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
    public void execute(TreeMap<Integer, MusicBand> collection, Integer key, boolean executionOfScript, Iterator<String> iterator) throws IOException {

        checkCollectionForEmptiness(collection);

        if (!collection.containsKey(key)) throw new IllegalArgumentException("No such key in the collection. Command is impossible.");

        MusicBand musicBand = getNewMusicBand(key, executionOfScript, iterator);

        if (musicBand.isLessThan(collection.get(key))) {
            collection.put(key, musicBand);
        }
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
