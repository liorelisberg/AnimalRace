package Tournaments;

import animals.Animal;
import animals.AnimalThread;
import java.util.Vector;

/**
 * The CourierTournament class represents a Courier tournament of animals
 * in the year that tournament was held
 * @author Dudi Biton - 314963141
 */
public class CourierTournament extends Tournament
{
    public static Thread thread = new Thread();
    public static Boolean stop = false;
    private int type_tour_flag = 0;
    public static Vector<Animal> animal_courier_arr = new Vector<>();


    public CourierTournament(Animal[][] animal_arr)
    {
        super(animal_arr);
    }

    @Override
    public void setup(Animal[][] animal_arr)
    {
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
        AnimalThread t = new AnimalThread(false, false, temp[0][i], type_tour_flag);
        thread = new Thread(t);
        animal_courier_arr.add(t.GetParticipant());
        thread.start();
    }

    public static void StopThreads()
    {
        stop = true;
    }
}