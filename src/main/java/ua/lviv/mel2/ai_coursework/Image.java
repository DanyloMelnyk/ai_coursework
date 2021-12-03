package ua.lviv.mel2.ai_coursework;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Image extends JComponent {
    private final Mat originalImage;
    private Mat image;
    private double aspect;

    public Image(File imageFile, int width) {
        super();

        image = Imgcodecs.imread(imageFile.getName());
        originalImage = image;

        aspect = ((double) image.height()) / image.width();

        resizeImage(new Size(width, (int) (width * aspect)));
        setPreferredSize(new Dimension(width, (int) (width * aspect)));

//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                var size = e.getComponent().getSize();
//                resizeImage(new Size(size.width, size.height));
//            }
//        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(HighGui.toBufferedImage(image), 0, 0, this);
    }

    public void resizeImage(Size newSize) {
        System.out.println("New size: " + newSize);
        newSize.height = (int) (aspect * newSize.width);

        setPreferredSize(new Dimension((int) newSize.width, (int) newSize.height));

        var out = new Mat((int) newSize.height, (int) newSize.width, CvType.CV_8U);

        Imgproc.resize(originalImage, out, newSize);

        image = out;
    }

    public void gauss() {
        var out = new Mat(originalImage.size(), CvType.CV_8U);

        Imgproc.GaussianBlur(originalImage, out, new Size(11, 11), 2);
        image = out;
        repaint();
    }

    public void bilateral() {
        var out = new Mat(originalImage.size(), CvType.CV_8U);

        Imgproc.bilateralFilter(originalImage, out, 11, 1.2, 3.4);
        image = out;
        repaint();

    }

    public void blur() {
        var out = new Mat(originalImage.size(), CvType.CV_8U);

        Imgproc.blur(originalImage, out, new Size(10, 10));
        image = out;
        repaint();

    }

    public void medianBlur() {
        var out = new Mat(originalImage.size(), CvType.CV_8U);

        Imgproc.medianBlur(originalImage, out, 11);
        image = out;
        repaint();

    }

    public void sqrBox() {
        var out = new Mat(originalImage.size(), CvType.CV_8U);

        Imgproc.sqrBoxFilter(originalImage, out, 10, new Size(11, 10));
        image = out;
        repaint();

    }

    public void sobel() {
        // https://docs.opencv.org/3.4/d2/d2c/tutorial_sobel_derivatives.html
        Mat src_gray = new Mat();
        int ddepth = CvType.CV_16S;
        int scale = 2;
        int delta = 3;

        var src = originalImage;
        // Remove noise by blurring with a Gaussian filter ( kernel size = 3 )
        Imgproc.GaussianBlur(src, src, new Size(3, 3), 0, 0, Core.BORDER_DEFAULT);
        // Convert the image to grayscale
        Imgproc.cvtColor(src, src_gray, Imgproc.COLOR_RGB2GRAY);
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

        image = grad;

        repaint();
    }
}
