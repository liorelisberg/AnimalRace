package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Dog object
 */
public class Dog extends TerrestrialAnimal {
    private static final String sound = "Woof Woof";

    /**
     * Creates Dog object with the given arguments.
     *
     * @param name     A given name of Dog object.
     * @param speed    A given speed of Dog object.
     * @param position A given Point object of Dog's object location in space.
     * @see gen,Medal,Point
     */
    public Dog(String name, double speed, int energy, gen gender, Point position, CompetitionPanel pan, String choice) {
        super(name, speed, energy, gender, position, pan, "dog", choice);
        super.drawObject(this.pan.getG());
    }

    /**
     * @return readable info of this Dog object.
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Gets this Dog's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return sound;
    }
}
