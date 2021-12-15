package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class Sobel extends AbstractFilter {
    private static final double STEP = 0.25;
    private static final double DELTA_STEP = 5;


    private double scale = 2;
    private double delta = 0;

    public Sobel(MainFrame mainFrame) {
        addSlider(new JSlider(1, (int) (5 / STEP), (int) (scale / STEP)), "Scale", e -> {
            scale = ((JSlider) e.getSource()).getValue() * STEP;
            mainFrame.update();
        }, STEP);
        addSlider(new JSlider(0, (int) (100 / DELTA_STEP), (int) (delta / DELTA_STEP)), "Delta", e -> {
            delta = ((JSlider) e.getSource()).getValue() * DELTA_STEP;
            mainFrame.update();
        }, DELTA_STEP);
    }

    @Override
    public String getName() {
        return "Sobel";
    }

    @Override
    public Mat apply(Mat img) {
        Mat src_gray = new Mat();

        Imgproc.cvtColor(img, src_gray, Imgproc.COLOR_RGB2GRAY);
        Mat grad_x = new Mat(), grad_y = new Mat();
        Mat abs_grad_x = new Mat(), abs_grad_y = new Mat();

        Imgproc.Sobel(src_gray, grad_x, CvType.CV_16S, 1, 0, 3, scale, delta, Core.BORDER_DEFAULT);
        Imgproc.Sobel(src_gray, grad_y, CvType.CV_16S, 0, 1, 3, scale, delta, Core.BORDER_DEFAULT);
        // converting back to CV_8U
        Core.convertScaleAbs(grad_x, abs_grad_x);
        Core.convertScaleAbs(grad_y, abs_grad_y);
        Mat grad = new Mat();

        Core.addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, grad);
        return grad;
    }
}
