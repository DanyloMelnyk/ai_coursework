package ua.lviv.mel2.ai_coursework.gui;

import lombok.SneakyThrows;
import org.opencv.imgcodecs.Imgcodecs;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class ImageFileFilter extends FileFilter {
    @Override
    @SneakyThrows
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        } else {
            var name = f.getCanonicalPath();
            return Imgcodecs.haveImageReader(name);
        }
    }

    @Override
    public String getDescription() {
        return "Images";
    }
}
