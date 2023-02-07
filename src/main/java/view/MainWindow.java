package view;


import controller.Config;
import controller.Factory;
import controller.ViewController;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class MainWindow extends WindowBase {
    private Factory factory;
    private Config config;

    private JSlider bodySupplierSlider;
    private JSlider engineSupplierSlider;
    private JSlider accessorySupplierSlider;
    private JSlider WorkerSlider;
    private JSlider dealerSlider;

    private JLabel bodySupplierLabel;
    private JLabel engineSupplierLabel;
    private JLabel accessorySupplierLabel;

    private JLabel bodyStorageLabel;
    private JLabel engineStorageLabel;
    private JLabel accessoryStorageLabel;
    private JLabel carStorageLabel;

    private JLabel carsSoldLabel;

    public MainWindow(int width, int height, ViewController windowController, Factory factory, Config config){
        super(width, height, windowController);
        this.factory = factory;
        this.config = config;
        this.add(CreateSliders());
        this.add(CreateButtons());
        this.add(CreateCurrentInfo());
        StartUpdate();
    }

    private JPanel CreateSliders(){
        JPanel panel = new JPanel();
        panel.setSize(410, 475);
        panel.setLayout(null);
        bodySupplierSlider = CreateSlider();
        engineSupplierSlider = CreateSlider();
        accessorySupplierSlider = CreateSlider();
        WorkerSlider = CreateSlider();
        dealerSlider = CreateSlider();

        JLabel bodySupplierStatusLabel = new JLabel();
        bodySupplierStatusLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        JLabel engineSupplierStatusLabel = new JLabel();
        engineSupplierStatusLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        JLabel accessorySupplierStatusLabel = new JLabel();
        accessorySupplierStatusLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        JLabel WorkerStatusLabel = new JLabel();
        WorkerStatusLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        JLabel dealerStatusLabel = new JLabel();
        dealerStatusLabel.setFont(new Font("Verdana", Font.PLAIN, 12));

        bodySupplierSlider.addChangeListener(e -> bodySupplierStatusLabel.setText("Body parts: " + ((JSlider) e.getSource()).getValue() + "/s"));
        engineSupplierSlider.addChangeListener(e -> engineSupplierStatusLabel.setText("Engine parts: " + ((JSlider) e.getSource()).getValue() + "/s"));
        accessorySupplierSlider.addChangeListener(e -> accessorySupplierStatusLabel.setText("Accessory parts: " + ((JSlider) e.getSource()).getValue() + "/s"));
        WorkerSlider.addChangeListener(e -> WorkerStatusLabel.setText("Workers speed parts: " + ((JSlider) e.getSource()).getValue() + "/s"));
        dealerSlider.addChangeListener(e -> dealerStatusLabel.setText("Dealers demand cars: " + ((JSlider) e.getSource()).getValue() + "/s"));

        bodySupplierSlider.setValue(1);
        engineSupplierSlider.setValue(1);
        accessorySupplierSlider.setValue(1);
        WorkerSlider.setValue(1);
        dealerSlider.setValue(1);

        bodySupplierStatusLabel.setBounds(65, 34, 200, 25);
        engineSupplierStatusLabel.setBounds(60, 124, 200, 25);
        accessorySupplierStatusLabel.setBounds(50, 214, 200, 25);
        WorkerStatusLabel.setBounds(30, 304, 200, 25);
        dealerStatusLabel.setBounds(30, 394, 200, 25);

        panel.add(bodySupplierStatusLabel);
        panel.add(engineSupplierStatusLabel);
        panel.add(accessorySupplierStatusLabel);
        panel.add(WorkerStatusLabel);
        panel.add(dealerStatusLabel);

        bodySupplierSlider.setBounds(200, 35, 200, 50);
        engineSupplierSlider.setBounds(200, 125, 200, 50);
        accessorySupplierSlider.setBounds(200, 215, 200, 50);
        WorkerSlider.setBounds(200, 305, 200, 50);
        dealerSlider.setBounds(200, 395, 200, 50);

        panel.add(bodySupplierSlider);
        panel.add(engineSupplierSlider);
        panel.add(accessorySupplierSlider);
        panel.add(WorkerSlider);
        panel.add(dealerSlider);
        panel.setBackground(Color.getHSBColor(192,192,238));
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        panel.setBorder( BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel));
        return panel;
    }

    private JSlider CreateSlider(){
        JSlider slider = new JSlider();
        slider.setMinimum(1);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.setPaintLabels(true);
        slider.setLabelTable(getSliderValuesTable());
        slider.setBackground(Color.lightGray);
        return slider;
    }

    private static Hashtable<Integer, JLabel> getSliderValuesTable() {
        Hashtable<Integer, JLabel> position = new Hashtable<Integer, JLabel>();
        position.put(1, new JLabel("1"));
        position.put(25, new JLabel("25"));
        position.put(50, new JLabel("50"));
        position.put(75, new JLabel("75"));
        position.put(100, new JLabel("100"));
        return position;
    }

    private JPanel CreateButtons() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,4,3,4));
        panel.setBounds(0, getHeight() - 125, getWidth()-15, 87);
        panel.setBackground(Color.lightGray);

        JButton applyButton = new JButton("Apply settings");
        applyButton.addActionListener((e) -> ViewController.ApplyConfiguration());


        JButton startButton = new JButton("Start");
        startButton.addActionListener((e) -> ViewController.StartFactory());

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener((e) -> ViewController.StopFactory());

        JButton showButton = new JButton("Show Config");
        showButton.addActionListener((e -> ViewController.Show()));
        showButton.setSize(new Dimension(100,100));
        panel.add(startButton);
        panel.add(applyButton);
        panel.add(stopButton);
        panel.add(showButton);
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        panel.setBorder( BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel));
        return panel;
    }


    private JPanel CreateCurrentInfo() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(409, 0, 276, 475);

        bodyStorageLabel = new JLabel();
        bodyStorageLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        bodyStorageLabel.setBounds(10, 35, 200, 25);

        engineStorageLabel = new JLabel();
        engineStorageLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        engineStorageLabel.setBounds(10, 60, 200, 25);

        accessoryStorageLabel = new JLabel();
        accessoryStorageLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        accessoryStorageLabel.setBounds(10, 85, 200, 25);

        carStorageLabel = new JLabel();
        carStorageLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        carStorageLabel.setBounds(10, 110, 200, 25);

        carsSoldLabel = new JLabel();
        carsSoldLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        carsSoldLabel.setBounds(10, 135, 200, 25);

        bodySupplierLabel = new JLabel();
        bodySupplierLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        bodySupplierLabel.setBounds(10, 160, 200, 25);

        engineSupplierLabel = new JLabel();
        engineSupplierLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        engineSupplierLabel.setBounds(10, 185, 200, 25);

        accessorySupplierLabel = new JLabel();
        accessorySupplierLabel.setFont(new Font("Verdana", Font.PLAIN, 12));
        accessorySupplierLabel.setBounds(10, 210, 200, 25);

        panel.add(bodySupplierLabel);
        panel.add(engineSupplierLabel);
        panel.add(accessorySupplierLabel);
        panel.add(bodyStorageLabel);
        panel.add(engineStorageLabel);
        panel.add(accessoryStorageLabel);
        panel.add(carStorageLabel);
        panel.add(carsSoldLabel);
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        Border loweredbevel = BorderFactory.createLoweredBevelBorder();
        panel.setBorder( BorderFactory.createCompoundBorder(
                raisedbevel, loweredbevel));
        panel.setBackground(Color.lightGray);
        return panel;
    }

    private void StartUpdate(){
        Update();
        ActionListener updateTask = e -> {
            String date = new SimpleDateFormat("HH:mm:ss").format(new Date(System.currentTimeMillis()));
            Update();
        };

        new Timer(100, updateTask).start();
    }

    private void Update(){
        bodySupplierLabel.setText("Bodies provided: " + factory.BodyStorage().getItemsPut());
        engineSupplierLabel.setText("Engines provided: " + factory.EngineStorage().getItemsPut());
        accessorySupplierLabel.setText("Accessories provided: " + factory.AccessoryStorage().getItemsPut());
        bodyStorageLabel.setText("Body storage: " + factory.BodyStorage().getItemsList().size() + "/" + factory.BodyStorage().getSize());
        engineStorageLabel.setText("Engine storage: " + factory.EngineStorage().getItemsList().size() + "/" + factory.EngineStorage().getSize());
        accessoryStorageLabel.setText("Accessory storage: " + factory.AccessoryStorage().getItemsList().size() + "/" + factory.AccessoryStorage().getSize());
        carStorageLabel.setText("Car storage: " + factory.CarStorage().getItemsList().size() + "/" + factory.CarStorage().getSize());
        carsSoldLabel.setText("Cars sold: " + factory.CarStorage().getItemsTaken());
    }

    public int BodySupplierSpeed(){
        return bodySupplierSlider.getValue();
    }

    public int EngineSupplierSpeed(){
        return engineSupplierSlider.getValue();
    }

    public int AccessorySupplierSpeed(){
        return accessorySupplierSlider.getValue();
    }

    public int WorkerSpeed(){
        return WorkerSlider.getValue();
    }

    public int DealerSpeed(){
        return dealerSlider.getValue();
    }
}
