package view;

import controller.Config;

import javax.swing.*;
import java.awt.*;

public class ConfigFrame extends JFrame {


    public ConfigFrame(Config config) throws HeadlessException {
        setSize(200, 300);
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("BodyStorageSize: " + config.BodyStorageSize() + "\n");
        label1.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label1);
        JLabel label2 = new JLabel("EngineStorageSize: " + config.EngineStorageSize() + "\n");
        label2.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label2);
        JLabel label3 = new JLabel("AccessoryStorageSize: " + config.AccessoryStorageSize() + "\n");
        label3.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label3);
        JLabel label4 = new JLabel("CarStorageSize: " + config.CarStorageSize() + "\n");
        label4.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label4);
        JLabel label5 = new JLabel("BodySuppliersCount: " + config.BodySupplierCount() + "\n");
        label5.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label5);
        JLabel label6 = new JLabel("EngineSuppliersCount: " + config.EngineSupplierCount() + "\n");
        label6.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label6);
        JLabel label7 = new JLabel("WorkersCount: " + config.WorkersCount() + "\n");
        label7.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label7);
        JLabel label8 = new JLabel("DealersCount: " + config.DealersCount() + "\n");
        label8.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label8);
        JLabel label9 = new JLabel("Log: " + config.Log());
        label9.setFont(new Font("Verdana", Font.PLAIN, 12));
        panel.add(label9);
        add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
}
