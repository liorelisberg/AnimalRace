package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Presents  Dolphin object
 */
public class Dolphin extends WaterAnimal {
    private waterType WaterType;
    private static final String sound = "Click-click";

    /**
     * Creates Dolphin object with the given arguments.
     *
     * @param name     A given name of Dolphin object.
     * @param speed    A given speed of Dolphin object.
     * @param position A given Point object of Dolphin's object location in space.
     * @see Medal,gen,Point
     */
    public Dolphin(String name,double speed, Point position, CompetitionPanel pan, String choice) {
        super(name, speed,position,pan,"dolphin",choice);
        this.WaterType = waterType.Sea;
    }
    /**
     * Sets this Dolphin's WaterType.
     *
     * @param type A given waterType enum value that is used to initialize this WaterType.
     * @return A boolean if this WaterType enum was initialized or not.
     * @see waterType
     */
    protected boolean setWaterType(waterType type) {
        this.WaterType = type;
        return true;
    }

    /**
     * Gets this Dolphin object's WaterType enum.
     *
     * @return this object's WaterType enum using toString() method.
     * @see waterType
     */
    public String getWaterType() {
        return this.WaterType.toString();
    }

    /**
     * @return readable info of this Dolphin object.
     */
    @Override
    public String toString() {
        return super.toString() + "\nWaterType : " + this.WaterType.toString();
    }

    /**
     * Gets this Dolphin's object sound.
     *
     * @return this object's sound.
     */
    @Override
    public String getSound() {
        return sound;
    }

}
