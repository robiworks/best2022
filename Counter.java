import java.util.*;

public class Counter {
    ArrayList<Day> readings;

    public Counter() {
        this.readings = new ArrayList<Day>();
    }

    public void addDay(Date d, ArrayList<Meritev> readings) {
        // create new day
        Day day = new Day(d);
        // add all the Meritev to the day
        for (Meritev m : readings) {
            Status s = day.addMeritev(m);
            if (!s.noWarnings()) {
                // TODO TODO: warning handling
            }
        }
        // add the day to the list of days
        this.readings.add(day);
    }

    public Day createNewDay() {
        // creates a new date
        Date d = new Date();
        // creates a new day
        Day day = new Day(d);
        // add the day to the list of days
        this.readings.add(day);
        // return the day
        return day;
    }

    public void addNewEntry(Meritev m) {
        // pass the meritev to the last day
        Status s = this.readings.get(this.readings.size() - 1).addMeritev(m);
        if (!s.noWarnings()) {
            // TODO TODO: warning handling
        }
    }

    
}
