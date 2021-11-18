import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class testTable {
    public static void main(String[] args) {

        JFrame f = new JFrame("LoL");
        f.setSize(470, 330);
        f.setLocation(200, 200);
        f.setLayout(new BorderLayout());

        final HeroTableModel htm = new HeroTableModel();
        htm.updateInfo();
        htm.setPage(0);

        final JTable t = new JTable(htm);

        JScrollPane sp = new JScrollPane(t);
        sp.setPreferredSize(new Dimension(470, 220));

        // 使用selection监听器来监听table的哪个条目被选中
        Hero selectedHero = new Hero();
        selectedHero.id = -1;
        ListSelectionListener lsl = new ListSelectionListener() {

            // 当选择了某一行的时候触发该事件
            public void valueChanged(ListSelectionEvent e) {
                // 获取哪一行被选中了
                int row = t.getSelectedRow();
                // 根据选中的行，到HeroTableModel中获取对应的对象
                Hero h = htm.heros.get(row);
                // 更新标签内容
                selectedHero.id = h.id;
                selectedHero.name = h.name;
                selectedHero.hp = h.hp;
            }
        };
        t.getSelectionModel().addListSelectionListener(lsl);

        JPanel p1 = new JPanel();
        JButton bDelete = new JButton("Delete");
        JButton bAdd = new JButton("Add");
        JButton bModify = new JButton("Modify");

        p1.add(bDelete);
        p1.add(bAdd);
        p1.add(bModify);

        JPanel p2 = new JPanel();
        JButton bFirstPage = new JButton("First Page");
        JButton bPrePage = new JButton("Previous Page");
        JButton bNextPage = new JButton("Next Page");
        JButton bLastPage = new JButton("Last Page");
        JComboBox cb = new JComboBox();

        p2.add(bFirstPage);
        p2.add(bPrePage);
        p2.add(cb);
        p2.add(bNextPage);
        p2.add(bLastPage);

        class setButton {
            public void setbutton() {
                bLastPage.setEnabled(true);
                bNextPage.setEnabled(true);
                bPrePage.setEnabled(true);
                bFirstPage.setEnabled(true);
                if (0 == htm.maxPage) {
                    bLastPage.setEnabled(false);
                    bNextPage.setEnabled(false);
                    bPrePage.setEnabled(false);
                    bFirstPage.setEnabled(false);
                } else if (htm.currPage == 0) {
                    bPrePage.setEnabled(false);
                    bFirstPage.setEnabled(false);
                } else if (htm.currPage == htm.maxPage) {
                    bLastPage.setEnabled(false);
                    bNextPage.setEnabled(false);
                }
            }
            public void setComboBox() {
                for (int i = 0; i < htm.maxPage + 1; i++) {
                    cb.addItem(i);
                }
                cb.setSelectedItem(htm.currPage);
            }
        }
        setButton setb = new setButton();
        setb.setbutton();
        setb.setComboBox();

        cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                htm.setPage((int)cb.getSelectedItem());
                setb.setbutton();
                t.updateUI();
            }
        });

        bDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedHero.id == -1) {
                    JOptionPane.showMessageDialog(f, "Please select one row before deleting");
                } else {
                    int option = JOptionPane.showConfirmDialog(f, "Are you sure to delete?");
                    if (JOptionPane.OK_OPTION == option) {
                        HeroDAO dao = new HeroDAO();
                        // 通过dao从数据库删除该对象
                        dao.delete(selectedHero.id);

                        // 通过dao更新tablemodel中的数据
                        htm.heros = dao.list();
                        htm.updateInfo();
                        htm.setPage(0);
                        setb.setbutton();
                        setb.setComboBox();
                        t.updateUI();
                    }
                }
            }
        });

        bAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Instantiate jDialog from an external frame
                JDialog d = new JDialog(f);
                // Set modal
                d.setModal(true);

                d.setTitle("Add");
                d.setSize(400, 300);
                d.setLocation(200, 200);
                d.setLayout(new BorderLayout());

                // Add a panel to place the name, input box and submit button
                JPanel p = new JPanel();

                final JLabel lName = new JLabel("Name");
                final JTextField tfName = new JTextField("");
                final JLabel lHp = new JLabel("Hp");
                final JTextField tfHp = new JTextField("");
                JButton bAddinAdd = new JButton("Submit");
                tfName.setPreferredSize(new Dimension(80, 30));
                tfHp.setPreferredSize(new Dimension(80, 30));

                p.add(lName);
                p.add(tfName);
                p.add(lHp);
                p.add(tfHp);
                p.add(bAddinAdd);
                d.add(p);

                // Add a listener to the submit button
                bAddinAdd.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        HeroDAO dao = new HeroDAO();
                        Hero h = new Hero();
                        h.name = tfName.getText();
                        h.hp = Integer.parseInt(tfHp.getText());

                        dao.add(h);
                        htm.heros = dao.list();
                        htm.updateInfo();
                        htm.setPage(0);
                        setb.setbutton();
                        setb.setComboBox();

                        t.updateUI();
                        JOptionPane.showMessageDialog(f, "Submitted successfully");
                    }
                });
                d.setVisible(true);
            }
        });

        bModify.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (selectedHero.id == -1) {
                    JOptionPane.showMessageDialog(f, "Please select one row before modifying");
                } else {
                    // 根据外部窗体实例化JDialog
                    JDialog d = new JDialog(f);
                    // 设置为模态
                    d.setModal(true);

                    d.setTitle("Modify");
                    d.setSize(400, 300);
                    d.setLocation(200, 200);
                    d.setLayout(new BorderLayout());

                    // 增加 一个 panel用于放置名称，血量输入框和增加 按钮
                    JPanel p = new JPanel();

                    final JLabel lName = new JLabel("Name");
                    final JTextField tfName = new JTextField(selectedHero.name);
                    final JLabel lHp = new JLabel("Hp");
                    final JTextField tfHp = new JTextField(Float.toString(selectedHero.hp));
                    JButton bModinMod = new JButton("Submit");
                    tfName.setPreferredSize(new Dimension(80, 30));
                    tfHp.setPreferredSize(new Dimension(80, 30));

                    p.add(lName);
                    p.add(tfName);
                    p.add(lHp);
                    p.add(tfHp);
                    p.add(bModinMod);
                    d.add(p);

                    // 为增加按钮添加监听
                    bModinMod.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            HeroDAO dao = new HeroDAO();

                            // 根据输入框数据创建一个Hero对象
                            Hero h = new Hero();
                            h.id = selectedHero.id;
                            h.name = tfName.getText();
                            h.hp = Integer.parseInt(tfHp.getText());

                            dao.update(h);
                            htm.heros = dao.list();
                            htm.updateInfo();
                            htm.setPage(0);
                            setb.setbutton();
                            setb.setComboBox();
                            t.updateUI();
                            JOptionPane.showMessageDialog(f, "Modified successfully");
                        }
                    });
                    d.setVisible(true);
                }
            }
        });

        bNextPage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                htm.setPage(htm.currPage + 1);
                setb.setbutton();
                cb.setSelectedItem(htm.currPage);
                t.updateUI();
            }
        });

        bPrePage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                htm.setPage(htm.currPage - 1);
                setb.setbutton();
                cb.setSelectedItem(htm.currPage);
                t.updateUI();
            }
        });

        bLastPage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                htm.setPage(htm.maxPage);
                setb.setbutton();
                cb.setSelectedItem(htm.currPage);
                t.updateUI();
            }
        });

        bFirstPage.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                htm.setPage(0);
                setb.setbutton();
                cb.setSelectedItem(htm.currPage);
                t.updateUI();
            }
        });



        f.add(sp, BorderLayout.NORTH);
        f.add(p1, BorderLayout.CENTER);
        f.add(p2, BorderLayout.SOUTH);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true);
    }
}