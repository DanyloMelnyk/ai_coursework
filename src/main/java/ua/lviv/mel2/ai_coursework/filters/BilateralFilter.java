package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class BilateralFilter extends AbstractFilter {
    private static final double STEP = 0.5;

    private int d = 2;
    private double sigmaColor = 5;
    private double sigmaSpace = 5;

    public BilateralFilter(MainFrame mainFrame) {
        addSlider(new JSlider(1, 10, d), "Diameter", e -> {
            d = ((JSlider) e.getSource()).getValue();
            mainFrame.update();
        });

        addSlider(new JSlider(0, 256, (int) (sigmaColor / STEP)), "SigmaColor", e -> {
            sigmaColor = ((JSlider) e.getSource()).getValue() * STEP;
            mainFrame.update();
        }, STEP);

        addSlider(new JSlider(0, 256, (int) (sigmaSpace / STEP)), "SigmaSpace", e -> {
            sigmaSpace = ((JSlider) e.getSource()).getValue() * STEP;
            mainFrame.update();
        }, STEP);
    }

    @Override
    public String getName() {
        return "Bilateral";
    }


    @Override
    public Mat apply(Mat img) {
        var out = new Mat();

        Imgproc.bilateralFilter(img, out, d, sigmaColor, sigmaSpace);
        return out;
    }
}
