package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class MedianBlur extends AbstractFilter {
    private int kernel = 5;

    public MedianBlur(MainFrame mainFrame) {
        addSlider(new JSlider(0, 25, (kernel - 1) / 2), "Kernel size", e -> {
            kernel = 2 * ((JSlider) e.getSource()).getValue() + 1;
            mainFrame.update();
        }, val -> String.valueOf(2 * val + 1));
    }

    @Override
    public String getName() {
        return "Median blur";
    }


    @Override
    public Mat apply(Mat img) {
        var out = new Mat();

        Imgproc.medianBlur(img, out, kernel);
        return out;
    }
}
