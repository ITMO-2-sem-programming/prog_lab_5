package ru.itmo.commands;

import ru.itmo.InvalidCommandException;
import ru.itmo.classes.MusicBand;

import java.util.TreeMap;

public class ShowCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: show [key]" +
                    "\nDescription: Prints all the elements in collection or the one with specified key." +
                    "\nNumber of arguments: 0 - 1" +
                    "\n   Optional argument: key (Integer)\n";

    {
        setNumberOfArguments(1);
    }

    /**
     * @param collection
     * @return
     */
    public String execute(TreeMap<Integer, MusicBand> collection) {
        return collection.toString();
    }


    /**
     * @param collection
     * @param key
     * @return
     */
    public String execute(TreeMap<Integer, MusicBand> collection, Integer key) {

        checkCollectionForEmptiness(collection);

        if (!collection.containsKey(key)) throw new InvalidCommandException("Error: No element with such 'key' in the collection.");

        return collection.get(key).toString();
    }


    /**
     * @param collection
     * @param args
     * @return
     */
    public String execute(TreeMap<Integer, MusicBand> collection, String[] args) {

        if (args.length > 1) throw new IllegalArgumentException("Error: Command gets not more than 1 argument.");

        if (args.length == 0) {
            return  execute(collection);
        } else {
            try {
                return execute(collection, Integer.parseInt(args[0]));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: Incorrect 'key' value.");
            }
        }
    }
}
