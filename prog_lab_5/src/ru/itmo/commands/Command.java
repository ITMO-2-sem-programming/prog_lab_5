package ru.itmo.commands;

import ru.itmo.CollectionManager;
import ru.itmo.InvalidCommandException;
import ru.itmo.classes.MusicBand;

import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;

public abstract class Command {

    private int numberOfArguments;
    protected static String syntaxDescription;


    public void checkCollectionForEmptiness(TreeMap<Integer, MusicBand> collection) {
    if (collection.size() == 0)
        throw new InvalidCommandException("Error: Collection is empty. Impossible to run the command.");
    }


    public void checkNumberOfArguments(String[] arguments) {
        if (arguments == null) throw new IllegalArgumentException("Error: Arguments can't be 'null' !");
        if (numberOfArguments != arguments.length)
            throw new IllegalArgumentException(String.format("Error: Command gets only %s argument(s).", numberOfArguments));
    }


    public MusicBand getNewMusicBand(Integer key, boolean executionOfScript, Iterator<String> iterator) throws IOException {

        if (key == null || key <= 0) throw new IllegalArgumentException("Error: Value of 'key' {Integer} can't be 'null' and must be greater than zero.");

        MusicBand musicBand;

        if (executionOfScript) musicBand = CollectionManager.getNewMusicBandFromFile(iterator);
        else musicBand = CollectionManager.getNewMusicBandFromStandardInput();
        musicBand.setId(key);

        return musicBand;
    }


    public int getNumberOfArguments() {
        return numberOfArguments;
    }


    public void setNumberOfArguments(int numberOfArguments) {
        this.numberOfArguments = numberOfArguments;
    }
}
