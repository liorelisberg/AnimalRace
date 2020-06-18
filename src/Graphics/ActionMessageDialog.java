package Graphics;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

/**
 * Creates a JDialog object.
 */
public class ActionMessageDialog {
    private int index = 3;

    //just a test message when competition is over
    public static void ShowVictoryDialog(CompetitionFrame frame, String goldName, String silverName, String bronzeName) {
        JOptionPane.showMessageDialog(frame,
                "first place\t" + goldName + "\nsecond place\t" + silverName + "\nfirst place\t" + bronzeName,
                "VICTORY MESSAGE",
                JOptionPane.PLAIN_MESSAGE);
    }

    /**
     * Creates JDialog to get the user input whether or not a new competition should be chosen.
     *
     * @param frame -A given CompetitionFrame on which the JDialog will be created.
     * @return the chosen answer.
     **/
    public static int createClrCompetitionDialog(CompetitionFrame frame) {
        Object[] options = {"NO!", "Yes, please"};
        return JOptionPane.showOptionDialog(frame,
                "Are you sure you want to chose new competition ?",
                "NEW COMPETITION ?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,     //do not use a custom Icon
                options,  //the titles of buttons
                options[0]); //default button title
    }

    /**
     * Creates JDialog to get the chosen animal that should be cleared.
     *
     * @param frame   -A given CompetitionFrame on which the JDialog will be created.
     * @param options - A given list of existing Animal options.
     * @return The chosen Animal that will be cleared.
     */
    public static String createClrAnimalDialog(CompetitionFrame frame, Object[] options) {
        return (String) JOptionPane.showInputDialog(
                frame,
                "Choose animal to delete :", "Clear",
                JOptionPane.PLAIN_MESSAGE, null,
                options, options[0]);
    }

    /**
     * Creates JDialog to get the chosen competition type.
     *
     * @param frame -A given CompetitionFrame on which the JDialog will be created.
     * @return The chosen the chosen competition type.
     */
    public static String chooseCompTypeDialog(CompetitionFrame frame) {
        ImageIcon imageIcon = frame.getImageIcon("/competition.jpg");
        Object[] options = {"Water animals", "Air animals", "Terrestrial animals"};

        return (String) JOptionPane.showInputDialog(
                frame, "Choose competition type", "Competition",
                JOptionPane.PLAIN_MESSAGE, imageIcon, options, "Terrestrial animals");
    }

    public static void createAnimalDialogError(CompetitionFrame frame) {
        //custom title, warning icon
        JOptionPane.showMessageDialog(frame,
                "DONT BE LAZY!\nplease choose the gender.",
                "Animal with no gender ?",
                JOptionPane.WARNING_MESSAGE);
    }

    public void startCompetitionDialog(CompetitionFrame frame) {
        JFrame f = new JFrame();
        f.setSize(new Dimension(150, 150));
        f.setUndecorated(true);
        centreWindow(f);
        JPanel p = new JPanel();
        JLabel lbl = new JLabel();

        new java.util.Timer().schedule(new TimerTask() {
            @Override

            public void run() {
                f.add(p);
                p.add(lbl);
                if (index >= 0) {
                    f.setVisible(true);
                    ImageIcon imageIcon = new ImageIcon(ActionMessageDialog.class.getResource("/start" + index-- + ".png")); // load the image to a imageIcon
                    Image image = imageIcon.getImage(); // transform it

                    Image newImg = null;
                    if(index+1 != 0)
                         newImg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH); // scale it the smooth way
                    else
                         newImg = image.getScaledInstance(220, 220, Image.SCALE_SMOOTH); // scale it the smooth way

                    imageIcon = new ImageIcon(newImg);// transform it back
                    lbl.setIcon(imageIcon);
//                    b.setIcon(imageIcon);
                    p.repaint();
                } else {
                    cancel();
                    f.dispose();
                }
            }
        }, 1000, 1000);
    }

    public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 4);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
}



