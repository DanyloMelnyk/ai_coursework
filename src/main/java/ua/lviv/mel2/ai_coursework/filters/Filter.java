package ua.lviv.mel2.ai_coursework.filters;

import org.opencv.core.Mat;

import javax.swing.*;

public interface Filter {
    String getName();

    JPanel getSettings();

    Mat apply(Mat img);
}
