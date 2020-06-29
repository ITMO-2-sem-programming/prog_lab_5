package ru.itmo.commands;

import ru.itmo.CollectionManager;
import ru.itmo.FileManager;
import ru.itmo.classes.MusicBand;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

public class ExecuteScriptCommand extends Command {

    public static String syntaxDescription =
                    "\nCommand: execute_script <file_path>" +
                    "\nDescription: Executes the commands from the file." +
                    "\nNumber of arguments: 1" +
                    "\n   Argument:  file_path (String)\n";

    {
        setNumberOfArguments(1);
    }


    /**
     * @param collection
     * @param filePath
     * @return
     */
    public String execute(TreeMap<Integer, MusicBand> collection, String filePath) {

        ArrayList<String> commands;
        try {
            commands = FileManager.getCommandsFromFile(filePath);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

        StringBuilder ignoredCommands = new StringBuilder("\nIgnored commands:");
        String input = "";
        Iterator<String> iterator = commands.iterator();
        while (iterator.hasNext()) {
            try {
                input = iterator.next();
                CollectionManager.executeCommand(collection, input, true, iterator);
            } catch (Exception e) {
                ignoredCommands.append(String.format("\nCommand: %s\n%s", input, e.getMessage()));
                ignoredCommands.append("\nWarning: The execution of the commands from the file was finished because of Error.");
                break;
            }
        }
        if (ignoredCommands.length() == 18) return "";
        else return ignoredCommands.toString();
    }


    /**
     * @param collection
     * @param args
     * @return
     */
    public String execute(TreeMap<Integer, MusicBand> collection, String[] args) {

        checkNumberOfArguments(args);

        return execute(collection, args[0]);
    }
}
