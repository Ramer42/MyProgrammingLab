/**
 * This class implements the methods such as
 * changing page, calculating page information
 * and update table when a change to data is made.
 */

package frame;

import model.Student;
import model.StudentDAO;
import model.StudentTableModel;

import java.util.ArrayList;
import java.util.List;

public class Updater {
    // set connection to database
    private StudentDAO dao;
    private Updater() {
        dao = new StudentDAO();
    }

    private static final Updater instance = new Updater();

    public static Updater getInstance(){
        return instance;
    }

    private int currPage;
    private int maxPage;
    private int sizeDB;
    private List<Integer> pageEndpoints = new ArrayList<>();

    // update page information when a change to data is made
    public void update() {
        pageEndpoints = new ArrayList<>();
        sizeDB = dao.getTotal();
        maxPage = sizeDB / 6 - 1;
        for (int i = 0; i < maxPage + 1; i++) {
            pageEndpoints.add((i + 1) * 6);
        }
        if (sizeDB % 6 != 0) {
            maxPage++;
            pageEndpoints.add(sizeDB);
        }
        updateComboBox();
        setPage(0);
    }

    // set page of table
    public void setPage(int page) {
        currPage = page;
//        System.out.printf("%d, %d\n", page * 6, pageEndpoints.get(page) - page * 6);
        List<Student> students;
        if (maxPage == -1) {
            students = new ArrayList<>();
        } else {
            students = dao.list(page * 6, pageEndpoints.get(page) - page * 6);
        }
        StudentTableModel.getInstance().setModel(students);
        listSelection();
//        selectedStudent.getInstance().setSelectedStudent(null);
        updateButton();
//        mainFrame.getComboBox().setSelectedIndex(currPage);
        mainFrame.getTable().updateUI();
    }

    // update the status of buttons
    private void updateButton() {
        mainFrame.getbLastPage().setEnabled(true);
        mainFrame.getbNextPage().setEnabled(true);
        mainFrame.getbPrePage().setEnabled(true);
        mainFrame.getbFirstPage().setEnabled(true);
        if (maxPage == 0) {
            mainFrame.getbLastPage().setEnabled(false);
            mainFrame.getbNextPage().setEnabled(false);
            mainFrame.getbPrePage().setEnabled(false);
            mainFrame.getbFirstPage().setEnabled(false);
        } else if (currPage == 0) {
            mainFrame.getbPrePage().setEnabled(false);
            mainFrame.getbFirstPage().setEnabled(false);
        } else if (currPage == maxPage) {
            mainFrame.getbLastPage().setEnabled(false);
            mainFrame.getbNextPage().setEnabled(false);
        }
    }

    private void updateComboBox() {
        for (int i = 0; i < maxPage + 1; i++) {
            mainFrame.getComboBox().addItem(Integer.toString(i + 1));
        }
    }

    // delete a student
    public void delete() {
        dao.delete(selectedStudent.getInstance().getSelectedStudent().id);
        update();
    }

    public void nextPage() {
        setPage(currPage + 1);
    }
    public void prePage() {
        setPage(currPage - 1);
    }
    public void lastPage() {
        setPage(maxPage);
    }

    // According to the behaviour of ListSelectionListener, if change
    // to next page, the selected item will be the one that's in the
    // same row. If in the next page, the item in the row doesn't exist
    // (there is less items in this page), it won't select any item.
    public void listSelection() {
        int row = mainFrame.getTable().getSelectedRow();
        if (row == -1) {
            return;
        }
        if (row > StudentTableModel.getInstance().getModel().size() - 1) {
            selectedStudent.getInstance().setSelectedStudent(null);
        } else {
            Student stu = StudentTableModel.getInstance().getModel().get(row);
            selectedStudent.getInstance().setSelectedStudent(stu);
        }
    }

    // change the values of a existed student
    public void updateItem(Student stu) {
        dao.update(stu);
        setPage(currPage);
    }

    // add a new student
    public void addItem(Student stu) {
        dao.add(stu);
        update();
    }
}
