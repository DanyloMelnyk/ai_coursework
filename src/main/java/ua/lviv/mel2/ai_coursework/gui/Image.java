package ua.lviv.mel2.ai_coursework.gui;

import lombok.SneakyThrows;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import ua.lviv.mel2.ai_coursework.filters.Filter;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Image extends JComponent {
    private Mat originalImage;
    private Mat image;
    private int width;
    private double aspect;

    private String fileName;
    private String fileExt;
    private String filterName;

    @SneakyThrows
    public Image(File imageFile, int width) {
        super();

        this.width = width;

        load(imageFile.getCanonicalPath());
        setAutoscrolls(true); //enable synthetic drag events
    }

    public void load(String filePath) {
        image = Imgcodecs.imread(filePath);
        if (image.empty()) {
            throw new RuntimeException("Can't to load " + filePath);
        } else {
            System.out.println("Load image " + filePath);
        }

        originalImage = image;
        aspect = ((double) image.height()) / image.width();

        var origName = filePath;
        var lastDot = origName.lastIndexOf('.');

        this.fileName = origName.substring(0, lastDot);
        this.fileExt = origName.substring(lastDot);

        setPreferredSize(new Dimension(width, (int) (width * aspect)));

        repaint();
//        if (getParent() != null) {
//            getParent().repaint();
//        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(HighGui.toBufferedImage(image), 0, 0, width, (int) (width * aspect), this);
//        g.
    }

    public void applyFilter(Filter filter) {
        System.out.println("Apply filter: " + filter.getName());

        filterName = filter.getName();
        image = filter.apply(originalImage);
        repaint();
    }

    public void save() {
        var filename = fileName + "-" + filterName + fileExt;
        System.out.println("Save img as " + filename);
        Imgcodecs.imwrite(filename, image);
    }

}
