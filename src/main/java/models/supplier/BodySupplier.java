package models.supplier;

import models.storage.Accessory;
import models.storage.Body;
import models.storage.Storage;

import java.util.UUID;

public class BodySupplier extends Supplier<Body> {
    public BodySupplier(models.storage.Storage<Body> storage, int delay) {
        super(storage, delay);
    }
    @Override
    protected void Supply() {
        if (Storage.getItemsList().size() != Storage.getSize()){
            try {
                Body body = new Body(UUID.randomUUID(),"Body id" + Math.random()*100);
                Storage.Add(body);
                System.out.println("ADDED BODY:" + body+"\n");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
