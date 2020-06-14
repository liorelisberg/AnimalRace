package Tournaments;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * The Scores class represents a score of animals in tournament.
 * in the year that tournament was held
 * @author Dudi Biton - 314963141
 */
public class Scores
{
    private Map<String, Date> scores;

    public Scores()
    {
        this.scores = Collections.synchronizedMap(new HashMap());
    }

    public void add(String name)
    {
        Date date = new Date();
        this.scores.put(name, date);
    }

    public Map<String, Date> getAll()
    {
        return this.scores;
    }
}