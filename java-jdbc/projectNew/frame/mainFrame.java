/**
 * The main frame class.
 */

package frame;

import listener.MyListSelectionListener;
import listener.*;
import model.StudentTableModel;

import javax.swing.*;
import java.awt.*;

public class mainFrame {
    private static JFrame f;
    private static JTable t;
    private static JButton bDelete;
    private static JButton bAdd;
    private static JButton bModify;
    private static JButton bFirstPage;
    private static JButton bPrePage;
    private static JButton bNextPage;
    private static JButton bLastPage;
    private static JComboBox cb;

    // public methods to give access of components to listeners
    public static JFrame getFrame() { return f; }
    public static JTable getTable() { return t; }
    public static JButton getbDelete() { return bDelete; }
    public static JButton getbAdd() { return bAdd; }
    public static JButton getbModify() { return bModify; }
    public static JButton getbFirstPage() { return bFirstPage; }
    public static JButton getbPrePage() { return bPrePage; }
    public static JButton getbNextPage() { return bNextPage; }
    public static JButton getbLastPage() { return bLastPage; }
    public static JComboBox getComboBox() { return cb; }


    public static void main(String[] args) {
        // main Frame
        f = new JFrame("Student Management");
        f.setSize(470, 240);
        f.setLocation(200, 200);
        f.setLayout(new BorderLayout());

        // data table
        t = new JTable(StudentTableModel.getInstance());
        t.getSelectionModel().addListSelectionListener(new MyListSelectionListener());

        // A panel with Scroll, in this case not necessary
        JScrollPane sp = new JScrollPane(t);
        sp.setPreferredSize(new Dimension(470, 130));

        // Delete, Add and Modify buttons
        JPanel p1 = new JPanel();
        bDelete = new JButton("Delete");
        bAdd = new JButton("Add");
        bModify = new JButton("Modify");

        // Add listeners to buttons
        bDelete.addActionListener(new DeleteActionListener());
        bModify.addActionListener(new ModifyActionListener());
        bAdd.addActionListener(new AddActionListener());

        p1.add(bDelete);
        p1.add(bAdd);
        p1.add(bModify);

        // page buttons
        JPanel p2 = new JPanel();
        bFirstPage = new JButton("First Page");
        bPrePage = new JButton("Previous Page");
        bNextPage = new JButton("Next Page");
        bLastPage = new JButton("Last Page");
        cb = new JComboBox();

        bFirstPage.addActionListener(new FirstPageActionListener());
        bPrePage.addActionListener(new PrePageActionListener());
        cb.addItemListener(new ComboBoxItemListener());
        bNextPage.addActionListener(new NextPageActionListener());
        bLastPage.addActionListener(new LastPageActionListener());

        p2.add(bFirstPage);
        p2.add(bPrePage);
        p2.add(cb);
        p2.add(bNextPage);
        p2.add(bLastPage);

        // set layout
        f.add(sp, BorderLayout.NORTH);
        f.add(p1, BorderLayout.CENTER);
        f.add(p2, BorderLayout.SOUTH);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Updater.getInstance().update();
        f.setVisible(true);
    }
}
