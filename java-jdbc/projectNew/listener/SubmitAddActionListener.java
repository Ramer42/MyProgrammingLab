package listener;

import frame.Updater;
import model.Student;
import panel.AddPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitAddActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Student stu = AddPanel.getInstance().getAddStu();
        Updater.getInstance().addItem(stu);
        JOptionPane.showMessageDialog(AddPanel.getInstance().getPanel(), "Submitted successfully");
    }
}
