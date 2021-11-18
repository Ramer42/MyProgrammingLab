/**
 * This class is to save the currently selected student in table.
 */

package frame;

import model.Student;

public class selectedStudent {
    private static final selectedStudent instance = new selectedStudent();
    private Student stu;

    private selectedStudent() {
        this.stu = null;
    }

    public static selectedStudent getInstance(){
        return instance;
    }

    public void setSelectedStudent(Student stu) {
        this.stu = stu;
    }

    public Student getSelectedStudent() {
        return this.stu;
    }
}
