package ua.lviv.mel2.ai_coursework;

import ua.lviv.mel2.ai_coursework.gui.MainFrame;

public class Main {
    public static void main(String[] args) {
        System.load("C:\\projects\\opencv\\opencv\\build\\java\\x64\\opencv_java454.dll");

        javax.swing.SwingUtilities.invokeLater(Main::createGUI);
    }

    public static void createGUI() {
        var mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
