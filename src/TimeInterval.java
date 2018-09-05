import java.time.LocalTime;
/**
 * represents an interval of time, suitable for events
 * @author (Xinxin Yang)
 */
public class TimeInterval
{
    private LocalTime startingTime;
    private LocalTime endingTime;

    /**
     * Constructor for objects of class TimeInterval
     * @param s the given starting time to create time interval
     * @param e the given ending time to create time interval
     */
    public TimeInterval(LocalTime s, LocalTime e)
    {
        startingTime = s;
        endingTime = e;
    }

    /**
     * get the start time of the time interval
     * @return the start time of the time interval
     */
    public LocalTime startingTime()
    {
        return startingTime;
    }

    /**
     * get the ending time of the time interval
     * @return the ending time of the time interval
     */
    public LocalTime endingTime()
    {
        return endingTime;
    }

    /**
     * check if time is conflict
     * @param t  the given time interval
     * @return true if time is conflict
     */
    public boolean conflict (TimeInterval t)
    {
        if (this.endingTime.isBefore(t.startingTime) || this.startingTime.isAfter(t.endingTime))
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    @Override
    public String toString()
    {
        return startingTime.toString() + " - " + endingTime.toString();
    }
}
