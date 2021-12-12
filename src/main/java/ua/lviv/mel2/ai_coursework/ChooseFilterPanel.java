package ua.lviv.mel2.ai_coursework;

import ua.lviv.mel2.ai_coursework.filters.Filter;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.function.Consumer;

public class ChooseFilterPanel extends JToolBar {
    private Filter currentFilter;
    private Consumer<Filter> callback;

    public ChooseFilterPanel(Consumer<Filter> callback, Filter[] filters) {
        super(VERTICAL);
        setFloatable(false);

        this.callback = callback;

        var filterBox = new JComboBox<>(filters);
        filterBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateFilter((Filter) e.getItem());
            }
        });

        add(filterBox);

        updateFilter((Filter) filterBox.getSelectedItem());
    }

    private void updateFilter(Filter newFilter) {
        if (currentFilter != null) {
            remove(getComponentCount() - 1);
        }
        currentFilter = newFilter;

        add(currentFilter.getSettings());
        repaint();
        JComponent parentComponent = (JComponent) SwingUtilities.getAncestorOfClass(JComponent.class, this);

        // Could we find a parent?
        if (parentComponent != null) {
            // Repaint the parent.
            parentComponent.revalidate();
            parentComponent.repaint();
        }

        callback.accept(currentFilter);
    }


}
