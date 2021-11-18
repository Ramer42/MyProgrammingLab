package panel;

import frame.mainFrame;
import frame.selectedStudent;
import listener.SubmitModifyActionListener;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class ModifyPanel {
    private static final ModifyPanel instance = new ModifyPanel();
    private JDialog d = new JDialog(mainFrame.getFrame());
    private final JTextField tfNumber;
    private final JTextField tfName;
    private final JTextField tfGPA;

    public JDialog getPanel() { return d; }

    private ModifyPanel() {
        d.setModal(true);

        d.setTitle("Modify");
        d.setSize(400, 180);
        d.setLocation(200, 200);
        d.setLayout(new BorderLayout());

        JPanel p = new JPanel();

        final JLabel lNumber = new JLabel("Number");
        tfNumber = new JTextField();
        final JLabel lName = new JLabel("Name");
        tfName = new JTextField();
        final JLabel lGPA = new JLabel("GPA");
        tfGPA = new JTextField();
        JButton bSubmit = new JButton("Submit");
        tfNumber.setPreferredSize(new Dimension(80, 30));
        tfName.setPreferredSize(new Dimension(80, 30));
        tfGPA.setPreferredSize(new Dimension(80, 30));

        bSubmit.addActionListener(new SubmitModifyActionListener());

        p.add(lNumber);
        p.add(tfNumber);
        p.add(lName);
        p.add(tfName);
        p.add(lGPA);
        p.add(tfGPA);
        p.add(bSubmit);
        d.add(p);
    }

    public static ModifyPanel getInstance(){
        return instance;
    }
    public void show() {
        Student stu = selectedStudent.getInstance().getSelectedStudent();
        tfNumber.setText(Integer.toString(stu.number));
        tfName.setText(stu.name);
        tfGPA.setText(Float.toString(stu.gpa));
        d.setVisible(true);
    }

    public Student getModStu() {
        Student stu = new Student();
        stu.number = Integer.parseInt(tfNumber.getText());
        stu.name = tfName.getText();
        stu.gpa = Float.parseFloat(tfGPA.getText());
        return stu;
    }
}
