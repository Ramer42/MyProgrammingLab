package listener;

import panel.AddPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        AddPanel.getInstance().show();
    }
}
