package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class UnsharpFilter extends AbstractFilter {
    private static final double STEP = 0.05;

    int ksize = 3;
    double strength = 1.0;

    public UnsharpFilter(MainFrame mainFrame) {
        addSlider(new JSlider(1, 25, 1), "Kernel size", e -> {
            ksize = 2 * ((JSlider) e.getSource()).getValue() + 1;
            mainFrame.update();
        }, val -> String.valueOf(2 * val + 1));

        addSlider(new JSlider(0, (int) (5 / STEP), (int) (strength / STEP)), "k", e -> {
            strength = ((JSlider) e.getSource()).getValue() * STEP;
            mainFrame.update();
        }, STEP);
    }

    @Override
    public String getName() {
        return "Unsharp";
    }

    @Override
    public Mat apply(Mat img) {
        var median = new Mat();
        Imgproc.medianBlur(img, median, ksize);
        Imgproc.GaussianBlur(img, median, new Size(ksize, ksize), 10);

        var lap = new Mat();
        Imgproc.Laplacian(median, lap, -1);

        var out = new Mat();
        Core.scaleAdd(lap, -strength, img, out);

        return out;
    }
}
