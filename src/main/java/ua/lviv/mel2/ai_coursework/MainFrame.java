package ua.lviv.mel2.ai_coursework;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class MainFrame extends JFrame {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 800;
    private static final String[] FILTERS = new String[]{
            "gauss", "bilateral", "blur", "medianBlur", "sobel"
    };

    private Image originalImage;
    private Image filteredImage;
    private String currentFilter;

    public MainFrame() {
        super("Test frame");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loadImage("empty.png");

        getContentPane().add(buildContent(), "North");

        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        pack();
    }

    private static JPanel createVerticalPanel() {
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
        var slider = new JSlider(new DefaultBoundedRangeModel(51, 2, 1, 90));
        panel.add(slider);
        slider.addChangeListener(e -> {
            System.out.println(((JSlider) e.getSource()).getValue()); //FIXME
        });
        slider.setPaintLabels(true);

        return panel;
    }

    private void loadImage(String fileName) {
        originalImage = new Image(new File(fileName), WIDTH / 2);
        filteredImage = new Image(new File(fileName), WIDTH / 2);
    }

    private void applyFilter(Image image, String filterName) {
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
