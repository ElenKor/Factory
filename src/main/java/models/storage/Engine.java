package models.storage;

import models.storage.CarPart;

import java.util.UUID;

public class Engine extends CarPart {
    public Engine(UUID uuid, String name) {
        super(uuid, name);
    }
}
