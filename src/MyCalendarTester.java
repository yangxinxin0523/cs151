import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * the tester to test the calendar using main menu
 * author: Xinxin Yang
 */
public class MyCalendarTester
{
    public static void main(String [] args)
    {
        LocalDate cal = LocalDate.now(); // capture today
        MyCalendar myCal = new MyCalendar();
        int year = cal.getYear();

        printCalendar(cal);
        boolean exit = false;
        do{
            Scanner sc = new Scanner(System.in);
            printMenu();

            String input1 = sc.nextLine().toUpperCase();
            switch(input1)
            {
                case "L": {
                    final String EVENTFILE = "resources/events.txt";
                    File infile = new File(EVENTFILE);
                    try {
                        Scanner fin = new Scanner(infile);
                        while (fin.hasNextLine()) {
                            String name = fin.nextLine();
                            String info = fin.nextLine();
                            String[] tokens = info.split(" ");
                            String date = tokens[0];
                            String startingT = tokens[1];
                            String endingT = tokens[2];

                            LocalTime s = LocalTime.parse(startingT);
                            LocalTime e = LocalTime.parse(endingT);
                            TimeInterval t= new TimeInterval(s,e);

                            if (Character.isLetter(date.charAt(0)))
                            {
                                if (date.contains("S")) {
                                    int weekday = 0;
                                    myCal.addRepeatEvent(weekday, name, t, year);
                                }
                                else if (date.contains("M"))
                                {
                                int weekday = 1;
                                myCal.addRepeatEvent(weekday, name, t, year);
                                }
                                else if (date.contains("T"))
                                {
                                    int weekday = 2;
                                    myCal.addRepeatEvent(weekday, name, t, year);
                                }
                                else if (date.contains("W"))
                                {
                                    int weekday = 3;
                                    myCal.addRepeatEvent(weekday, name, t, year);
                                }
                                else if (date.contains("R"))
                                {
                                    int weekday = 4;
                                    myCal.addRepeatEvent(weekday, name, t, year);
                                }
                                else if (date.contains("F"))
                                {
                                    int weekday = 5;
                                    myCal.addRepeatEvent(weekday, name, t, year);
                                }
                                else if (date.contains("A"))
                                {
                                    int weekday = 6;
                                    myCal.addRepeatEvent(weekday, name, t, year);
                                }
                                else
                                {
                                    System.out.println("read invalid input from file");
                                }
                            }
                            else
                            {
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
                                LocalDate d = LocalDate.parse(date,formatter);
                                Event theEvent = new Event(name, d, t);
                                myCal.add(theEvent);
                            }
                        }
                        fin.close();
                    }
                    catch(FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                    System.out.println("file has loaded");
                    break;
                }
                case "V":
                {
                    System.out.println("[D]ay view or [M]view ? ");
                    String input2 = sc.nextLine().toUpperCase();
                    switch(input2) {
                        case "D": {
                            myCal.getDayView(cal);
                            System.out.println("\n" +"[P]revious or [N]ext or [G]o back to main menu ? ");
                            while (sc.hasNextLine()) {
                                String input3 = sc.nextLine().toUpperCase();
                                if (input3.equals("G")) {
                                    break;
                                } else {

                                    switch (input3) {
                                        case "P": {
                                            cal = cal.minusDays(1); // LocalDateTime is immutable
                                            myCal.getDayView(cal);
                                            break;
                                        }
                                        case "N": {
                                            cal = cal.plusDays(1); // LocalDateTime is immutable
                                            myCal.getDayView(cal);
                                            break;
                                        }
                                        default: {
                                            System.out.println("InValid Seclection");
                                        }
                                    }
                                }
                                System.out.println("\n" + "[P]revious or [N]ext or [G]o back to main menu ? ");
                            }
                            break;
                        }


                        case "M":
                        {
                            myCal.getMonthView(cal);
                            System.out.println("\n" + "[P]revious or [N]ext or [G]o back to main menu ? ");
                            while (sc.hasNextLine())
                            {
                                String input4 = sc.nextLine().toUpperCase();
                                if (input4.equals("G"))
                                {
                                    break;
                                }
                                else{
                                    switch(input4)
                                    {
                                        case "P":
                                        {
                                            cal = cal.minusMonths(1); // LocalDateTime is immutable
                                            myCal.getMonthView(cal);
                                            break;
                                        }
                                        case"N":
                                        {
                                            cal = cal.plusMonths(1); // LocalDateTime is immutable
                                            myCal.getMonthView(cal);
                                            break;
                                        }
                                        default:
                                        {
                                            System.out.println("InValid Seclection");
                                        }
                                    }
                                }
                                System.out.println("\n" + "[P]revious or [N]ext or [G]o back to main menu ? ");
                            }
                            break;
                        }
                    }
                    break;
                }

                case"C":
                {
                    //Scanner sc5 = new Scanner(System.in);
                    System.out.println("Please stick to the following format to enter data:");
                    System.out.println("Title: a string (doesn't have to be one word");
                    System.out.println("date: MM/dd/yyyy");
                    System.out.println("Starting time and ending time: 24 hour clock such as 06:00 for 6 AM and 15:30 for 3:30 PM");

                    String name = sc.nextLine();
                    String date = sc.nextLine();
                    String starting = sc.next();
                    String ending = sc.next();

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate dt = LocalDate.parse(date,formatter);

                    LocalTime st = LocalTime.parse(starting);
                    LocalTime et = LocalTime.parse(ending);
                    TimeInterval tInterval= new TimeInterval(st,et);

                    Event theEvent = new Event (name,dt,tInterval);
                    ArrayList<Event> dayEvent =  myCal.getEventOfDay(dt);


                    boolean conflict =false;
                    for (Event e : dayEvent) {
                        if (e.getTimeInterval().conflict(tInterval)) {
                            conflict = true;
                            break;
                        }
                    }

                    if (conflict)
                    {
                        System.out.println("new event is a conflict with existing events");
                    }
                    else
                    {
                        myCal.add(theEvent);
                        System.out.println("new event is created");
                    }
                    break;
                }
                case"G":
                {
                    System.out.println("Please enter a date in the form of MM/dd/yyyy");
                    String input6 = sc.nextLine();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                    LocalDate d = LocalDate.parse(input6,formatter);

                    myCal.getDayView(d);
                    break;
                }
                case "E":
                {
                    System.out.println(myCal.getEvents());
                    break;
                }
                case "D":
                {
                    System.out.println("[S]elected or [A]ll ? ");
                    String input7 = sc.nextLine().toUpperCase();
                    switch(input7)
                    {
                        case"S":
                        {
                            System.out.println("Enter the date [MM/dd/yyyy]");

                            String input8 = sc.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                            LocalDate date = LocalDate.parse(input8,formatter);
                            myCal.getDayView(date);

                            System.out.println("Enter the name of the event to be deleted");
                            String input9 = sc.nextLine();
                            Boolean found = false;
                            for (Event e : myCal.getEventOfDay(date))
                            {
                                if (input9.equals(e.getName()))
                                {
                                    myCal.delete(e);
                                    found = true;
                                    break;
                                }
                            }

                            if (found)
                            {
                                System.out.println("event deleted");

                            }
                            else
                            {
                                System.out.println("error:there is no such event");
                            }
                            break;
                        }
                        case"A":
                        {
                            myCal.clear();
                            break;
                        }
                        default:
                        {
                            System.out.println("InValid Seclection");
                        }
                    }
                    break;
                }
                case "Q":
                {
                    System.out.println("Good Bye");
                    exit = true;
                    break;
                }
                default:
                {
                    System.out.println("InValid Seclection");
                }

            }

        }
        while(!exit);
    }


    /**
     * method to print original calendar on a given LocalDate
     * @param c the given LocalDate
     */
    public static void printCalendar(LocalDate c)
    {
        int firstDay = c.getDayOfWeek().getValue()-1;
        int daysOfMonth = c.lengthOfMonth();
        int date = c.getDayOfMonth();
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

            if(days == date)
            {
                print = "[" + print + "]";
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
     * print the main menu
     */
    public static void printMenu()
    {
        System.out.println("Select one of the following options:");
        System.out.println("[L]oad   [V]iew by  [C]reate, [G]o to [E]vent list [D]elete [Q]uit");
    }

}

