package ua.lviv.mel2.ai_coursework.gui;

import ua.lviv.mel2.ai_coursework.gui.actions.ChooseImageAction;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ChooseFilePanel extends JToolBar {
    public ChooseFilePanel(Consumer<String> callback) {
        setFloatable(false);

        add(new JLabel("Image:"));

        var jTextField = new JTextField(36);
        jTextField.setMaximumSize(new Dimension(jTextField.getMaximumSize().width, jTextField.getPreferredSize().height));
        add(jTextField);

        add(new JButton(new ChooseImageAction(imagePath -> {
            jTextField.setText(imagePath);
            callback.accept(imagePath);
        })));
        add(Box.createGlue());
    }
}
