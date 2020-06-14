package Tournaments;

/**
 * The Referee class represents a referee of tournament
 * in the year that tournament was held
 * @author Dudi Biton - 314963141
 */
public class Referee implements Runnable
{
    private Scores scores;
    private String name;
    private Boolean isArrived = false;

    public Referee(Scores scores, String name, Boolean isArrived)
    {
        this.name = name;
        this.scores = scores;
        this.isArrived = isArrived;
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
        synchronized (this.isArrived)
        {
            while (this.isArrived == false)
            {
                try{ wait(); }
                catch(InterruptedException e){}
            }
            this.scores.add(this.name);
            this.isArrived = false;
            notify();
        }
    }
}