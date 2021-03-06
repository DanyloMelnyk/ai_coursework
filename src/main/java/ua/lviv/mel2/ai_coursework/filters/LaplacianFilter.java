package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class LaplacianFilter extends AbstractFilter {
    private int kernelSize = 5;

    public LaplacianFilter(MainFrame mainFrame) {
        addSlider(new JSlider(0, 25, 2), "Kernel size", e -> {
            kernelSize = 2 * ((JSlider) e.getSource()).getValue() + 1;
            mainFrame.update();
        }, val -> String.valueOf(2 * val + 1));
    }

    @Override
    public String getName() {
        return "Laplacian";
    }


    @Override
    public Mat apply(Mat img) {
        var out = new Mat();

        Imgproc.Laplacian(img, out, img.depth(), kernelSize);
        return out;
    }
}
