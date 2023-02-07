package view;

import controller.ViewController;

import javax.swing.*;

public class WindowBase extends JFrame {
    protected final String Title = "Factory";

    protected int Width;
    protected int Height;
    protected ViewController viewController;

    public WindowBase(int width, int height, ViewController viewController){
        this.Width = width;
        this.Height = height;
        this.viewController = viewController;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(Title);
        setSize(width, height);
        setLocationRelativeTo(null);
        setLayout(null);
    }
}
