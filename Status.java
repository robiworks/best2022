public class Status {
    boolean critical_levels;
    boolean exceed_consumption_limit;

    public Status(boolean critical_levels, boolean exceed_consumption_limit) {
        this.critical_levels = critical_levels;
        this.exceed_consumption_limit = exceed_consumption_limit;
    }

    public boolean noWarnings() {
        return !critical_levels && !exceed_consumption_limit;
    }
}
