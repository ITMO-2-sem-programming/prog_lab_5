package ru.itmo.commands;

import ru.itmo.classes.MusicBand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class RemoveGreaterCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: remove_greater {element}" +
                    "\nDescription: Removes all the element greater than the specified one." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument: element (MusicBand)\n";

    {
        setNumberOfArguments(0);
    }


    /**
     * @param collection
     * @param executionOfScript
     * @param iterator
     * @throws IOException
     */
    public void execute(TreeMap<Integer, MusicBand> collection, boolean executionOfScript, Iterator<String> iterator) throws IOException {

        checkCollectionForEmptiness(collection);

        MusicBand musicBand = getNewMusicBand(1, executionOfScript, iterator);

        ArrayList<Integer> keysToRemove = new ArrayList<>();

        for (Map.Entry<Integer, MusicBand> entry: collection.entrySet()) {
            if (musicBand.isLessThan(entry.getValue())) {
                keysToRemove.add(entry.getKey());
            }
        }

        for (Integer k: keysToRemove) {
            collection.remove(k);
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

        execute(collection, executionOfScript, iterator);
    }
}
