package animals;

import Graphics.CompetitionPanel;
import Olympics.Medal;
import mobility.Point;

/**
 * Represents TerrestrialAnimal object
 */
abstract public class TerrestrialAnimal extends Animal {

    /**
     * Creates TerrestrialAnimal object with the given arguments.
     *
     * @param name     A given name of TerrestrialAnimal object.
     * @param speed    A given speed of TerrestrialAnimal object.
     * @param position A given Point object of TerrestrialAnimal's object location in space.
     * @see gen,Medal,Point
     */
    public TerrestrialAnimal(String name, double speed, int energy, gen gender, Point position, CompetitionPanel pan, String type, String choice) {
        super(name, speed, energy, gender, position, pan, type, choice);
    }

    /**
     * Makes this TerrestrialAnimal dive with a given Integer.
     *
     * @param p A given Point object that is used to move the object to.
     * @return A boolean if this TerrestrialAnimal object was initialized or not.
     * @see Point
     */
    @Override
    public double move(Point p) {
        if (p.getY() != this.getPosition().getY()) {
            System.out.println("TerrestrialAnimals can't fly or dive");
            return this.getTotalDistance();
        }
        return super.move(p);
    }

    @Override
    public String getType() {
        return super.type;
    }

    public String[] getAnimalInfo() {
        return new String[]{getName(), "Terrestrial Animal", getType(), String.valueOf(getSpeed()), String.valueOf(maxEnergy), String.valueOf(getTotalDistance()), String.valueOf(energyPerMeter)};
    }
}
