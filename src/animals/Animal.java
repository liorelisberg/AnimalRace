package animals;

import Graphics.CompetitionPanel;
import Graphics.IDrawable;
import Olympics.Medal;
import mobility.ILocatable;
import mobility.Mobile;
import mobility.Point;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Represents animal object
 */
public abstract class Animal extends Mobile implements IDrawable, IAnimal, Cloneable, ILocatable {
    private String name;
    private gen gender;
    private double speed;
    private Medal[] medals;
    protected String type;

    protected static final int size = 65;
    protected static final int maxEnergy = 100;

    protected int currentEnergy;
    protected int energyPerMeter;
    protected Orientation orientation;
    protected BufferedImage eastImg, westImg, southImg, northImg;
    protected CompetitionPanel pan;

    /**
     * Creates Animal object with the given arguments.
     *
     * @param name      A given name of Animal object.
     * @param speed     A given speed of Animal object.
     * @param position  A given Point object of Animal's object location in space.
     * @param imgChoice A given image choice of Animal object.
     * @param pan       A given CompetitionPanel of Animal object.
     * @param type      A given animal type of Animal object.
     * @see gen,Medal,Point
     */
    public Animal(String name, double speed, int energyPerMeter, gen gender, Point position, CompetitionPanel pan, String type, String imgChoice) {
        super(position);

        this.setName(name);
        this.setSpeed(speed);
        this.setEnergyPerMeter(energyPerMeter);
        this.setGender(gender);
        this.setType(type);
        this.setPan(pan);

        this.setMedals(null);
        this.setOrientation(Orientation.E);

        loadImages(imgChoice);
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPan(CompetitionPanel pan) {
        this.pan = pan;
    }

    abstract public String getSound();

    public String getName() {
        return this.name;
    }

    public gen getGender() {
        return this.gender;
    }

    public double getSpeed() {
        return this.speed;
    }

    public Medal[] getMedals() {
        return medals;
    }

    public abstract String getType();

    protected void setName(String name) {
        if (name != null) {
            this.name = name;
            return;
        }
        this.name = "- Empty name - ";
    }

    protected void setGender(gen gender) {
        this.gender = gender;
    }

    public void setEnergyPerMeter(int energyPerMeter) {
        this.energyPerMeter = energyPerMeter;
    }

    protected void setSpeed(double speed) {
        if (speed > 0) {
            this.speed = speed;
            return;
        }
        this.speed = 0;
    }

    public abstract String[] getAnimalInfo();

    protected void setMedals(Medal[] medals) {
        if (medals != null) {
            this.medals = medals.clone();
            return;
        }
        this.medals = null;
    }

    /**
     * Returns a readable String of this array od medals.
     *
     * @return this array of medals' objects as readable String.
     */
    public String MedalsToString() {
        StringBuilder info = new StringBuilder();
        if (this.medals == null)
            return "None";

        for (int i = 0; i < medals.length; ++i) {
            if (medals.length > 1) {
                String headLine = "\n\t MEDAL NO " + (i + 1) + " -";
                info.append(headLine);
            }
            info.append(medals[i].toString());
        }
        return info.toString();
    }

    public String toString() {
        return "Name : " + this.getName() + "\nGender : " + this.getGender() + "\nWeight : "
                + "\nSpeed : " + this.getSpeed() + "\nMedals : " + this.MedalsToString() + "\nLocation : "
                + super.getPosition();
    }

    /**
     * Makes this Animal object's sound.
     */
    public void makeSound() {
        System.out.println(this.getName() + " said " + getSound());
    } //todo -use when animal wins

    public void setCurrentEnergy(int Energy) {
        this.currentEnergy = Energy;
    }

    @Override
    public void loadImages(String nm) {
        try {
            this.eastImg = ImageIO.read(getClass().getResource("/" + nm + "E.png"));
            this.westImg = ImageIO.read(getClass().getResource("/" + nm + "W.png"));
            this.northImg = ImageIO.read(getClass().getResource("/" + nm + "N.png"));
            this.southImg = ImageIO.read(getClass().getResource("/" + nm + "S.png"));


        } catch (IOException e) {
            try {
                System.out.println("reach to catch"); //todo delete line
                this.eastImg = ImageIO.read(new File(PICTURE_PATH + nm + "E.png"));
                this.westImg = ImageIO.read(new File(PICTURE_PATH + nm + "W.png"));

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void drawObject(Graphics g) {
        if (orientation == Orientation.E)  // animal move to the east side
            g.drawImage(eastImg, super.getPosition().getX() - size / 10, super.getPosition().getY() - size / 10, size * 2, size, pan);
        else if (orientation == Orientation.W) // animal move to the south side
            g.drawImage(westImg, super.getPosition().getX() - size / 10, super.getPosition().getY() - size / 10, size * 2, size, pan);
        else if (orientation == Orientation.N) // animal move to the west side
            g.drawImage(northImg, super.getPosition().getX() - size / 10, super.getPosition().getY() - size / 10, size * 2, size, pan);
        else if (orientation == Orientation.S) // animal move to the north side
            g.drawImage(southImg, super.getPosition().getX() - size / 10, super.getPosition().getY() - size / 10, size * 2, size, pan);
    }

    @Override
    public boolean eat(int energy) {
        if (energy <= 0 || energy >= maxEnergy)
            return false;
        else {
            setCurrentEnergy(energy);
            return true;
        }
    }

}

