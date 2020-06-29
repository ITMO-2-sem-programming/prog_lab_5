package ru.itmo;

public class StopCreatingTheMusicBandException extends RuntimeException {

    public StopCreatingTheMusicBandException(String message) {
        super(message);
    }
    public StopCreatingTheMusicBandException(){};
}
