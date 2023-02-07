package controller;

import com.google.common.util.concurrent.MoreExecutors;
import models.dealer.Dealer;
import models.storage.*;
import models.supplier.AccessorySupplier;
import models.supplier.BodySupplier;
import models.supplier.EngineSupplier;
import models.worker.Worker;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Factory {
    private Storage<Body> carBodyStorage;
    private Storage<Engine> carEngineStorage;
    private Storage<Accessory> carAccessoryStorage;
    private Storage<Car> carsStorage;
    private ArrayList<BodySupplier> carBodySupplier;
    private ArrayList<EngineSupplier> carEngineSupplier;
    private ArrayList<AccessorySupplier> carAccessorySupplier;
    private ArrayList<Worker> workers;
    private ArrayList<Dealer> dealers;

    private boolean isAlive;

    private int threadsCount;
    private ThreadPoolExecutor pool;
    private ExecutorService executorService;

    private Logger logger;

    public Factory(Config config){
        this.logger = Logger.getLogger(Factory.class.getName()) ;
        this.Configure(config);
    }

    public void Configure(Config config) {
        carBodyStorage = new Storage<Body>(config.BodyStorageSize());
        carEngineStorage = new Storage<Engine>(config.EngineStorageSize());
        carAccessoryStorage = new Storage<Accessory>(config.AccessoryStorageSize());
        cfg(config);
    }



    public void ConfigureSpeed(Config config){
        cfg(config);
    }

    public synchronized void Start(){
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(threadsCount);
        executorService = MoreExecutors.getExitingExecutorService(pool, 100, TimeUnit.MILLISECONDS);
        for (int i = 0; i < carBodySupplier.size(); i++){
            executorService.submit(carBodySupplier.get(i));
        }

        for (int i = 0; i < carEngineSupplier.size(); i++){
            executorService.submit(carEngineSupplier.get(i));
        }

        for (int i = 0; i < carAccessorySupplier.size(); i++){
            executorService.submit(carAccessorySupplier.get(i));
        }

        for (int i = 0; i < workers.size(); i++){
            executorService.submit(workers.get(i));
        }

        for (int i = 0; i < dealers.size(); i++){
            executorService.submit(dealers.get(i));
        }

        isAlive = true;
    }

    public synchronized void Stop(){
        executorService.shutdownNow();
        isAlive = false;
    }

    public boolean IsAlive(){
        return isAlive;
    }

    public Storage<Body> BodyStorage(){
        return carBodyStorage;
    }

    public Storage<Engine> EngineStorage(){
        return carEngineStorage;
    }

    public Storage<Accessory> AccessoryStorage(){
        return carAccessoryStorage;
    }

    public Storage<Car> CarStorage(){
        return carsStorage;
    }
    private void cfg(Config config) {
        boolean needRestart = false;
        if (isAlive) {
            needRestart = true;
            Stop();
        }
        carsStorage = new Storage<Car>(config.CarStorageSize());
        carBodySupplier = new ArrayList<>();
        carEngineSupplier = new ArrayList<>();
        carAccessorySupplier = new ArrayList<>();
        workers = new ArrayList<>();
        dealers = new ArrayList<>();
        for (int i = 0; i < config.BodySupplierCount(); i++){
            carBodySupplier.add(new BodySupplier(carBodyStorage, 1000 / config.BodySupplierSpeed));
        }

        for (int i = 0; i < config.EngineSupplierCount(); i++){
            carEngineSupplier.add(new EngineSupplier(carEngineStorage, 1000 / config.EngineSupplierSpeed));
        }

        for (int i = 0; i < config.AccessorySupplierCount(); i++){
            carAccessorySupplier.add(new AccessorySupplier(carAccessoryStorage, 1000 / config.AccessorySupplierSpeed));
        }

        for (int i = 0; i < config.WorkersCount(); i++){
            workers.add(new Worker(carBodyStorage, carEngineStorage, carAccessoryStorage, carsStorage, 1000 / config.WorkerSpeed));
        }


        if (!config.Log()){
            logger = null;
        }

        for (int i = 0; i < config.DealersCount(); i++){
            dealers.add(new Dealer(carsStorage, 1000 / config.DealerSpeed, logger));
        }
        threadsCount = config.BodySupplierCount() + config.EngineSupplierCount() + config.AccessorySupplierCount() + config.WorkersCount() + config.DealersCount();
        if (needRestart){
            Start();
        }
    }
}
