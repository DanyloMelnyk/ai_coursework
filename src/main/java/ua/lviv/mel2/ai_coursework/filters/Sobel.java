package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

public class Sobel extends AbstractFilter {
    public Sobel(MainFrame mainFrame) {

    }

    @Override
    public String getName() {
        return "Sobel";
    }

    @Override
    public Mat apply(Mat img) {
// https://docs.opencv.org/3.4/d2/d2c/tutorial_sobel_derivatives.html
        Mat src_gray = new Mat();
        int ddepth = CvType.CV_16S;
        int scale = 2;
        int delta = 3;

        // Remove noise by blurring with a Gaussian filter ( kernel size = 3 )
//        Imgproc.GaussianBlur(img, img, new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
        // Convert the image to grayscale
        Imgproc.cvtColor(img, src_gray, Imgproc.COLOR_RGB2GRAY);
        Mat grad_x = new Mat(), grad_y = new Mat();
        Mat abs_grad_x = new Mat(), abs_grad_y = new Mat();
        //Imgproc.Scharr( src_gray, grad_x, ddepth, 1, 0, scale, delta, Core.BORDER_DEFAULT );
        Imgproc.Sobel(src_gray, grad_x, ddepth, 1, 0, 3, scale, delta, Core.BORDER_DEFAULT);
        //Imgproc.Scharr( src_gray, grad_y, ddepth, 0, 1, scale, delta, Core.BORDER_DEFAULT );
        Imgproc.Sobel(src_gray, grad_y, ddepth, 0, 1, 3, scale, delta, Core.BORDER_DEFAULT);
        // converting back to CV_8U
        Core.convertScaleAbs(grad_x, abs_grad_x);
        Core.convertScaleAbs(grad_y, abs_grad_y);
        Mat grad = new Mat();

        Core.addWeighted(abs_grad_x, 0.5, abs_grad_y, 0.5, 0, grad);
        return grad;
    }
}
