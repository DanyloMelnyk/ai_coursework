package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.gui.MainFrame;

import javax.swing.*;

public class SqrBox extends AbstractFilter {
    private int kernelWidth = 1;
    private int kernelHeight = 1;
    private int dDepth = 10;

    public SqrBox(MainFrame mainFrame) {
        addSlider(new JSlider(1, 25), "Kernel width", e -> {
            kernelWidth = ((JSlider) e.getSource()).getValue();
            mainFrame.update();
        });
        addSlider(new JSlider(1, 25), "Kernel height", e -> {
            kernelHeight = ((JSlider) e.getSource()).getValue();
            mainFrame.update();
        });
        addSlider(new JSlider(1, 25), "output image depth", e -> {
            dDepth = ((JSlider) e.getSource()).getValue();
            mainFrame.update();
        });
    }

    @Override
    public String getName() {
        return "Sqr box";
    }


    @Override
    public Mat apply(Mat img) { // FIXME
        var out = new Mat();

        Imgproc.sqrBoxFilter(img, out, -1, new Size(kernelWidth, kernelHeight));

        var newM = new Mat();
        Imgproc.cvtColor(out, newM, Imgproc.COLOR_RGB2BGR);
        System.out.println(img);
        System.out.println(newM);

        return newM;
    }
}
