package Tournaments;

import animals.Animal;


/**
 * The Tournament class represents a tournament of animals.
 * in the year that tournament was held
 * @author Dudi Biton - 314963141
 */
public abstract class Tournament
{
    protected TournamentThread tournamentThread;

    public Tournament(Animal[][] animal_arr)
    {
        this.tournamentThread = new TournamentThread();
        setup(animal_arr);
    }

    public TournamentThread getTournamentThread()
    {
        return this.tournamentThread;
    }

    public abstract void setup(Animal[][] animal_arr);
}