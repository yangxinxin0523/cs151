
import java.time.format.DateTimeFormatter;
        import java.time.LocalDate;
/**
 * represent an event.
 * @author (Xinxin Yang)
 */
public class Event implements Comparable<Event>
{
    private String name;
    private LocalDate date;
    private TimeInterval t_Interval;

    /**
     * Constructor for objects of class Event
     * @param name the given name of the event
     * @param date the given date of the event
     * @param t_Interval the given timeInterval of the event
     */
    public Event(String name, LocalDate date, TimeInterval t_Interval)
    {
        this.name = name;
        this.date = date;
        this.t_Interval = t_Interval;
    }

    /**
     *gets the name of the event
     * @return  name of the event
     */
    public String getName()
    {
        return name;
    }

    /**
     *gets the time Interval of the event
     * @return  time Interval of the event
     */
    public TimeInterval getTimeInterval()
    {
        return t_Interval;
    }

    /**
     *gets the date of the event
     * @return  date of the event
     */
    public LocalDate getDate()
    {
        return date;
    }

    /**
     * the way to compare two event, first compare date, second compare time interval
     * @param other the event to compare
     * @return the compare result
     */
    public int compareTo(Event other)
    {
        int d1 = date.compareTo(other.date);
        if (d1 != 0 )
        {
            return d1;
        }
        else
        {
            return t_Interval.startingTime().compareTo(other.t_Interval.startingTime());
        }
    }

    @Override
    public String toString()
    {
        String s = date.getDayOfWeek() + " " + date + " " + t_Interval
                + " " + name + "\n";
        return s;
    }
}