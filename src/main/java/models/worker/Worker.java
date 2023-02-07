package models.worker;

import models.storage.*;

public class Worker extends Thread {
    private Storage<Body> bodyStorage;
    private Storage<Engine> engineStorage;
    private Storage<Accessory> accessoryStorage;
    private Storage<Car> carStorage;

    private static int startId = 0;
    private int id;

    private Car car;
    private int delay;

    public Worker(Storage<Body> bodyStorage, Storage<Engine> engineStorage, Storage<Accessory> accessoryStorage, Storage<Car> carStorage, int delay){
        this.bodyStorage = bodyStorage;
        this.engineStorage = engineStorage;
        this.accessoryStorage = accessoryStorage;
        this.carStorage = carStorage;
        this.delay = delay;
        this.id = startId++;
    }

    @Override
    public void run(){
        while (!interrupted()) {
            try {
                sleep(delay);
                make();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void make() throws InterruptedException {
        if (car == null){
            car = new Car("Car");
            return;
        }

        if (car.getBody() == null){
            Body body = bodyStorage.Get();
            car.setBody(body);
            return;
        }

        if (car.getEngine() == null){
            Engine engine = engineStorage.Get();
            car.setEngine(engine);
            return;
        }

        if (car.getAccessory() == null){
            Accessory accessory = accessoryStorage.Get();
            car.setAccessory(accessory);
            return;
        }
        carStorage.Add(car);
        System.out.println("ADDED CAR:" + car.getName()+"\n");
        car = null;
    }

    public int get_Id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Storage<Body> getBodyStorage() {
        return bodyStorage;
    }

    public void setBodyStorage(Storage<Body> bodyStorage) {
        this.bodyStorage = bodyStorage;
    }

    public Storage<Engine> getEngineStorage() {
        return engineStorage;
    }

    public void setEngineStorage(Storage<Engine> engineStorage) {
        this.engineStorage = engineStorage;
    }

    public Storage<Accessory> getAccessoryStorage() {
        return accessoryStorage;
    }

    public void setAccessoryStorage(Storage<Accessory> accessoryStorage) {
        this.accessoryStorage = accessoryStorage;
    }

    public Storage<Car> getCarStorage() {
        return carStorage;
    }

    public void setCarStorage(Storage<Car> carStorage) {
        this.carStorage = carStorage;
    }

    public static int getStartId() {
        return startId;
    }

    public static void setStartId(int startId) {
        Worker.startId = startId;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
