package ua.lviv.mel2.ai_coursework;

import javax.swing.*;
import java.util.function.Consumer;

public class ChooseFilePanel extends JToolBar {
    public ChooseFilePanel(Consumer<String> callback) {
        setFloatable(false);

        add(new JLabel("Image:"));

        var jTextField = new JTextField(36);
        add(jTextField);

        add(new JButton(new ChooseImageAction(imagePath -> {
            jTextField.setText(imagePath);
            callback.accept(imagePath);
        })));
        add(Box.createGlue());
    }
}
