package models.supplier;

import models.storage.Accessory;
import models.storage.Storage;

import java.util.UUID;

public class AccessorySupplier extends Supplier<Accessory>{
    public AccessorySupplier(Storage<Accessory> storage, int delay) {
        super(storage, delay);
    }
    @Override
    protected void Supply() {
        if (Storage.getItemsList().size() != Storage.getSize()){
            try {
                Accessory accessory = new Accessory(UUID.randomUUID(),"Accessory id" + Math.random()*100);
                Storage.Add(accessory);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
