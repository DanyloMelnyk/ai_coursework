package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class Erosion extends AbstractFilter {
    private int kernelSize = 5;

    public Erosion(MainFrame mainFrame) {
        addSlider(new JSlider(0, 9, 2), "Kernel size", e -> {
            kernelSize = 2 * ((JSlider) e.getSource()).getValue() + 1;
            mainFrame.update();
        }, val -> String.valueOf(2 * val + 1));
    }


    @Override
    public String getName() {
        return "Erosion";
    }

    @Override
    public Mat apply(Mat img) {
        Mat element = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_ELLIPSE, new Size(2 * kernelSize + 1, 2 * kernelSize + 1),
                new Point(kernelSize, kernelSize));
        Mat out = new Mat();
        Imgproc.erode(img, out, element);
        return out;
    }
}
