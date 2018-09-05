import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Collections;
import java.time.format.DateTimeFormatter;
/**
 * defines an underlying data structure to hold events
 * @author (Xinxin Yang)
 */
public class MyCalendar
{
    private ArrayList<Event> events;
    //private LocalDate current;

    /**
     * Constructor for objects of class MyCalendar
     */
    public MyCalendar()
    {
        events = new ArrayList<Event>();
        //current = LocalDate.now();
    }

    /**
     * add event to the calendar
     * @param  e  the event to add
     */
    public void add(Event e)
    {
        events.add(e);
    }

    /**
     * delete a given event
     * @param e the given event
     */
    public void delete(Event e)
    {
        events.remove(e);
    }

    /**
     * delete all events in the calendar
     */
    public void clear()
    {
        events.clear();
    }

    /**
     * get all events ordered in the calendar
     * @return all ordered events
     */
    public  ArrayList <Event> getEvents()
    {
        Collections.sort(events);
        return  events;
    }

    /**
     * get the events ordered in a particular day
     * @param t the given date
     * @return the ordered events in the given date
     */
    public ArrayList<Event> getEventOfDay(LocalDate t)
    {
        ArrayList<Event> target = new  ArrayList<>();
        for (Event e: events )
        {
            if (t.equals(e.getDate()))
            {
                target.add(e);
            }
        }
        Collections.sort(target);
        return target;
    }

    /**
     * get a day view of the calendar
     * @param t the given date
     */
    public void getDayView(LocalDate t)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, MMM d, yyyy");
        System.out.println(formatter.format(t));
        ArrayList<Event> dayEvents =  getEventOfDay(t);
        for (Event e : dayEvents)
        {
            System.out.println(e.getName() + " : " + e.getTimeInterval());
        }

    }

    /**
     * get a month view of a calendar
     * @param c the given date
     */
    public void getMonthView(LocalDate c)
    {
        int firstDay = c.getDayOfWeek().getValue()-1;
        int daysOfMonth = c.lengthOfMonth();
        System.out.println(c.getMonth() + " " + c.getYear());
        System.out.println(" Su Mo Tu We Th Fr Sa");

        //print space
        if (firstDay<7)
        {
            for (int space = 1; space <= firstDay * 3; space++)
            {
                System.out.print(" ");
            }
        }

        // Print the days
        for (int days = 1; days <= daysOfMonth; days++)
        {
            int check = days + firstDay;
            String print = Integer.toString(days);

            if ( getEventOfDay(c.withDayOfMonth(days)).size() != 0)
            {
                print = "{" + print + "}";
            }

            if ((days < 10))
            {
                System.out.print("  " + print);
            }
            else
            {
                System.out.print(" " + print);
            }

            if (check % 7 == 0)
            {
                System.out.println();
            }
        }
    }

    /**
     * add regular event to the calendar
     * @param m the day of week to add the regular event
     * @param n the name of the event
     * @param t the time interval of the event
     * @param year the year to add the regular event
     */
    public void addRepeatEvent(int m, String n, TimeInterval t,int year)
    {
        int daysOfYear = 0;
        if(year % 4 == 0)
        {
            daysOfYear = 366;
        }
        else
        {
            daysOfYear = 365;
        }

        for (int i = m; i <daysOfYear; i = i+7)
        {
            LocalDate repeat = LocalDate.ofYearDay(year,i);
            Event repeatEvent = new Event(n,repeat,t);
            events.add(repeatEvent);
        }

    }



    @Override
    public String toString()
    {
        return events.toString();
    }
}