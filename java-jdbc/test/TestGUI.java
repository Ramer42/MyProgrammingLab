import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TestGUI {
    public static final File FILE = new File("./location.txt");

    public static void main(String[] args) {
        // 主窗体
        JFrame jf = new JFrame("LOL");
        // 主窗体设置大小
        jf.setSize(400, 300);
        // 主窗体设置位置
        int[] xy = method2();
        int x, y;
        if (xy != null) {
            x = xy[0];
            y = xy[1];
            jf.setLocation(x, y);
        } else {
            jf.setLocation(200, 200);
        }
        // 主窗体中的组件设置为绝对定位
        jf.setLayout(null);
        // 按钮组件
        JButton jb = new JButton("一键秒对方基地挂");
        // 同时设置组件的大小和位置
        jb.setBounds(50, 50, 280, 30);
        // 把按钮加入到主窗体中
        jf.add(jb);
        // 关闭窗体的时候，退出程序
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        jf.setVisible(true);
        method(jf);
    }

    /**
     * 把窗口位置信息写出到文件中
     * @param jf
     */
    public static void method(JFrame jf){
        Thread thread = new Thread() {
            public void run() {
                while (true) {
                    int x = jf.getX();
                    int y = jf.getY();
                    try (PrintWriter pw = new PrintWriter(FILE);) {
                        pw.print(x + "@" + y);
                        pw.flush();
                        Thread.sleep(100);
                    } catch (IOException | InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        thread.start();
    }

    /**
     * 读入文件中的位置信息
     * @return
     */
    public static int[] method2() {
        int[] is = null;
        if (!FILE.exists()) {
            FILE.getParentFile().mkdirs();
            try {
                FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (FileReader fr = new FileReader(FILE); BufferedReader br = new BufferedReader(fr);) {
            String str = br.readLine();
            if (str == null) {
                return null;
            }
            String[] ss = str.split("@");
            int[] is2 = new int[2];
            is2[0] = Integer.parseInt(ss[0]);
            is2[1] = Integer.parseInt(ss[1]);
            is = is2;
        } catch (IOException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return is;
    }
}