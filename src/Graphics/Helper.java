package Graphics;// Java program to demonstrate
//schedule method calls of Timer class

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

class Helper extends TimerTask {
    private int index;
    private CompetitionFrame frame;

    public Helper(CompetitionFrame frame, int index) {
        this.index = index;
        this.frame = frame;
    }

    public void run() {
        System.out.println("Timer ran " + --index);

    }
}

//public class Test
//{
//    public static void main(String[] args)
//    {
//
//        Timer timer = new Timer();
//        TimerTask task = new Helper();
//
//        timer.schedule(task, 2000, 5000);
//
//    }
//}
