package listener;

import frame.Updater;
import frame.mainFrame;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ComboBoxItemListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        Updater.getInstance().setPage(Integer.parseInt((String)mainFrame.getComboBox().getSelectedItem()) - 1);
    }
}
