package ua.lviv.mel2.ai_coursework.gui;

import ua.lviv.mel2.ai_coursework.filters.*;
import ua.lviv.mel2.ai_coursework.gui.actions.SaveImageAction;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public static final int WIDTH = 1500;
    public static final int HEIGHT = 800;

    private final Filter[] FILTERS = new Filter[]{
            new Blur(this),
            new GaussianBlur(this),
            new MedianBlur(this),
            new BilateralFilter(this),

            new UnsharpFilter(this),

//            new PyrDown(this),
//            new PyrUp(this),

            new Canny(this),
            new LaplacianFilter(this),
            new Sobel(this),

            new Erosion(this),
            new Dilating(this)
    };

    private Image originalImage1;

    private Image filteredImage1;

    private Filter currentFilter;

    public MainFrame() {
        super("Test frame");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        loadImage("empty.png");

        setContentPane(buildContent());

        setSize(WIDTH, HEIGHT);
//        setMinimumSize(new Dimension(WIDTH, HEIGHT));
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
        var panel = createVerticalPanel();
        panel = new JPanel(new BorderLayout());
        var images = new JPanel(new GridLayout(1, 2));

        var col1 = createVerticalPanel();
        col1.add(new JLabel("Original"));
        col1.add(new JScrollPane(originalImage1));

        images.add(col1);


        var col2 = createVerticalPanel();
        col2.add(new JLabel("Filtered"));
        col2.add(new JScrollPane(filteredImage1));

        images.add(col2);
        panel.add(images);
        panel.add(new JButton(new SaveImageAction(filteredImage1)), BorderLayout.SOUTH);

        return panel;
    }

    private JPanel actionsPanel() {
        var panel = createVerticalPanel();
        panel.add(new ChooseFilePanel(imagePath -> {
            originalImage1.load(imagePath);
            filteredImage1.load(imagePath);
            applyFilter(filteredImage1, currentFilter);
        }));

        panel.add(new ChooseFilterTabPanel(filter -> {
            applyFilter(filteredImage1, filter);
            currentFilter = filter;
        }, FILTERS));

        return panel;
    }

    private void loadImage(String fileName) {
        originalImage1 = new Image(fileName, WIDTH / 2 - 28);

        filteredImage1 = new Image(fileName, WIDTH / 2 - 28);
    }

    private void applyFilter(Image image, Filter filter) {
        image.applyFilter(filter);
    }

    public void update() {
        applyFilter(filteredImage1, currentFilter);
    }
}
