package animals;

import javax.swing.*;

import static Tournaments.RegularTournament.stop;
import static Tournaments.CourierTournament.*;

/**
 * The AnimalThread class represents a thread that start the tournament for all animals.
 * in the year that tournament was held
 * @author Dudi Biton - 314963141
 */
public class AnimalThread implements Runnable {
    private Animal participant;
    private double neededDistance;
    private Boolean startFlag;
    private Boolean finishFlag;
    public static long SleepTime = (long) (Math.random() * 1000);
    static Boolean is_winner = false;
    int type_tour_flag;
    private int[] i_arr = {0, 3};

    public AnimalThread(Animal participant) {
        this.participant = participant;
        this.startFlag = false;
        this.finishFlag = false;
        this.neededDistance = 5;
    }


    public AnimalThread(Boolean startFlag, Boolean finishFlag, double neededDistance) {
        this.neededDistance = neededDistance;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
    }

    public AnimalThread(Boolean startFlag, Boolean finishFlag, Animal participant, int type_tour_flag) {
        this.neededDistance = 5;
        this.startFlag = startFlag;
        this.finishFlag = finishFlag;
        this.participant = participant;
        this.type_tour_flag = type_tour_flag;
    }

    public Animal GetParticipant() {
        return this.participant;
    }

    public void run() {}
//    /**
//     * When an object implementing interface <code>Runnable</code> is used
//     * to create a thread, starting the thread causes the object's
//     * <code>run</code> method to be called in that separately executing
//     * thread.
//     * <p>
//     * The general contract of the method <code>run</code> is that it may
//     * take any action whatsoever.
//     *
//     * @see Thread#run()
//     */
//    @Override
//    public void run() {
//        if (this.type_tour_flag == 1) { // regular
//            if (!stop) {
//                synchronized (this.participant) {
//                    synchronized (start_competition_flag) {
//                        if (start_competition_flag == 0) {
//                            try {
//                                JOptionPane.showMessageDialog(null, "The competition will start in 10 seconds",
//                                        "Message", JOptionPane.INFORMATION_MESSAGE);
//                                Thread.sleep(10000);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                            start_competition_flag = 1;
//                        }
//                    }
//
//                    while (!is_winner) {
//                        try {
//                            Thread.sleep(SleepTime);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//
//                        if (is_winner) {
//                            break;
//                        }
//                        synchronized (stop) {
//                            if (stop) {
//                                //stop = false;
//                                break;
//                            }
//                        }
//
//                        this.participant.eat(10);
//
//                        if (this.participant.getLocation().getX() >= this.participant.getMaxDistance()) {
//                            if (is_winner) {
//                                break;
//                            }
//                            is_winner = true;
//                            JOptionPane.showMessageDialog(null, "Winner: " + this.participant.getName(),
//                                    "Message", JOptionPane.INFORMATION_MESSAGE);
//                            System.exit(0);
//                        }
//                    }
//                }
//            }
//        }
//
//        if (this.type_tour_flag == 0) // courier
//        {
//            synchronized (this) {
//                if (animal_courier_arr.size() < 6) {
//                    try {
//                        wait();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                notifyAll();
//                while (true) {
//                    animal_courier_arr.get(i_arr[0]).eat(10);
//                    animal_courier_arr.get(i_arr[1]).eat(10);
//                    try {
//                        Thread.sleep(SleepTime);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    if (i_arr[0] != 2) {
//                        if (animal_courier_arr.get(i_arr[0]).getLocation().getX() >= animal_courier_arr.get(i_arr[0] + 1).getLocation().getX() - 65) {
//                            ++i_arr[0];
//                        }
//                    }
//
//                    if (i_arr[1] <= 4) {
//                        if (animal_courier_arr.get(i_arr[1]).getLocation().getX() >= animal_courier_arr.get(i_arr[1] + 1).getLocation().getX() - 65) {
//                            ++i_arr[1];
//                        } else if (i_arr[1] == 5) {
//                            ++i_arr[1];
//                        }
//                    }
//
//                    if (animal_courier_arr.get(i_arr[0]).getLocation().getX() >= animal_courier_arr.get(i_arr[0]).getMaxDistance()) {
//                        JOptionPane.showMessageDialog(null, "Winners: Squad 1",
//                                "Message", JOptionPane.INFORMATION_MESSAGE);
//                        System.exit(0);
//                    }
//
//                    if (animal_courier_arr.get(i_arr[1]).getLocation().getX() >= animal_courier_arr.get(i_arr[1]).getMaxDistance()) {
//                        JOptionPane.showMessageDialog(null, "Winners: Squad 2",
//                                "Message", JOptionPane.INFORMATION_MESSAGE);
//                        System.exit(0);
//                    }
//                }
//            }
//        }
//    }
}
