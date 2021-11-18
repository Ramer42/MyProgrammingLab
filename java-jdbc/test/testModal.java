import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class testModal {
    public static void main(String[] args) {
        JFrame f = new JFrame("Outer Frame");
        f.setSize(800, 600);
        f.setLocation(100, 100);

        // 根据外部窗体实例化JDialog
        JDialog modalDialog = new JDialog(f);
        // 设置为模态
        modalDialog.setModal(true);

        modalDialog.setTitle("Modal Frame");
        modalDialog.setSize(400, 300);
        modalDialog.setLocation(200, 200);
        modalDialog.setLayout(null);
        modalDialog.setResizable(false);

        // Button: Open a modal dialog
        JButton bOpenFrame = new JButton("Open a modal dialog");
        bOpenFrame.setBounds(50, 50, 100, 30);
        bOpenFrame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modalDialog.setVisible(true);
            }
        });
        f.add(bOpenFrame);

        // Button: Unlock size
        JButton bResizable = new JButton("Unlock size");
        bResizable.setBounds(50, 50, 100, 30);
        bResizable.addActionListener(new ActionListener() {
            boolean resizable = false;

            public void actionPerformed(ActionEvent e) {
                if (resizable) {
                    modalDialog.setResizable(false);
                    bResizable.setText("Unlock size");
                    resizable = false;
                } else {
                    modalDialog.setResizable(true);
                    bResizable.setText("Lock size");
                    resizable = true;
                }
            }
        });
        modalDialog.add(bResizable);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true);

    }
}