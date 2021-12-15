package ua.lviv.mel2.ai_coursework.gui.actions;

import ua.lviv.mel2.ai_coursework.gui.Image;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class SaveImageAction extends AbstractAction {
    private final Image image;

    public SaveImageAction(Image image) {
        super("Save image");
        this.image = image;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        image.save();
    }
}
