package models.storage;

import models.storage.CarPart;

import java.util.UUID;

public class Body extends CarPart {
    public Body(UUID uuid, String name) {
        super(uuid, name);
    }
}
