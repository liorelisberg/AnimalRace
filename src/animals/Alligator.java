package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Represents Alligator animal object
 */
public class Alligator extends TerrestrialAnimal implements IReptile {

    private static final String sound = "Roar";
    private final WaterAnimal waterAnimal;

    /**
     * Creates Alligator object with the given arguments.
     *
     * @param name     A given name of Alligator object.
     * @param speed    A given speed of Alligator object.
     * @param position A given Point object of Alligator's object location in space.
     * @see gen,Medal,Point
     */
    public Alligator(String name, double speed, int energy, gen gender, Point position, CompetitionPanel pan, String choice) {
        super(name, speed, energy, gender, position, pan, "alligator", choice);
        waterAnimal = new WaterAnimal(name, speed, energy, gender, position, pan, "alligator", "alligator2") {
            public String getSound() {
                return sound;
            }
        };
        super.drawObject(super.pan.getG());
    }

    public String getType() {
        return super.getType();
    }

    /**
     * Speeds up a Alligator object, until a maximum amount of speed.
     *
     * @param speed A given speed that will increase this object's current speed.
     * @return boolean value if increasing speed succeeded or not.
     */
    @Override
    public boolean speedUp(int speed) {
        if ((super.getSpeed() + speed < MAX_SPEED) && speed > 0) {
            super.setSpeed(super.getSpeed() + speed);
            return true;
        } else if (speed <= 0)
            return false;
        else
            System.out.println(this.getName() + "'s new speed is MAX SPEED");
        this.setSpeed(MAX_SPEED);
        return true;
    }

    /**
     * Gets this Alligator's object sound.
     *
     * @return this object's sound.
     */
    public String getSound() {
        return sound;
    }

    public WaterAnimal getWaterAnimal() {
        return waterAnimal;
    }

    @Override
    public boolean eat(int energy) {
        if (energy <= 0 || energy >= TerrestrialAnimal.maxEnergy) {
            return false;
        } else {
            super.setCurrentEnergy(energy);
            return true;
        }
    }
}
