package Tournaments;

import animals.Animal;
import animals.AnimalThread;
import java.util.Vector;

/**
 * The RegularTournament class represents a regular tournament of animals
 * in the year that tournament was held
 * @author Dudi Biton - 314963141
 */
public class RegularTournament extends Tournament {
    Vector<Object> field = new Vector<>();
    private Boolean startFlag = false;
    private Boolean finishFlag = false;
    public static Thread thread = new Thread();
    public static Boolean stop = false;
    private int type_tour_flag = 1;

    public RegularTournament(Animal[][] animal_arr) {
        super(animal_arr);

    }

    @Override
    public void setup(Animal[][] animal_arr) {
        Boolean startFlag = false;
        Scores scores = new Scores();

        for (int i = 0; i < animal_arr.length; i++) {
            Boolean finishFlag = false;
            Thread thread = new Thread(new AnimalThread(startFlag, finishFlag, animal_arr[i][0], this.type_tour_flag));
            Referee referee = new Referee(scores, String.valueOf(i), finishFlag);
        }
    }

    /**
     * The method is responsible for running the thread
     * @param new_animal Represents an animal participating in the competition
     * @param i Represents index of animals
     */
    public void start_th(Animal new_animal, int i)
    {
        Animal[][] temp = new Animal[1][i+1];
        temp[0][i] = new_animal;
        thread = new Thread(new AnimalThread(false, false, temp[0][i], type_tour_flag));
        thread.start();
    }

    public static void StopThreads()
    {
        stop = true;
    }
}