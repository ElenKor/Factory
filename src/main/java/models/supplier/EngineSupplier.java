package models.supplier;

import models.storage.Body;
import models.storage.Engine;

import java.util.UUID;

public class EngineSupplier extends Supplier<Engine>{
    public EngineSupplier(models.storage.Storage<Engine> storage, int delay) {
        super(storage, delay);
    }
    @Override
    protected void Supply() {
        if (Storage.getItemsList().size() != Storage.getSize()){
            try {
                Engine engine = new Engine(UUID.randomUUID(),"Body id" + Math.random()*100);
                Storage.Add(engine);
                System.out.println("ADDED ENGINE:" + engine+"\n");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
