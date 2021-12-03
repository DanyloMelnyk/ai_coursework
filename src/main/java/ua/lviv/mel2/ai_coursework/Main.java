package ua.lviv.mel2.ai_coursework;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Main {

    public static final int WIDTH = 640;
    public static final int HEIGHT = 380;
    private static final String[] FILTERS = new String[]{
            "gauss", "bilateral", "blur", "medianBlur", "sobel"
    };

    public static void main(String[] args) {
        System.load("C:\\projects\\opencv\\opencv\\build\\java\\x64\\opencv_java454.dll");

        javax.swing.SwingUtilities.invokeLater(Main::createGUI);
    }

    public static void createGUI() {
        JFrame frame = new JFrame("Test frame");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        var pane = createVerticalPanel();

        var image = new Image(new File("lychakiv.png"), WIDTH / 2);
        var image2 = new Image(new File("lychakiv.png"), WIDTH / 2);
        pane.add(actionsPanel(image2));


        pane.add(imagesPanel(image, image2));
        frame.getContentPane().add(pane, "North");

        frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        frame.pack();
        frame.setVisible(true);
    }

    private static JPanel actionsPanel(Image img) {
        var panel = createVerticalPanel();//new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 2));
        panel.add(new ChooseFilePanel());
        panel.add(new ChooseFilterPanel(filter -> applyFilter(img, filter), FILTERS));

//        panel.add(fileChooser);

        return panel;
    }

    private static JPanel imagesPanel(Image original, Image mod) {
        var panel = new JPanel(new GridLayout(1, 2));

        var col1 = createVerticalPanel();
        col1.add(new JLabel("Original"));
        col1.add(original);

        panel.add(col1);

        var col2 = createVerticalPanel();
        col2.add(new JLabel("Filtered"));
        col2.add(mod);

        panel.add(col2);

        return panel;
    }

    public static JPanel createVerticalPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        return p;
    }

    private static void applyFilter(Image image, String filterName) {
        switch (filterName) {
            case "gauss" -> image.gauss();
            case "bilateral" -> image.bilateral();
            case "blur" -> image.blur();
            case "medianBlur" -> image.medianBlur();
            case "sqrBox" -> image.sqrBox();
            case "sobel" -> image.sobel();
            default -> throw new RuntimeException("Bad filter name" + filterName);
        }
    }
}
