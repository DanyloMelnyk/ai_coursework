package ua.lviv.mel2.ai_coursework;

import ua.lviv.mel2.ai_coursework.filters.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 800;

    private final Filter[] FILTERS = new Filter[]{
            new Blur(this),
            new MedianBlur(this),
            new GaussianBlur(this),
            new Canny(this),
            new SqrBox(this),
            new BilateralFilter(this)
    };

    private Image originalImage;
    private Image filteredImage;
    private Filter currentFilter;

    public MainFrame() {
        super("Test frame");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loadImage("empty.png");

        getContentPane().add(buildContent(), "North");

        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        pack();
    }

    public static JPanel createVerticalPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        return p;
    }

    private JPanel buildContent() {
        var pane = createVerticalPanel();

        pane.add(actionsPanel());

        pane.add(buildImagesPanel());

        return pane;
    }

    private JPanel buildImagesPanel() {
        var panel = new JPanel(new GridLayout(1, 2));

        var col1 = createVerticalPanel();
        col1.add(new JLabel("Original"));
        col1.add(originalImage);

        panel.add(col1);

        var col2 = createVerticalPanel();
        col2.add(new JLabel("Filtered"));
        col2.add(filteredImage);

        panel.add(col2);

        return panel;
    }

    private JPanel actionsPanel() {
        var panel = createVerticalPanel();
        panel.add(new ChooseFilePanel(imagePath -> {
            originalImage.load(imagePath);
            filteredImage.load(imagePath);
            applyFilter(filteredImage, currentFilter);
        }));

        panel.add(new ChooseFilterPanel(filter -> {
            applyFilter(filteredImage, filter);
            currentFilter = filter;
        }, FILTERS));

        return panel;
    }

    private void loadImage(String fileName) {
        originalImage = new Image(new File(fileName), WIDTH / 2);
        filteredImage = new Image(new File(fileName), WIDTH / 2);
    }

    private void applyFilter(Image image, Filter filter) {
        image.applyFilter(filter);
    }

    public void update() {
        applyFilter(filteredImage, currentFilter);
    }
}
