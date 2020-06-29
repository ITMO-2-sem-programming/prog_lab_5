package ru.itmo;

import ru.itmo.classes.*;
import ru.itmo.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.TreeMap;

public class CollectionManager {

    private static String filePath;

    private static String stopCreatingMusicBandCommand = "stop_creating";
    static HelpCommand helpCommand = new HelpCommand();
    static InfoCommand infoCommand = new InfoCommand();
    static ShowCommand showCommand = new ShowCommand();
    static InsertCommand insertCommand = new InsertCommand();
    static UpdateCommand updateCommand = new UpdateCommand();
    static RemoveByKeyCommand removeByKeyCommand = new RemoveByKeyCommand();
    static ClearCommand clearCommand = new ClearCommand();
    static SaveCommand saveCommand = new SaveCommand();
    static ExecuteScriptCommand executeScriptCommand = new ExecuteScriptCommand();
    static ExitCommand exitCommand = new ExitCommand();
    static RemoveGreaterCommand removeGreaterCommand = new RemoveGreaterCommand();
    static ReplaceIfLowerCommand replaceIfLowerCommand = new ReplaceIfLowerCommand();
    static RemoveLowerKeyCommand removeLowerKeyCommand = new RemoveLowerKeyCommand();
    static RemoveAllByGenreCommand removeAllByGenreCommand = new RemoveAllByGenreCommand();
    static MaxByFrontManCommand maxByFrontManCommand = new MaxByFrontManCommand();
    static FilterGreaterThanSinglesCountCommand filterGreaterThanSinglesCountCommand = new FilterGreaterThanSinglesCountCommand();


    private static FileManager fileManager;


    /**
     * @param collection
     * @param commandLineInput
     * @param executionOfScript
     * @param iterator
     * @throws Exception
     */
    public static void executeCommand(TreeMap<Integer, MusicBand> collection, String commandLineInput, boolean executionOfScript, Iterator<String> iterator) throws Exception {

        if (commandLineInput == null) throw new IllegalArgumentException("Error: 'null' command can't be executed.");
        String[] commandLineInputArgs = commandLineInput.trim().toLowerCase().split(" ");
        String commandName = commandLineInputArgs[0];
        String[] args;
        if (commandLineInputArgs.length == 1) {
            args = new String[]{};
        } else args = Arrays.copyOfRange(commandLineInputArgs, 1, commandLineInputArgs.length);


        switch (commandName) {
            case ("help"):
                System.out.println(helpCommand.execute(collection, args));
                break;

            case ("info"):
                System.out.println(infoCommand.execute(collection, args, fileManager.getDateOfInitialisation().toString()));
                break;

            case ("show"):
                System.out.println(showCommand.execute(collection, args));
                break;

            case ("insert"):
                insertCommand.execute(collection, args, executionOfScript, iterator);
                break;

            case ("update"):
                updateCommand.execute(collection, args, executionOfScript, iterator);
                break;

            case ("remove_key"):
                removeByKeyCommand.execute(collection, args);
                break;

            case ("clear"):
                if (executionOfScript) {
                    clearCommand.execute(collection, args, false);
                } else {
                    clearCommand.execute(collection, args, true);
                }
                break;

            case ("save"):
                if (args.length == 1) {
                    saveCommand.execute(collection, args);
                } else {
                    saveCommand.execute(collection, filePath);
                }
                break;

            case ("execute_script"):
                System.out.println(executeScriptCommand.execute(collection, args));
                break;

            case ("exit"):
                if (executionOfScript) {
                    exitCommand.execute(collection, args, false);
                } else {
                    exitCommand.execute(collection, args, true);
                }
                break;

            case ("remove_greater"):
                removeGreaterCommand.execute(collection, args, executionOfScript, iterator);
                break;

            case ("replace_if_lower"):
                replaceIfLowerCommand.execute(collection, args, executionOfScript, iterator);
                break;

            case ("remove_lower_key"):
                removeLowerKeyCommand.execute(collection, args);
                break;

            case ("remove_all_by_genre"):
                removeAllByGenreCommand.execute(collection, args);
                break;

            case ("max_by_front_man"):
                System.out.println(maxByFrontManCommand.execute(collection, args));
                break;

            case ("filter_greater_than_singles_count"):
                System.out.println(filterGreaterThanSinglesCountCommand.execute(collection, args));
                break;
            case (""):
                break;

            case ("fuck_you"):
                System.out.println("Thank you! Fuck you too!!!");
                break;


            default: throw new UnsupportedOperationException(String.format("Error: Command '%s' isn't supported.", commandLineInputArgs[0]));

        }
    }


    /**
     * @param collection
     * @param commandLineInput
     * @throws Exception
     */
    public static void executeCommand(TreeMap<Integer, MusicBand> collection, String commandLineInput) throws Exception {
        executeCommand(collection, commandLineInput, false, null);
    }


    /**
     * @return
     * @throws IOException
     */
    public static MusicBand getNewMusicBandFromStandardInput() throws IOException {
        MusicBand newMusicBand = new MusicBand();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String input;
        boolean correctInputIndicator = false;


        System.out.println(String.format("Creating new Music band. Please, fill the information...\n" +
                "Tip: You can type '%s' to finish creating the band. Attention: No input will be saved.", stopCreatingMusicBandCommand));

        while (!correctInputIndicator) {
            try {
                System.out.println("Enter band name {String}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);

                newMusicBand.setName(Validator.validateString(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + MusicBand.musicBandFieldsDescription.get("name"));
            }
        }

        newMusicBand.setCoordinates(getNewCoordinatesFromStandardInput());

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter number of participants {long}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                newMusicBand.setNumberOfParticipants(Validator.validateLongPrimitive(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + MusicBand.musicBandFieldsDescription.get("numberOfParticipants"));
            }
        }

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter number of singles {int}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                newMusicBand.setSinglesCount(Validator.validateIntegerPrimitive(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + MusicBand.musicBandFieldsDescription.get("singlesCount"));
            }
        }

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter music genre type {Genre}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                newMusicBand.setGenre(Validator.validateEnum(MusicGenre.class, input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + MusicBand.musicBandFieldsDescription.get("genre"));
            }
        }


        System.out.println("Creating new front man..." +
                "\nEnter 'empty string' to set value to 'null', enter anything else to continue creating:");

        Person person;
        input = reader.readLine().trim();
        checkForStopCreatingMusicBandException(input);
        if (Arrays.asList(Validator.getSymbolsForNullValues()).contains(input)) {
            person = null;
        } else {
            person = getNewPersonFromStandardInput();
        }

        newMusicBand.setFrontMan(person);

        System.out.println("MusicBand was created!");

        return newMusicBand;
    }


    /**
     * @return
     * @throws IOException
     */
    public static Coordinates getNewCoordinatesFromStandardInput() throws IOException {

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String input;
        boolean correctInputIndicator = false;

        Coordinates coordinates = new Coordinates();

        System.out.println("Creating new coordinates...");

        while (!correctInputIndicator) {
            try {
                System.out.println("Enter x {double} coordinate:");
                input = reader.readLine().trim();

                checkForStopCreatingMusicBandException(input);

                coordinates.setX(Validator.validateDoublePrimitive(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Coordinates.coordinatesFieldsDescription.get("x"));
            }
        }

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter y {int} coordinate:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                coordinates.setY(Validator.validateIntegerPrimitive(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Coordinates.coordinatesFieldsDescription.get("y"));
            }
        }


        return coordinates;
    }


    /**
     * @return
     * @throws IOException
     */
    public static Person getNewPersonFromStandardInput() throws IOException {
        Person person = new Person();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String input;
        boolean correctInputIndicator = false;


        System.out.println("Creating new front man...");

        while (!correctInputIndicator) {
            try {
                System.out.println("Enter front man name {String}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                person.setName(Validator.validateString(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Person.personFieldsDescription.get("name"));
            }
        }

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter front man height {Long}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                person.setHeight(Validator.validateLong(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Person.personFieldsDescription.get("height"));
            }
        }

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter front man heir color {Color}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                person.setHeirColor(Validator.validateEnum(Color.class, input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Person.personFieldsDescription.get("heirColor"));
            }
        }

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter front man nationality {Country}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                person.setNationality(Validator.validateEnum(Country.class, input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Person.personFieldsDescription.get("nationality"));
            }
        }

        person.setLocation(getNewLocationFromStandardInput());


        return person;
    }


    public static Location getNewLocationFromStandardInput() throws IOException {
        Location location = new Location();

        BufferedReader reader =
                new BufferedReader(new InputStreamReader(System.in));

        String input;
        boolean correctInputIndicator = false;


        System.out.println("Creating new location...");

        while (!correctInputIndicator) {
            try {
                System.out.println("Enter x {Integer} location coordinate:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                location.setX(Validator.validateInteger(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Location.locationFieldsDescription.get("x"));
            }
        }

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter y {int} location coordinate:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                location.setY(Validator.validateIntegerPrimitive(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Location.locationFieldsDescription.get("y"));
            }
        }

        correctInputIndicator = false;
        while (!correctInputIndicator) {
            try {
                System.out.println("Enter location name {String}:");
                input = reader.readLine().trim();
                checkForStopCreatingMusicBandException(input);
                location.setName(Validator.validateString(input));
                correctInputIndicator = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + Location.locationFieldsDescription.get("name"));
            }
        }

        return location;
    }


    /**
     * @param iterator
     * @return
     */
    public static MusicBand getNewMusicBandFromFile(Iterator<String> iterator) {
        MusicBand newMusicBand = new MusicBand();

        String input;
        try {
            try {
                newMusicBand.setName(Validator.validateString(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + MusicBand.musicBandFieldsDescription.get("name"));
            }

            newMusicBand.setCoordinates(getNewCoordinatesFromFile(iterator));

            try {
                newMusicBand.setNumberOfParticipants(Validator.validateLongPrimitive(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + MusicBand.musicBandFieldsDescription.get("numberOfParticipants"));
            }

            try {
                newMusicBand.setSinglesCount(Validator.validateIntegerPrimitive(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + MusicBand.musicBandFieldsDescription.get("singlesCount"));
            }

            try {
                newMusicBand.setGenre(Validator.validateEnum(MusicGenre.class, iterator.next().toUpperCase()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + MusicBand.musicBandFieldsDescription.get("genre"));
            }

            Person person;
            input = iterator.next();
            if (Arrays.asList(Validator.getSymbolsForNullValues()).contains(input)) {
                person = null;
            } else {
                person = getNewPersonFromFile(iterator);
            }

            newMusicBand.setFrontMan(person);

            return newMusicBand;

        } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Error: Creating new 'music band': Not all of the fields are present.");
        }
    }


    /**
     * @param iterator
     * @return
     */
    public static Coordinates getNewCoordinatesFromFile(Iterator<String> iterator) {

        Coordinates coordinates = new Coordinates();

        try {
            try {
                coordinates.setX(Validator.validateDoublePrimitive(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Coordinates.coordinatesFieldsDescription.get("x"));
            }

            try {
                coordinates.setY(Validator.validateIntegerPrimitive(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Coordinates.coordinatesFieldsDescription.get("y"));
            }

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Error: Creating new 'Coordinates': Not all of the fields are present.");
        }
        return coordinates;
    }


    /**
     * @param iterator
     * @return
     */
    public static Person getNewPersonFromFile(Iterator<String> iterator) {

        Person person = new Person();

        try {
            try {
                person.setName(iterator.next());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Person.personFieldsDescription.get("name"));
            }

            try {
                person.setHeight(Validator.validateLong(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Person.personFieldsDescription.get("height"));
            }

            try {
                person.setHeirColor(Validator.validateEnum(Color.class, iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Person.personFieldsDescription.get("heirColor"));
            }

            try {
                person.setNationality(Validator.validateEnum(Country.class, iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Person.personFieldsDescription.get("nationality"));
            }

            person.setLocation(getNewLocationFromFile(iterator));

            return person;

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Error: Creating new 'Person': Not all of the fields are present.");
        }
    }


    /**
     * @param iterator
     * @return
     */
    public static Location getNewLocationFromFile(Iterator<String> iterator) {

        Location location = new Location();

        try {
            try {
                location.setX(Validator.validateInteger(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Location.locationFieldsDescription.get("x"));
            }

            try {
                location.setY(Validator.validateIntegerPrimitive(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Location.locationFieldsDescription.get("y"));
            }

            try {
                location.setName(Validator.validateString(iterator.next()));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Error: " + Location.locationFieldsDescription.get("name"));
            }

        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Error: Creating new 'location': Not all of the fields are present.");
        }
        return location;
    }


    /**
     * @param command
     */
    private static void checkForStopCreatingMusicBandException(String command) {
        if (command.equals(stopCreatingMusicBandCommand)) {
            throw new StopCreatingTheMusicBandException("Creating new music band was stopped.");
        }
    }


    public static String getFilePath() {
        return filePath;
    }


    public static void setFilePath(String filePath) {
        CollectionManager.filePath = filePath;
    }


    public static FileManager getFileManager() {
        return fileManager;
    }


    public static void setFileManager(FileManager fileManager) {
        CollectionManager.fileManager = fileManager;
    }


    public String getStopCreatingMusicBandCommand() {
        return stopCreatingMusicBandCommand;
    }


    public void setStopCreatingMusicBandCommand(String stopCreatingMusicBandCommand) {
        CollectionManager.stopCreatingMusicBandCommand = stopCreatingMusicBandCommand;
    }
}
