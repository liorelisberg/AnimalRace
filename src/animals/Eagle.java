package animals;

import Graphics.CompetitionPanel;
import mobility.Point;

/**
 * Presents Eagle object
 */
public class Eagle extends AirAnimal {

    private final String sound = "Clack-wack-chack";

    /**
     * Creates Eagle object with the given arguments.
     *
     * @param name     A given name of Eagle object.
     * @param speed    A given speed of Eagle object.
     * @param position A given Point object of Eagle's object location in space.
     * @param choice   A given image choice of Eagle object.
     * @param pan      A given CompetitionPanel of Eagle object.
     * @see Point,CompetitionPanel
     */
    public Eagle(String name, double speed, int energy, gen gender, Point position, CompetitionPanel pan, String choice) {
        super(name, speed, energy, gender, position, pan, "eagle", choice);
    }

    /**
     * @return readable info of this Eagle object.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Gets this Eagle's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return this.sound;
    }

}
