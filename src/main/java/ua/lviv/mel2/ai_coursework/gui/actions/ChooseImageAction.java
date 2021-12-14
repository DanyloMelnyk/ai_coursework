package ua.lviv.mel2.ai_coursework.gui.actions;

import lombok.SneakyThrows;
import ua.lviv.mel2.ai_coursework.gui.ImageFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.function.Consumer;

public class ChooseImageAction extends AbstractAction {
    private final Consumer<String> callback;

    public ChooseImageAction(Consumer<String> callback) {
        super("Choose image");
        this.callback = callback;
    }

    @Override
    @SneakyThrows
    public void actionPerformed(ActionEvent e) {
        var fileChooser = new JFileChooser(new File("."));
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new ImageFileFilter());

        var ret = fileChooser.showDialog(null, "Open image");
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            callback.accept(file.getCanonicalPath());
        }
    }

}
