/**
 * Add panel, shows up when click Add button.
 */

package panel;

import frame.mainFrame;
import listener.SubmitAddActionListener;
import model.Student;

import javax.swing.*;
import java.awt.*;

public class AddPanel {
    private static final AddPanel instance = new AddPanel();
    public static AddPanel getInstance(){
        return instance;
    }

    // Instantiate jDialog from the main frame
    private JDialog d = new JDialog(mainFrame.getFrame());
    private final JTextField tfNumber;
    private final JTextField tfName;
    private final JTextField tfGPA;

    public JDialog getPanel() { return d; }

    private AddPanel() {
        // Set modal
        d.setModal(true);

        d.setTitle("Add");
        d.setSize(400, 180);
        d.setLocation(200, 200);
        d.setLayout(new BorderLayout());

        JPanel p = new JPanel();

        // Add a panel to place the name, input box and submit button
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

        // Add a listener to the submit button
        bSubmit.addActionListener(new SubmitAddActionListener());

        p.add(lNumber);
        p.add(tfNumber);
        p.add(lName);
        p.add(tfName);
        p.add(lGPA);
        p.add(tfGPA);
        p.add(bSubmit);
        d.add(p);
    }

    // the old panel shows up everytime you click
    // the Add button, instead of creating a
    // new instance
    public void show() {
        tfNumber.setText("");
        tfName.setText("");
        tfGPA.setText("");
        d.setVisible(true);
    }

    // get values from JTextField
    public Student getAddStu() {
        Student stu = new Student();
        stu.number = Integer.parseInt(tfNumber.getText());
        stu.name = tfName.getText();
        stu.gpa = Float.parseFloat(tfGPA.getText());
        return stu;
    }
}

