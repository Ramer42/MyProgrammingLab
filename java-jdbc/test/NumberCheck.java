import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberCheck {
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    public static void main(String[] args) {

        JFrame f = new JFrame("Number Check");
        f.setSize(400, 300);
        f.setLocation(200, 200);

        f.setLayout(new FlowLayout());

        JLabel lName = new JLabel("Input:");
        // 输入框
        JTextField tfName = new JTextField("Please enter");
        tfName.setPreferredSize(new Dimension(80, 30));

        JButton b = new JButton("Check");
        b.setBounds(150, 200, 100, 30);

        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isNumeric(tfName.getText())) {
                    JOptionPane.showMessageDialog(f, "The input is a number");
                } else {
                    JOptionPane.showMessageDialog(f, "The input is not a number");
                }
            }
        });

        f.add(lName);
        f.add(tfName);
        f.add(b);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true);
    }
}
