package ru.itmo.commands;

import ru.itmo.classes.MusicBand;
import ru.itmo.classes.MusicGenre;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class RemoveAllByGenreCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: remove_all_by_genre <genre>" +
                    "\nDescription: Removes all the elements, which field 'genre' value matches the specified." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument: genre (MusicGenre)\n";

    {
        setNumberOfArguments(1);
    }


    /**
     * @param collection
     * @param genre
     */
    public void execute(TreeMap<Integer, MusicBand> collection, MusicGenre genre) {

        checkCollectionForEmptiness(collection);

        ArrayList<Integer> keysToRemove = new ArrayList<>();

        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (entry.getValue().getGenre() == genre) {
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
     */
    public void execute(TreeMap<Integer, MusicBand> collection, String[] args) {

        checkNumberOfArguments(args);

        try {
            execute(collection, MusicGenre.valueOf(args[0].toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error: Incorrect 'musicGenre' value. Please, use one of the following:\n" + MusicGenre.showValues());
        }
    }
}
