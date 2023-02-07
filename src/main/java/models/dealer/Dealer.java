package models.dealer;

import models.storage.Car;
import models.storage.Storage;

import java.util.logging.Logger;

public class Dealer extends Thread{
    private Logger logger;
    private Storage<Car> carStorage;
    private int delay;

    public Dealer(Storage<Car> carStorage, int delay, Logger logger){
        this.carStorage = carStorage;
        this.delay = delay;
        this.logger = logger;
    }

    @Override
    public void run(){
        while (!interrupted()) {
            try {
                sleep(delay);
                deal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void deal() throws InterruptedException {
        if(carStorage.getItemsList().size() != 0){
            Car car = carStorage.Get();
            Log(car, currentThread().getId());
        }
    }

    private synchronized void Log(Car car, long id){
        if (logger == null){
            return;
        }

        logger.info("Car Sold: Dealer " + id + ": " + car.toString());
        notifyAll();
    }
}
