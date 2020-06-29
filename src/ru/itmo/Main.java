package ru.itmo;












// Создась пакет "main" и переместить пакет 'lib' вне 'src'.
/**
 * @author Maxim Monakhov
 * @version 0.6
 */

import ru.itmo.classes.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

public class Main {


    /**
     * @param args
     */
    public static void main(String[] args) {
//        MusicBand beatles = new MusicBand(1, "Beatles", new Coordinates(161, 16), 7, 3, MusicGenre.PROGRESSIVE_ROCK, new Person("John Lennon", 195L, Color.RED, Country.GERMANY, new Location(1, 2, "Scene")));
//        MusicBand pinkFloyd = new MusicBand(1, "Pink Floyd", new Coordinates(16, 19), 5, 2, MusicGenre.POST_ROCK, new Person("Nick Mason", 175L, Color.ORANGE, Country.ITALY, new Location(6, 4, "KFC")));
//        MusicBand rollingStones = new MusicBand(3, "The Rolling Stones", new Coordinates(-16, -19), 5, 3, MusicGenre.PUNK_ROCK, new Person("Brian Jones", 180L, Color.WHITE, Country.CHINA, new Location(-18, 18, "Highway")));
//
//        TreeMap<Integer, MusicBand> collection = new TreeMap<>();
//
//        collection.put(1, beatles);
//        collection.put(2, pinkFloyd);
//        collection.put(3, rollingStones);
//
////**********************************************************************
        String filepath;
//        String filePath = System.getenv("COLLECTION_PATH");
//        if (filePath == null) {
//            System.out.println("Error: Env var 'COLLECTION_PATH' doesn't exist.");
//            return;
//        }

        String filePath = "collection";

        FileManager fileManager;
        TreeMap<Integer, MusicBand> collectionFromFile = new TreeMap<>();
        try {
            fileManager = new FileManager(); ////////////////
            collectionFromFile = fileManager.getCollectionFromFile(filePath);

            checkCollectionForKeysIDsIdentity(collectionFromFile);

            checkCollectionForFieldsValues(collectionFromFile);

            System.out.println("Collection was loaded successfully!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }


        String command;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Validator.setSymbolsForNullValues(new String[]{"", " "});
        CollectionManager.setFileManager(fileManager); //Is needed only for printing collection's DateOfInitialisation when running command "info
        CollectionManager.setFilePath(filePath);

        while (true) {
            try {
                command = reader.readLine();
                CollectionManager.executeCommand(collectionFromFile, command);
            } catch (IOException e) {
                System.out.println("Error: Unknown error during reading the input was occurred.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    /**
     * @param collection
     */
    public static void checkCollectionForKeysIDsIdentity(TreeMap<Integer, MusicBand> collection) {
        for (Map.Entry<Integer, MusicBand> entry: collection.entrySet()) {
            if (!entry.getKey().equals(entry.getValue().getId())) {
                throw new IllegalArgumentException(String.format("Error: Key isn't identical to ID. Element: key: '%s', id: '%s'", entry.getKey(), entry.getValue().getId()));
            }
        }
    }


    /**
     * @param collection
     */
    public static void checkCollectionForFieldsValues(TreeMap<Integer, MusicBand> collection) {
        for (Integer key: collection.keySet()) {
            try {
                MusicBand musicBand = new MusicBand();
                Person person = new Person();
                Coordinates coordinates = new Coordinates();
                Location location = new Location();

                MusicBand musicBandParsed = collection.get(key);
                Coordinates coordinatesParsed = musicBandParsed.getCoordinates();
                Person personParsed = musicBandParsed.getFrontMan();

                if (personParsed == null) person = null;
                else {
                    Location locationParsed = personParsed.getLocation();
                    location.setX(locationParsed.getX());
                    location.setY(locationParsed.getY());
                    location.setName(locationParsed.getName());

                    person.setName(personParsed.getName());
                    person.setHeight(personParsed.getHeight());
                    person.setHeirColor(personParsed.getHeirColor());
                    person.setNationality(personParsed.getNationality());
                    person.setLocation(location);
                }

                coordinates.setX(coordinatesParsed.getX());
                coordinates.setY(coordinatesParsed.getY());

                musicBand.setId(musicBandParsed.getId());
                musicBand.setName(musicBandParsed.getName());
                musicBand.setCoordinates(coordinates);
                musicBand.setNumberOfParticipants(musicBandParsed.getNumberOfParticipants());
                musicBand.setSinglesCount(musicBandParsed.getSinglesCount());
                musicBand.setGenre(musicBandParsed.getGenre());
                musicBand.setFrontMan(person);


            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException(String.format("Error: Element with key: '%s'\n%s", key, e.getMessage()));
            }
        }
    }


    /**
     * @param numberOfBands
     * @return
     */
    // For testing the Method "checkCollectionForFieldsValues" productivity
    //    checkCollectionForFieldsValues(generateHugeCollection(900000));
//            System.out.println("(Java) : I'm ready!");
    public static TreeMap<Integer, MusicBand> generateHugeCollection(Integer numberOfBands) {
        TreeMap<Integer, MusicBand> collection = new TreeMap<>();

        for (int i = 0; i < numberOfBands; i++) {
            MusicBand beatles = new MusicBand(i, "Beatles", new Coordinates(161, 16), 7, 3, MusicGenre.PROGRESSIVE_ROCK, new Person("John Lennon", 195L, Color.RED, Country.GERMANY, new Location(1, 2, "Scene")));
            collection.put(i, beatles);
        }
        return collection;
    }
}
