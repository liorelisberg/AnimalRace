/*
  This program implements the second assignment provided in
  "Advanced Object Oriented Programming" , of year 2020

   AUTHORS:
  * * Lior Elisberg , 308032473
  * * Guy Dahan , 208271734

  ---------------------------------------------------------------------------------------------------------------------
 */

package Graphics;

import animals.*;
import mobility.Point;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

/**
 * CompetitionFrame
 */
public class CompetitionFrame extends JFrame implements ActionListener {
    private static final CompetitionPanel competitionPanel = new CompetitionPanel();
    private final CompetitionMenu competitionMenu = new CompetitionMenu();
    private AddAnimalDialog addAnimalDialog;

    private final int maxNonAirAnimal = 4;
    private final int maxAirAnimal = 5;

    static Point[] startPointWater;
    static Point[] startPoint;

    private final Vector<Animal> animalVector = new Vector<>();
    Vector<Object[]> tempData = new Vector<>();
    String[][] data;

    private int airCurrentPosition = -1; //max index = 4
    private int waterCurrentPosition = -1; //max index = 3
    private int terCurrentPosition = -1; //max index  = 3

    private String chosenCompetition = null;
    private GameState gameState;

    /**
     * CompetitionFrame constructor.
     */
    public CompetitionFrame() {
        super("CompetitionFrame");

        setLayout(new BorderLayout());

        startPointWater = new Point[maxNonAirAnimal];
        startPoint = new Point[maxAirAnimal];

        pointInit();

        addButtonsToActionListener();

        add(competitionMenu, BorderLayout.PAGE_START);
        add(competitionPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(980, 800);
    }

    /**
     * Actives this CompetitionFrame's buttons to the addActionListener
     */
    private void addButtonsToActionListener() {
        competitionPanel.getCompetitionToolbar().getExitBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getInfoBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getEatBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getClearBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getAddAnimalBtn().addActionListener(this);
        competitionPanel.getCompetitionToolbar().getCompetitionBtn().addActionListener(this);

        gameState = GameState.CHOOSING_COMP_TYPE;
        updateBtnStatus();
    }

    /**
     * Sets the starting points of created animals.
     */
    public void pointInit() {
        int heightDiff = 150; //height difference
        int terStartY = 10; //terrestrial starting y
        int waterStartY = 90; //water starting y

        for (int i = 0; i < maxAirAnimal; i++) { ////set air & terrestrial animals points
            startPoint[i] = new Point(5, terStartY);
            terStartY += heightDiff;
            if (i < maxNonAirAnimal) { //set water animals points
                startPointWater[i] = new Point(90, waterStartY);
                waterStartY += heightDiff - i * 2;
            }
        }
    }

    /**
     * @return the current index that the new animal should be positioned on.
     */
    public int getPositionIndex() {
        int animalNewIndex = 0;

        if (chosenCompetition.contains("Air") && airCurrentPosition < maxAirAnimal) {

            airCurrentPosition++;
            if (airCurrentPosition == maxAirAnimal - 1) {
                competitionPanel.getCompetitionToolbar().getAddAnimalBtn().setEnabled(false);
            }
            animalNewIndex = airCurrentPosition;
        } else if (chosenCompetition.contains("Water") && waterCurrentPosition < maxNonAirAnimal) {

            waterCurrentPosition++;
            if (waterCurrentPosition == maxNonAirAnimal - 1) {
                competitionPanel.getCompetitionToolbar().getAddAnimalBtn().setEnabled(false);
            }
            animalNewIndex = waterCurrentPosition;
        } else if (chosenCompetition.contains("Terr") && terCurrentPosition < maxNonAirAnimal) {

            terCurrentPosition++;
            if (terCurrentPosition == maxNonAirAnimal - 1) {
                competitionPanel.getCompetitionToolbar().getAddAnimalBtn().setEnabled(false);
            }
            animalNewIndex = terCurrentPosition;
        }

        return animalNewIndex;
    }

    /**
     * Detects a preformed action on this frame's components.
     *
     * @param e - A given preformed action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object chosen_action = e.getSource();

        if (chosen_action == competitionPanel.getCompetitionToolbar().getExitBtn()) // exit button chosen
            System.exit(0);
        else if (chosen_action == competitionPanel.getCompetitionToolbar().getInfoBtn()) // info button chosen
            createInfoTable();

        else if (chosen_action == competitionPanel.getCompetitionToolbar().getEatBtn()) // info button chosen
        {
            for (Animal animal : animalVector) {
                int newX = animal.getPosition().getX() + 30;
                int newY = animal.getPosition().getY();
                Point nextPoint = new Point(newX, newY);
                animal.move(nextPoint);
            }
        } else if (chosen_action == competitionPanel.getCompetitionToolbar().getClearBtn()) { // clear button chosen
            clearCalled();

        } else if (chosen_action == competitionPanel.getCompetitionToolbar().getCompetitionBtn()) {  // competition button chosen
            chosenCompetition = ActionMessageDialog.chooseCompTypeDialog(this);
            if (chosenCompetition != null) {
                gameState = GameState.CHOOSING_COMP_FIRST_ANIMAL;
                updateBtnStatus();
            }

        } else if (chosen_action == competitionPanel.getCompetitionToolbar().getAddAnimalBtn()) {
            addAnimalDialog = new AddAnimalDialog(this, "Add Animal ", chosenCompetition, "Regular");
            addAnimalDialog.getCreateBtn().addActionListener(this);

        } else if (chosen_action == addAnimalDialog.getCreateBtn()) { //if create button is activated
            if (addAnimalDialog.getGender() != null) {
                createAnimalByType();
                addAnimalDialog.dispose();
            } else
                ActionMessageDialog.createAnimalDialogError(this);

        }
        validate();
        repaint();

        if (airCurrentPosition == maxAirAnimal - 1 || waterCurrentPosition == maxNonAirAnimal - 1 || terCurrentPosition == maxNonAirAnimal - 1) {
            gameState = GameState.COMPETING;
            updateBtnStatus();
        }
    }

    /**
     * Preforms the relevant function call , depending on the current state of the program.
     */
    private void clearCalled() {
        if (animalVector.size() > 0)
            ChooseAnimalToClr();
        else
            clearCompetitionDialog();

        updateBtnStatus();
    }

    /**
     * Changes the game state according to the user's choice of clearing.
     */
    private void clearCompetitionDialog() {
        int choice = ActionMessageDialog.createClrCompetitionDialog(this);
        gameState = (choice == 0 ? GameState.CHOOSING_COMP_FIRST_ANIMAL : GameState.CHOOSING_COMP_TYPE);
    }

    /**
     * Clears the chosen animal from the frame and updated the game state accordingly.
     */
    private void ChooseAnimalToClr() {
        if (displayClrOptions()) {
            updateAnimalLocationPostClr();

            if (animalVector.isEmpty())
                gameState = GameState.CHOOSING_COMP_FIRST_ANIMAL;
            else
                gameState = GameState.CHOOSING_COMP_ANIMALS;
        }

    }

    /**
     * Display the animals' names that can be chose to delete the animal.
     *
     * @return true if animal was chosen by user, else if exited- return false.
     */

    private boolean displayClrOptions() {
        Vector<String> animalNames = new Vector<>();
        for (Animal animal : animalVector) animalNames.add(animal.getName());

        Object[] options = animalNames.toArray();
        String nameToClear = ActionMessageDialog.createClrAnimalDialog(this, options);

        if (nameToClear != null) {
            for (int i = 0; i < animalVector.size(); ++i)
                if (animalVector.get(i).getName().equals(nameToClear)) {
                    animalVector.remove(i);
                    break;
                }
            return true;
        } else
            return false;

    }

    /**
     * Updates all animals' location after an animal was cleared from the competition .S
     */
    private void updateAnimalLocationPostClr() {
        String animalsType = chosenCompetition.contains("Water") ? "waterAnimals" : "otherAnimals";
        switch (animalsType) {
            case "waterAnimals" -> {
                waterCurrentPosition--;
                for (int i = 0; i < animalVector.size(); i++)
                    animalVector.get(i).setPosition(startPointWater[i]);
            }
            case "otherAnimals" -> {
                if (chosenCompetition.contains("Air")) airCurrentPosition--;
                else terCurrentPosition--;
                for (int i = 0; i < animalVector.size(); i++)
                    animalVector.get(i).setPosition(startPoint[i]);
            }
        }
    }

    /**
     * Gets the ImageIcon's object that matches the ImagePath.
     *
     * @param ImagePath - A given image path the should match an image.
     * @return the ImageIcon's object that matches the ImagePath.
     */
    public ImageIcon getImageIcon(String ImagePath) {
        ImageIcon imageIcon = null;
        try {
            imageIcon = new ImageIcon(ImageIO.read(getClass().getResource(ImagePath)));
            Image image = imageIcon.getImage(); // transform it
            Image newImg = image.getScaledInstance(150, 80, Image.SCALE_SMOOTH); // scale it the smooth way
            imageIcon = new ImageIcon(newImg);// transform it back

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return imageIcon;
    }

    /**
     * Creates the Information table when the "Info" button is clicked.
     */
    private void createInfoTable() {
        data = new String[tempData.size()][];
        for (int i = 0; i < tempData.size(); i++)
            data[i] = (String[]) tempData.get(i);

        InfoTable table = new InfoTable(data);
        table.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        table.setSize(600, 200);
        table.setVisible(true);
        table.setTitle("InfoTable");
    }

    public Vector<Animal> getAnimalVector() {
        return animalVector;
    }

    /**
     * Creates an animal by the chosen animal type.
     */
    private void createAnimalByType() {
        String animalType = addAnimalDialog.getAnimalFamilyType();
        String animalName = addAnimalDialog.getAnimalName();
        String animalKind = addAnimalDialog.getAnimalKind();
        gen animalGender = addAnimalDialog.getGender();
        int animalSpeed = addAnimalDialog.getSpeed();
        int animalEngConsumpt = addAnimalDialog.getEnergyConsumption();

        if (animalType.contains("Terr") && terCurrentPosition < maxNonAirAnimal) {
            createTerAnimal(animalName, animalKind, animalGender, animalSpeed, animalEngConsumpt);
        } else if (animalType.contains("Air") && airCurrentPosition < maxAirAnimal) {
            createAirAnimal(animalName, animalKind, animalGender, animalSpeed, animalEngConsumpt);
        } else if (animalType.contains("Water") && waterCurrentPosition < maxNonAirAnimal) {
            createWaterAnimal(animalName, animalKind, animalGender, animalSpeed, animalEngConsumpt);
        } else
            throw new IllegalArgumentException();

        gameState = GameState.CHOOSING_COMP_ANIMALS;
        updateBtnStatus();
    }

    /**
     * Creates an air animal object;
     */
    private void createAirAnimal(String name, String kind, gen gender, int speed, int energy) {
        int startPointIndex = getPositionIndex();

        if (addAnimalDialog.getAnimalKind().contains("pigeon"))
            animalVector.add(new Pigeon(name, speed, startPoint[startPointIndex], competitionPanel, kind));
        else if (addAnimalDialog.getAnimalKind().contains("eagle"))
            animalVector.add(new Eagle(name, speed, startPoint[startPointIndex], competitionPanel, kind));

        tempData.add(animalVector.get(airCurrentPosition).getAnimalInfo());
    }

    /**
     * Creates a terrestrial animal object;
     */
    private void createTerAnimal(String name, String kind, gen gender, int speed, int energy) {

        int startPointIndex = getPositionIndex();

        if (addAnimalDialog.getAnimalKind().contains("dog"))
            animalVector.add(new Dog(name, speed, startPoint[startPointIndex], competitionPanel, kind));
        else if (addAnimalDialog.getAnimalKind().contains("cat"))
            animalVector.add(new Cat(name, speed, startPoint[startPointIndex], competitionPanel, kind));
        else if (addAnimalDialog.getAnimalKind().contains("snake"))
            animalVector.add(new Snake(name, speed, startPoint[startPointIndex], competitionPanel, kind));
        else if (addAnimalDialog.getAnimalKind().contains("alligator"))
            animalVector.add(new Alligator(name, speed, startPoint[startPointIndex], competitionPanel, kind));
        tempData.add(animalVector.get(terCurrentPosition).getAnimalInfo());

    }

    /**
     * Creates a water animal object;
     */
    private void createWaterAnimal(String name, String kind, gen gender, int speed, int energy) {
        int startPointIndex = getPositionIndex();

        if (addAnimalDialog.getAnimalKind().contains("alligator"))
            animalVector.add(new Alligator(name, speed, startPointWater[startPointIndex], competitionPanel, kind));
        else if (addAnimalDialog.getAnimalKind().contains("whale"))
            animalVector.add(new Whale(name, speed, startPointWater[startPointIndex], competitionPanel, kind));
        else if (addAnimalDialog.getAnimalKind().contains("dolphin"))
            animalVector.add(new Dolphin(name, speed, startPointWater[startPointIndex], competitionPanel, kind));
        tempData.add(animalVector.get(waterCurrentPosition).getAnimalInfo());

    }

    /**
     * Updates the relevant buttons' enabling/disabling status by the current game state.
     */
    private void updateBtnStatus() {
        switch (gameState) {
            case CHOOSING_COMP_TYPE -> {
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getAddAnimalBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);
            }
            case CHOOSING_COMP_FIRST_ANIMAL -> {
                competitionPanel.getCompetitionToolbar().getAddAnimalBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);
            }
            case CHOOSING_COMP_ANIMALS -> {
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getAddAnimalBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(false);
            }
            case COMPETING -> {
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getAddAnimalBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(false);
            }
            case CLEARED -> {
                competitionPanel.getCompetitionToolbar().getCompetitionBtn().setEnabled(true);
                competitionPanel.getCompetitionToolbar().getClearBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getInfoBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getAddAnimalBtn().setEnabled(false);
                competitionPanel.getCompetitionToolbar().getEatBtn().setEnabled(false);
            }
            default -> throw new IllegalStateException("Unexpected value: " + gameState);
        }
    }

    public String getChosenCompetition() {
        return chosenCompetition;
    }

    public static void main(String[] args) {
        new CompetitionFrame();
    }
}
