package ua.lviv.mel2.ai_coursework.gui;


import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import ua.lviv.mel2.ai_coursework.filters.Filter;

import javax.swing.*;

public class Image extends JLabel {

    private final int width;
    private String fileName;
    private String fileExt;
    private Mat image;
    private Mat originalImage;

    private String filterName;


    public Image(String filePath, int width) {
        super();
        this.width = width;

        load(filePath);
    }

    public void load(String filePath) {
        var loadedImage = Imgcodecs.imread(filePath);
        if (loadedImage.empty()) {
            throw new RuntimeException("Can't to load " + filePath);
        } else {
            System.out.println("Load image " + filePath);
        }

        var lastDot = filePath.lastIndexOf('.');

        fileName = filePath.substring(0, lastDot);
        fileExt = filePath.substring(lastDot);

        var aspect = ((double) loadedImage.height()) / loadedImage.width();

        image = new Mat();
        Imgproc.resize(loadedImage, image, new Size(width, width * aspect));

        originalImage = image;
        setIcon(new ImageIcon(HighGui.toBufferedImage(image)));
    }

    public void applyFilter(Filter filter) {
        filterName = filter.getName();
        image = filter.apply(originalImage);
        setIcon(new ImageIcon(HighGui.toBufferedImage(image)));
        setAutoscrolls(true);
    }

    public void save() {
        var filename = fileName + "-" + filterName + fileExt;
        System.out.println("Save img as " + filename);
        Imgcodecs.imwrite(filename, image);
    }
}
