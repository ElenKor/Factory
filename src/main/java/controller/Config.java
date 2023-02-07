package controller;

import java.io.FileInputStream;
import java.util.Properties;

public class Config {
    private final String BodySizeProperty = "BodySize";
    private final String EngineSizeProperty = "EngineSize";
    private final String AccessorySizeProperty = "AccessorySize";
    private final String CarSizeProperty = "CarSize";

    private final String BodySupplierProperty = "BodySupplier";
    private final String EngineSupplierProperty = "EngineSupplier";
    private final String AccessorySupplierProperty = "AccessorySupplier";

    private final String WorkersCountProperty = "Workers";
    private final String DealersCountProperty = "Dealers";

    private final String LogProperty = "Log";

    private int bodyStorageSize = 10;
    private int engineStorageSize = 10;
    private int accessoryStorageSize = 10;
    private int carStorageSize = 10;

    private int bodySupplierCount = 1;
    private int engineSupplierCount = 1;
    private int accessorySupplierCount = 1;

    private int workersCount = 1;
    private int dealersCount = 1;

    public int BodySupplierSpeed = 1;
    public int EngineSupplierSpeed = 1;
    public int AccessorySupplierSpeed = 1;
    public int WorkerSpeed = 1;
    public int DealerSpeed = 1;

    private boolean log = true;

    public Config(){
        Load();
    }

    public void Load() {
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("C:\\Users\\User\\IdeaProjects\\lab7\\src\\main\\resources\\config.properties"));
            bodyStorageSize = Integer.parseInt(properties.getProperty(BodySizeProperty));
            engineStorageSize = Integer.parseInt(properties.getProperty(EngineSizeProperty));
            accessoryStorageSize = Integer.parseInt(properties.getProperty(AccessorySizeProperty));
            carStorageSize = Integer.parseInt(properties.getProperty(CarSizeProperty));

            bodySupplierCount = Integer.parseInt(properties.getProperty(BodySupplierProperty));
            engineSupplierCount = Integer.parseInt(properties.getProperty(EngineSupplierProperty));
            accessorySupplierCount = Integer.parseInt(properties.getProperty(AccessorySupplierProperty));

            workersCount = Integer.parseInt(properties.getProperty(WorkersCountProperty));
            dealersCount = Integer.parseInt(properties.getProperty(DealersCountProperty));

            log = Boolean.parseBoolean(properties.getProperty(LogProperty));

        } catch (Exception e) {
            System.out.println("Invalid config file!");
        }
    }

    public int BodyStorageSize(){
        return bodyStorageSize;
    }

    public int EngineStorageSize(){
        return engineStorageSize;
    }

    public int AccessoryStorageSize(){
        return accessoryStorageSize;
    }

    public int CarStorageSize(){
        return carStorageSize;
    }

    public int BodySupplierCount(){
        return bodySupplierCount;
    }

    public int EngineSupplierCount(){
        return engineSupplierCount;
    }

    public int AccessorySupplierCount(){
        return accessorySupplierCount;
    }

    public int WorkersCount(){
        return workersCount;
    }

    public int DealersCount(){
        return dealersCount;
    }

    public boolean Log(){
        return log;
    }
}
