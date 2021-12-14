package ua.lviv.mel2.ai_coursework.gui;

import ua.lviv.mel2.ai_coursework.filters.Filter;

import javax.swing.*;
import java.util.Arrays;
import java.util.function.Consumer;

public class ChooseFilterTabPanel extends JTabbedPane {
    private final Consumer<Filter> callback;

    public ChooseFilterTabPanel(Consumer<Filter> callback, Filter[] filters) {
        super(SwingConstants.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

        this.callback = callback;

        Arrays.stream(filters).forEach(i -> {
                    var panel = MainFrame.createVerticalPanel();
                    panel.add(i.getSettings());
                    addTab(i.getName(), panel);
                }
        );

        addChangeListener(e -> {
            var selected = ((JTabbedPane) e.getSource()).getSelectedIndex();
            updateFilter(filters[selected]);
        });

        updateFilter(filters[getSelectedIndex()]);
    }

    private void updateFilter(Filter newFilter) {
        callback.accept(newFilter);
    }


}
