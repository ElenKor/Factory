import controller.CarController;
import controller.Config;
import controller.Factory;
import controller.ViewController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.LogManager;

public class Main {
    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("/Users/elina/Desktop/java_lab/lab7/src/main/resources/logger.properties"));
        } catch (IOException e) {
            System.err.println("Could not setup logger configuration: " + Arrays.toString(e.getStackTrace()));
        }
        Factory factory = new Factory(new Config());
        CarController carController = new CarController(factory);
        ViewController windowController = new ViewController(carController, factory, new Config());
        carController.start();
    }
}
