package listener;

import frame.Updater;
import frame.selectedStudent;
import model.Student;
import panel.ModifyPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubmitModifyActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        int id = selectedStudent.getInstance().getSelectedStudent().id;
        Student stu = ModifyPanel.getInstance().getModStu();
        stu.id = id;
        Updater.getInstance().updateItem(stu);
        JOptionPane.showMessageDialog(ModifyPanel.getInstance().getPanel(), "Modified successfully");
    }
}
