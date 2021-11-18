package listener;

import frame.Updater;
import frame.mainFrame;
import frame.selectedStudent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (selectedStudent.getInstance().getSelectedStudent() == null) {
            JOptionPane.showMessageDialog(mainFrame.getFrame(), "Please select one row before deleting");
        } else {
            int option = JOptionPane.showConfirmDialog(mainFrame.getFrame(), "Are you sure to delete?");
            if (JOptionPane.OK_OPTION == option) {
                Updater.getInstance().delete();
            }
        }
    }
}
