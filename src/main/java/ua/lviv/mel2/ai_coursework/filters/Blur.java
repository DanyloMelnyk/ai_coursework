package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class Blur extends AbstractFilter {
    private int kernelWidth = 5;
    private int kernelHeight = 5;

    public Blur(MainFrame mainFrame) {
        addSlider(new JSlider(1, 25, kernelWidth), "Kernel width", e -> {
            kernelWidth = ((JSlider) e.getSource()).getValue();
            mainFrame.update();
        });
        addSlider(new JSlider(1, 25, kernelHeight), "Kernel height", e -> {
            kernelHeight = ((JSlider) e.getSource()).getValue();
            mainFrame.update();
        });
    }

    @Override
    public String getName() {
        return "Box filter";
    }


    @Override
    public Mat apply(Mat img) {
        var out = new Mat();

        Imgproc.blur(img, out, new Size(kernelWidth, kernelHeight));
        return out;
    }
}
