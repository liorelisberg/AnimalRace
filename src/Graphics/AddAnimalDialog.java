package Graphics;

import animals.gen;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

/**
 * Creates a JDialog frame to add animal to the competition.
 */
public class AddAnimalDialog extends JDialog implements ActionListener {
    GridBagConstraints gbc = new GridBagConstraints();

    private String[] waterAnimals = {"dolphin1", "dolphin2", "dolphin3", "whale1", "whale2", "alligator1", "alligator2"};
    private String[] airAnimals = {"eagle1", "eagle2", "eagle3", "pigeon1"};
    private String[] terAnimals = {"dog1", "dog2", "dog3", "dog4", "dog5", "dog6", "cat1", "cat2", "snake1", "snake2", "snake3", "alligator3"};

    private JLabel nameLabel = new JLabel("Animal Name :");
    private JLabel imageLabel = new JLabel("Animal Type :");
    private JLabel genLabel = new JLabel("Gender :");
    private JLabel speedLabel = new JLabel("Speed :");
    private JLabel engLabel = new JLabel("Energy Consumpt :");
    private JLabel imgChoiceLabel = new JLabel("Animal Icon :");
    private JLabel imgLabel;

    private JTextField nameTextField = new JTextField();
    private final JComboBox<String> imageCbox = new JComboBox<>();
    private final JRadioButton maleBtn = new JRadioButton("Male");
    private final JRadioButton femaleBtn = new JRadioButton("Female");
    private final JRadioButton hermaBtn = new JRadioButton("Hermaphrodite");
    private final JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 5);
    private final JSlider engSlider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
    private final ButtonGroup buttons = new ButtonGroup();
    private final JButton createBtn = new JButton("Create Animal");

    private String tourType; //kind of tournament, exampe: Regular
    private String animalFamilyType; // kind of animal, example: Air Animal
    private String animalName; // name of animal , example:REX
    private String animalKind; // kind of animal , example: Dog
    private gen gender; //animal's gender
    private double speed; //animal's speed
    private int energyConsumption; // animal's energy consumption

    /**
     * AddAnimalDialog constructor.
     *
     * @param frame - A given CompetitionFrame frame on which the JDialog is created.
     * @param name  - A given name to the JDialog's name.
     */
    public AddAnimalDialog(JFrame frame, String name, String animalFamilyType, String tourType) {
        super(frame, name);
        setLayout(new GridBagLayout());

        this.animalFamilyType = animalFamilyType;
        this.tourType = tourType;

        imgLabel = new JLabel();

        TitledBorder titledBorder = new TitledBorder("Current Tournament - " + animalFamilyType + " , " + tourType);
        titledBorder.setTitleColor(Color.RED);
        CompoundBorder border = new CompoundBorder(new EmptyBorder(5, 5, 5, 5), titledBorder);
        super.getRootPane().setBorder(border);

        gbc.insets = new Insets(5, 5, 5, 5);

        //add the name label
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameLabel, gbc);

        //add the name text field
        gbc.gridx = 2;
        gbc.weightx = GridBagConstraints.BOTH;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(nameTextField, gbc);

        //add the image label
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(imageLabel, gbc);

        //add image combobox
        chooseAddItemsToJcb();
        gbc.weightx = GridBagConstraints.BOTH;
        gbc.gridx = 2;
        this.add(imageCbox, gbc);

        //add the gender label
        gbc.gridy = 5;
        gbc.gridx = 0;
        add(genLabel, gbc);

        //add the radio buttons for gender
        gbc.gridx = 1;
        maleBtn.setActionCommand("Male");
        add(maleBtn, gbc);
        gbc.gridx = 2;
        hermaBtn.setActionCommand("Hermaphrodite");
        add(hermaBtn, gbc);
        gbc.gridx = 3;
        femaleBtn.setActionCommand("Female");
        add(femaleBtn, gbc);

        //add buttons to group buttons
        buttons.add(maleBtn);
        buttons.add(femaleBtn);
        buttons.add(hermaBtn);

        gbc.gridwidth = 0;
        //add the speed label
        gbc.gridy = 7;
        gbc.gridx = 0;
        add(speedLabel, gbc);

        //add the speed slider
        gbc.gridx = 1;
        add(speedSlider, gbc);

        //add energy consumption label
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(engLabel, gbc);

        //add energy consumption slider
        gbc.gridx = 1;
        add(engSlider, gbc);

        //add image choice label
        gbc.gridwidth = 3;
        gbc.gridy = 9;
        gbc.gridx = 0;
        add(imgChoiceLabel, gbc);

        //add the create button
        gbc.gridwidth = 2;
        gbc.gridx = 1;
        gbc.gridy = 10;
        gbc.fill = GridBagConstraints.BOTH;
        add(createBtn, gbc);

        //adds the matching image
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridy = 9;
        gbc.gridx = 2;
        showImage();

        nameTextField.addActionListener(this);
        imageCbox.addActionListener(this);
        maleBtn.addActionListener(this);
        hermaBtn.addActionListener(this);
        femaleBtn.addActionListener(this);
        createBtn.addActionListener(this);

        speedSlider.setMajorTickSpacing(1);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);

        engSlider.setMajorTickSpacing(10);
        engSlider.setPaintTicks(true);
        engSlider.setPaintLabels(true);

        activateSliders();

        this.setSize(600, 450);
        this.setVisible(true);
    }

    private void activateSliders() {
        speedSlider.addChangeListener(e -> {
            speedLabel.setText("Speed : " + speedSlider.getValue() + " P/CM");
            speed = speedSlider.getValue();
        });

        engSlider.addChangeListener(e -> {
            engLabel.setText("Energy Consumpt :" + engSlider.getValue() + " E/P");
            energyConsumption = engSlider.getValue();
        });
    }

        /**
         * Detects a preformed action on this frame's components.
         *
         * @param e - A given preformed action.
         */
        public void actionPerformed (ActionEvent e){
            Object chosen_action = e.getSource();

            if (chosen_action == nameTextField)
                animalName = nameTextField.getText();
            else if (chosen_action == imageCbox)
                showImage();
            else if (chosen_action == maleBtn || chosen_action == femaleBtn || chosen_action == hermaBtn) {
                gender = gen.valueOf(getSelectedButtonText(buttons));
            }
//         else if (chosen_action == createBtn)
//            if (gender != null)
//                System.out.println("animal can be created   " + gender);
//            else
//                System.out.println("animal can not be created   ");
        }

        public String getSelectedButtonText (ButtonGroup buttonGroup){
            for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton button = buttons.nextElement();

                if (button.isSelected()) {
                    System.out.println("caught it - its" + button.getText());
                    return button.getText();
                }
            }
            return null;
        }

        /**
         * Create the JComboBox option according to user's choice.
         */
        private void chooseAddItemsToJcb () {
            if (animalFamilyType.contains("Terr")) {
                addItemJcb(terAnimals);
            } else if (animalFamilyType.contains("Air")) {
                addItemJcb(airAnimals);
            } else if (animalFamilyType.contains("Water")) {
                addItemJcb(waterAnimals);
            }
        }

        /**
         * Shows the image that match the user's choices.
         */
        private void showImage () {
            imgLabel = new JLabel();
            this.animalKind = (String) imageCbox.getSelectedItem();
            System.out.println("reached showImage()     " + animalKind);

            try {
                ImageIcon imageIcon = new ImageIcon(getClass().getResource("/" + animalKind + "E.png")); // load the image to a imageIcon
                Image image = imageIcon.getImage(); // transform it
                Image newImg = image.getScaledInstance(120, 120, Image.SCALE_SMOOTH); // scale it the smooth way
                imageIcon = new ImageIcon(newImg);// transform it back
                imgLabel.setIcon(imageIcon);
                this.add(imgLabel, gbc);

                System.out.println(getClass().getResource("/" + animalKind + "E.png"));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            validate();
        }

        /**
         * Adds the options to the JComboBox.
         *
         * @param arr - A given array of options.
         */
        public void addItemJcb (String[]arr){
            for (String s : arr)
                imageCbox.addItem(s);
        }

        public JButton getCreateBtn () {
            return createBtn;
        }

        public String getAnimalFamilyType () {
            return animalFamilyType;
        }

        public String getAnimalName () {
            return animalName;
        }

        public String getAnimalKind () {
            return animalKind;
        }

        public double getSpeed () {
            return speed;
        }

//
//        public static void main (String[]args){
//            new AddAnimalDialog(new JFrame(), "Add Animal ", "Air animals", "Regular");
//        }

    }