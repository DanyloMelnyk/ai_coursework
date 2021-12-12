package ua.lviv.mel2.ai_coursework.filters;

import ua.lviv.mel2.ai_coursework.MainFrame;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.util.function.Function;

public abstract class AbstractFilter implements Filter {
    protected JPanel settingsPanel = MainFrame.createVerticalPanel();

    protected void addSlider(JSlider s, String name, ChangeListener listener) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(name));
        s.addChangeListener(listener);
        panel.add(s);

        var label = new JLabel(String.valueOf(s.getValue()));

        s.addChangeListener(e -> label.setText(String.valueOf(((JSlider) e.getSource()).getValue())));
        panel.add(label);
        settingsPanel.add(panel);
    }

    protected void addSlider(JSlider s, String name, ChangeListener listener, double step) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(name));
        s.addChangeListener(listener);
        panel.add(s);

        var label = new JLabel(String.valueOf(s.getValue() * step));

        s.addChangeListener(e -> label.setText(String.valueOf(((JSlider) e.getSource()).getValue() * step)));
        panel.add(label);
        settingsPanel.add(panel);
    }

    protected void addSlider(JSlider s, String name, ChangeListener listener, Function<Integer, String> labelFunc) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder(name));
        s.addChangeListener(listener);
        panel.add(s);

        var label = new JLabel(labelFunc.apply(s.getValue()));

        s.addChangeListener(e ->
                label.setText(labelFunc.apply(((JSlider) e.getSource()).getValue()))
        );
        panel.add(label);
        settingsPanel.add(panel);
    }

    @Override
    public JPanel getSettings() {
        return settingsPanel;
    }

    @Override
    public String toString() {
        return getName();
    }
}
