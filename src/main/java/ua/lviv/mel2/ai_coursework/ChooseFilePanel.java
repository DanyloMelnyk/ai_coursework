package ua.lviv.mel2.ai_coursework;

import javax.swing.*;

public class ChooseFilePanel extends JToolBar {
    public ChooseFilePanel() {
        setFloatable(false);

        add(new JLabel("Image:"));

        var jTextField = new JTextField(36);
        add(jTextField);

        add(new JButton(new ChooseImageAction(jTextField::setText)));
        add(Box.createGlue());

    }
}
