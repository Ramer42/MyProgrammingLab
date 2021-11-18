package listener;

import frame.Updater;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextPageActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Updater.getInstance().nextPage();
    }
}
