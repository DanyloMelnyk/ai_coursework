package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

public class PyrDown extends AbstractFilter {
    public PyrDown(MainFrame mainFrame) {

    }

    @Override
    public String getName() {
        return "PyrDown";
    }


    @Override
    public Mat apply(Mat img) {
        var out = new Mat();

        Imgproc.pyrDown(img, out);
        return out;
    }
}
