package Tournaments;


/**
 * The TournamentThread class represents a thread that start the tournament.
 * in the year that tournament was held
 * @author Dudi Biton - 314963141
 */
public class TournamentThread implements Runnable
{
    private Scores scores;
    private Boolean startSignal;
    private int groups;

    public TournamentThread()
    {
        this.scores = new Scores();
        this.startSignal = false;
        this.groups = 6;
    }

    public int getGroups()
    {
        return this.groups;
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run()
    {
        this.startSignal = true;
        notifyAll();
    }
}