package controller;

import view.ConfigFrame;
import view.MainWindow;

import javax.swing.*;
import java.awt.*;

public class ViewController {
    private static MainWindow mainWindow;

    private static CarController carStorageController;
    private static Factory factory;
    private static Config config;

    public ViewController(CarController carStorageController, Factory factory, Config config){
        ViewController.carStorageController = carStorageController;
        ViewController.factory = factory;
        ViewController.config = config;
        mainWindow = new MainWindow(700, 600, this, factory, config);
        mainWindow.setVisible(true);
    }

    public void CloseAll(){
        mainWindow.setVisible(false);
    }

    public void ShowMainWindow(){
        CloseAll();
        mainWindow.setVisible(true);
    }

    public static void ApplyConfiguration(){
        config.BodySupplierSpeed = mainWindow.BodySupplierSpeed();
        config.EngineSupplierSpeed = mainWindow.EngineSupplierSpeed();
        config.AccessorySupplierSpeed = mainWindow.AccessorySupplierSpeed();
        config.WorkerSpeed = mainWindow.WorkerSpeed();
        config.DealerSpeed = mainWindow.DealerSpeed();
        factory.ConfigureSpeed(config);
    }

    public static void StartFactory() {
        if (!factory.IsAlive()){
            factory.Start();
        }

        if (carStorageController.isInterrupted()){
            carStorageController.start();
        }
    }

    public static void StopFactory(){
        if (carStorageController.isAlive()){
            carStorageController.interrupt();
        }

        if (factory.IsAlive()){
            factory.Stop();
        }
    }
    public static void Show(){
        Thread thread = new Thread(() ->
        {
                JFrame frame = new ConfigFrame(new Config());
                frame.setVisible(true);

        });
        thread.start();
    }
}
