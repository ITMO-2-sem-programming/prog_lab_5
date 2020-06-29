package ru.itmo.commands;

import ru.itmo.classes.MusicBand;

import java.util.TreeMap;

public class FilterGreaterThanSinglesCountCommand extends Command{

    public static String syntaxDescription =
                    "\nCommand: filter_greater_than_singles_count <singlesCount>" +
                    "\nDescription: Prints the elements, which 'singlesCount' value is greater than the specified." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument:  singlesCount (int)\n";

    {
        setNumberOfArguments(1);
    }


    /**
     * @param collection
     * @param singlesCount
     * @return
     */
    public String execute(TreeMap<Integer, MusicBand> collection, int singlesCount) {

        checkCollectionForEmptiness(collection);

        StringBuilder commandOutput = new StringBuilder();

        for (MusicBand musicBand : collection.values()) {
            if (musicBand.getSinglesCount() > singlesCount) {
                commandOutput.append(musicBand);
            }
        }
        return commandOutput.toString();
    }


    /**
     * @param collection
     * @param args
     * @return
     */
    public String execute(TreeMap<Integer, MusicBand> collection, String[] args) {

        checkNumberOfArguments(args);

        try {
            return execute(collection, Integer.parseInt(args[0]));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error: Incorrect 'singlesCount' value.");
        }
    }
}
