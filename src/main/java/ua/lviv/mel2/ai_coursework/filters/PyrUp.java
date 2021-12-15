package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

public class PyrUp extends AbstractFilter {
    public PyrUp(MainFrame mainFrame) {

    }

    @Override
    public String getName() {
        return "PyrUp";
    }


    @Override
    public Mat apply(Mat img) {
        var out = new Mat();

        Imgproc.pyrUp(img, out);
        return out;
    }
}
