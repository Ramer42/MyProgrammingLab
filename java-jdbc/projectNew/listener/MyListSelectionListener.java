package listener;

import frame.*;
import model.Student;
import model.StudentTableModel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyListSelectionListener implements ListSelectionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        // 获取哪一行被选中了
        int row = mainFrame.getTable().getSelectedRow();
        // 根据选中的行，到StudentTableModel中获取对应的对象
        Student stu = StudentTableModel.getInstance().getModel().get(row);
        // 更新标签内容
        selectedStudent.getInstance().setSelectedStudent(stu);
    }
}
