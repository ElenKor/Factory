package models.storage;

import java.util.UUID;

public abstract class CarPart {
    private UUID id;
    private String name;

    public CarPart(UUID uuid, String name) {
        this.id = uuid;
        this.name = name;
    }

    public UUID getUuid() {
        return id;
    }

    public void setUuid(UUID uuid) {
        this.id = uuid;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\n';
    }
}
