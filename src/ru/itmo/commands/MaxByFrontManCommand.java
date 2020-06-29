package ru.itmo.commands;

import ru.itmo.classes.MusicBand;

import java.util.Map;
import java.util.TreeMap;

public class MaxByFrontManCommand extends Command{

    public static String syntaxDescription =
                    "\nCommand: max_by_front_man" +
                    "\nDescription: Prints element, which field 'frontMan' is the greatest." +
                    "\nNumber of arguments: 0\n";

    {
        setNumberOfArguments(0);
    }


    /**
     * @param collection
     * @return
     */
    public MusicBand execute(TreeMap<Integer, MusicBand> collection) {

        checkCollectionForEmptiness(collection);

        MusicBand musicBand = null;

        for (Map.Entry<Integer, MusicBand> entry: collection.entrySet()) {
            musicBand = entry.getValue();
            if (musicBand.getFrontMan() != null) {
                break;
            }
        }

        for (Map.Entry<Integer, MusicBand> entry : collection.entrySet()) {
            if (entry.getValue().getFrontMan() == null) continue;
            if (entry.getValue().getFrontMan().getHeight() > musicBand.getFrontMan().getHeight()) {
                musicBand = entry.getValue();
            }
        }
        return musicBand;
    }


    /**
     * @param collection
     * @param args
     * @return
     */
    public MusicBand execute(TreeMap<Integer, MusicBand> collection, String[] args) {

        checkNumberOfArguments(args);

        return execute(collection);
    }
}
