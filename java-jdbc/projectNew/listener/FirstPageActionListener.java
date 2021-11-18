package listener;

import frame.Updater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FirstPageActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Updater.getInstance().setPage(0);
    }
}
