package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class GaussianBlur extends AbstractFilter {
    private static final double STEP = 5;
    private int kernelWidth = 5;
    private int kernelHeight = 5;
    private double sigma = 10;

    public GaussianBlur(MainFrame mainFrame) {
        addSlider(new JSlider(1, 25, 2), "Kernel width", e -> {
            kernelWidth = 2 * ((JSlider) e.getSource()).getValue() + 1;
            mainFrame.update();
        }, val -> String.valueOf(2 * val + 1));

        addSlider(new JSlider(1, 25, 2), "Kernel height", e -> {
            kernelHeight = 2 * ((JSlider) e.getSource()).getValue() + 1;
            mainFrame.update();
        }, val -> String.valueOf(2 * val + 1));

        addSlider(new JSlider(0, (int) (255 / STEP), (int) (sigma / STEP)), "Sigma", e -> {
            sigma = ((JSlider) e.getSource()).getValue() * STEP;
            mainFrame.update();
        }, STEP);
    }

    @Override
    public String getName() {
        return "Gaussian blur";
    }

    @Override
    public Mat apply(Mat img) {
        var out = new Mat();
        Imgproc.GaussianBlur(img, out, new Size(kernelWidth, kernelHeight), sigma);
        return out;
    }
}
