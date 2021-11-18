import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class testProgressBar {
    public static void main(String[] args) {

        JFrame f = new JFrame("LoL");
        f.setSize(400, 300);
        f.setLocation(200, 200);

        f.setLayout(new FlowLayout());

        JProgressBar pb = new JProgressBar();

        //进度条最大100
        pb.setMaximum(100);
        //当前进度是0
        pb.setValue(0);
        //显示当前进度
        pb.setStringPainted(true);

        f.add(pb);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setVisible(true);

        int progress = 0;
        int sleepTime = 100;
        while (progress < 100) {
            try {
                Thread.sleep(sleepTime);
                pb.setValue(progress);
                sleepTime += 100;
                progress++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}