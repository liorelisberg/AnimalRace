package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents Cat object
 */
public class Cat extends TerrestrialAnimal {
    private final String sound = "Meow";

    /**
     * Creates Cat object with the given arguments.
     *
     * @param name     A given name of Cat object.
     * @param speed    A given speed of Cat object.
     * @param position A given Point object of Cat's object location in space.
     * @see Medal,Point,gen
     */
    public Cat(String name, double speed, int energy, gen gender, Point position, CompetitionPanel pan, String choice) {
        super(name, speed, energy, gender, position, pan, "cat", choice);
    }

    /**
     * Gets this Cat's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return sound;
    }
}

