package ua.lviv.mel2.ai_coursework;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.function.Consumer;

public class ChooseFilterPanel extends JToolBar {
    public ChooseFilterPanel(Consumer<String> callback, String[] filters) {
        setFloatable(false);

        var filterBox = new JComboBox<>(filters);
        filterBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                callback.accept((String) e.getItem());
            }
        });
        callback.accept((String) filterBox.getSelectedItem());

        add(filterBox);
//        setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));

//        add(new JLabel("Image:"));
//
//        var jTextField = new JTextField(36);
//        add(jTextField);
//
//        add(new JButton(new ChooseImageAction(jTextField::setText)));
//        add(Box.createGlue());

    }

}
