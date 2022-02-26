import java.util.*;

public class Day {
    Day yesterday;
    Date date;
    ArrayList<Meritev> readings;
    double consumption;
    double remaining_gas;

    public Day(Date date) {
        this.date = date;
        this.readings = new ArrayList<Meritev>();
        this.consumption = 0;
        this.remaining_gas = 0;
        this.yesterday = null;
    }

    public Day(Date date, Day yesterday) {
        this.date = date;
        this.readings = new ArrayList<Meritev>();
        this.consumption = 0;
        this.remaining_gas = yesterday.remaining_gas;
        this.yesterday = yesterday;
    }

    public Day(Date date, Meritev m) {
        this.date = date;
        this.readings = new ArrayList<Meritev>();
        this.readings.add(m);
        this.consumption = 0;
        this.remaining_gas = m.vrniVolumen();
        this.yesterday = null;
    }

    public Status addMeritev(Meritev m) {
        // if this is the first entry for the day, we need to do some extra groundwork
        if (readings.size() == 0) {
            // we need to poll yesterday to calculate the difference between the last reading of yesterday and first reading for today
            if (yesterday != null) {
                // now we can calculate the difference between the last reading of yesterday and first reading for today
                switch (m.validate(remaining_gas)) {
                    case -1:
                        // we can disregard this entry - not adding it to the consumption or remaining_gas
                        break;
                    case 0:
                        // calculate the differnece between the last reading of yesterday and first reading for today and add it to consumption
                        consumption += remaining_gas - m.vrniVolumen();
                        remaining_gas = m.vrniVolumen();
                        break;
                    case 1:
                        // we can just set remaining_gas to the new value
                        remaining_gas = m.vrniVolumen();
                        break;
                }
                // push the new Meritev to the list of readings
                readings.add(m);
            } else {
                this.remaining_gas = m.vrniVolumen();
                this.consumption = 0;
                this.readings.add(m);
            }
        } else {
            // validate the new reading against the last reading
            switch (m.validate(remaining_gas)) {
                case -1:
                    // we can disregard this entry - not adding it to the consumption or remaining_gas
                    break;
                case 0:
                    // calculate the differnece between the last reading of yesterday and first reading for today and add it to consumption
                    consumption += remaining_gas - m.vrniVolumen();
                    remaining_gas = m.vrniVolumen();
                    break;
                case 1:
                    // we can just set remaining_gas to the new value
                    remaining_gas = m.vrniVolumen();
                    break;
            }
        }
        // check if the consumption is < 0.4 and if so, return true
        return this.checkStatus();
    }

    public Status checkStatus() {
        // TODO TODO figure out how we can normalize this data to the end of the day, now we are taking yesterdays consumption
        return new Status(this.consumption < 0.4, this.remaining_gas > 1 + 7 * yesterday.consumption);
    }

}
