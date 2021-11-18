package listener;

import frame.mainFrame;
import frame.selectedStudent;
import panel.ModifyPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectedStudent.getInstance().getSelectedStudent() == null) {
            JOptionPane.showMessageDialog(mainFrame.getFrame(), "Please select one row before modifying");
        } else {
            ModifyPanel.getInstance().show();
        }
    }
}
