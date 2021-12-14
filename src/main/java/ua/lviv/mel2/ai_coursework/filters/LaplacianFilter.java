package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

public class LaplacianFilter extends AbstractFilter {

    public LaplacianFilter(MainFrame mainFrame) {

    }

    @Override
    public String getName() {
        return "Laplacian";
    }


    @Override
    public Mat apply(Mat img) {
        var out = new Mat();

        Imgproc.Laplacian(img, out, img.depth());
        return out;
    }
}
