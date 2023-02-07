package controller;

import models.storage.Car;
import models.storage.Storage;
public class CarController extends Thread{
    private Storage<Car> carStorage;
    private Factory factory;

    public CarController(Factory factory){
        this.factory = factory;
        this.carStorage = factory.CarStorage();
    }

    @Override
    public void run(){
        while (!interrupted()) {
            if (carStorage.isFull() && factory.IsAlive()){
                factory.Stop();
            }

            else if (!carStorage.isFull() && !factory.IsAlive()){
                factory.Start();
            }
        }
    }
}
