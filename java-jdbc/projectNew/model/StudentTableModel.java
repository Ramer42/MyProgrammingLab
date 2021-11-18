/**
 * TableModel to get data from database to show in table.
 */

package model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class StudentTableModel extends AbstractTableModel {
    private static final StudentTableModel instance = new StudentTableModel();
    private String[] columnNames = new String[] { "id", "number", "name", "gpa" };
    private List<Student> students;

    private StudentTableModel() {
        this.students = new ArrayList<>();
    }
    public static StudentTableModel getInstance(){
        return instance;
    }

    public void setModel(List<Student> studentTable) {
        this.students = studentTable;
    }

    public List<Student> getModel() {
        return students;
    }

    public int getRowCount() {
        // TODO Auto-generated method stub
        return students.size();
    }

    public int getColumnCount() {
        // TODO Auto-generated method stub
        return columnNames.length;
    }

    public String getColumnName(int columnIndex) {
        // TODO Auto-generated method stub
        return columnNames[columnIndex];
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Student h = students.get(rowIndex);
        if (0 == columnIndex)
            return h.id;
        if (1 == columnIndex)
            return h.number;
        if (2 == columnIndex)
            return h.name;
        if (3 == columnIndex)
            return h.gpa;
        return null;
    }

}
