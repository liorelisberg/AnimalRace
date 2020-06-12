package Graphics;

import mobility.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * CompetitionPanel
 */
public class CompetitionPanel extends JPanel {
    private final CompetitionToolbar competitionToolbar;

    private Image backgroundImg;
    Graphics g;

    /**
     * CompetitionPanel constructor.
     */
    public CompetitionPanel() {
        setLayout(new BorderLayout());
        competitionToolbar = new CompetitionToolbar();
        add(competitionToolbar, BorderLayout.PAGE_END);
    }

    /**
     * Paints this CompetitionPanel with the background image.
     *
     * @param g - A given Graphics object.
     */
    public void paintComponent(Graphics g) {
        try {
            backgroundImg = ImageIO.read(getClass().getResource("/competitionBackground.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.g = g;

        super.paintComponent(g);
        g.drawImage(backgroundImg, 5, 0, this);

        CompetitionFrame frame = (CompetitionFrame) SwingUtilities.getWindowAncestor(this);

        if (!frame.getAnimalVector().isEmpty())
            for (int i = 0; i < frame.getAnimalVector().size(); ++i)
                frame.getAnimalVector().get(i).drawObject(g);
    }

    /**
     * Draw the given image on this Panel.
     *
     * @param img - A given Image object.
     * @param p   -A given Point object the set the location of the image.
     */
    public void drawSome(Image img, Point p) {
        g.drawImage(img, p.getX(), p.getY(), this);
    }

    public Graphics getG() {
        return this.g;
    }

    public CompetitionToolbar getCompetitionToolbar() {
        return competitionToolbar;
    }


}

