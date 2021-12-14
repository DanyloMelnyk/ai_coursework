package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class Canny extends AbstractFilter {
    private static final double STEP = 10;

    private double threshold1 = 5;
    private double threshold2 = 10;

    public Canny(MainFrame mainFrame) {
        addSlider(new JSlider(0, 25, (int) (threshold1 / STEP)), "Threshold1", e -> {
            threshold1 = ((JSlider) e.getSource()).getValue() * STEP;
            mainFrame.update();
        }, STEP);

        addSlider(new JSlider(0, 25, (int) (threshold2 / STEP)), "Threshold2", e -> {
            threshold2 = ((JSlider) e.getSource()).getValue() * STEP;
            mainFrame.update();
        }, STEP);
    }

    @Override
    public String getName() {
        return "Canny86";
    }

    @Override
    public Mat apply(Mat img) {
        var out = new Mat();

        Imgproc.Canny(img, out, threshold1, threshold2);
        return out;
    }
}
