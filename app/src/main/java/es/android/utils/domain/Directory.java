package es.android.utils.domain;

/**
 * Created by miguelconde on 11/01/17.
 */

public class Directory {

    private int id;
    private String ruteOrigin;
    private String ruteDestiny;
    private boolean active;
    private DaysOfWeek daysOfWeek;

    public Directory(){
        daysOfWeek = new DaysOfWeek();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuteOrigin() {
        return ruteOrigin;
    }

    public void setRuteOrigin(String ruteOrigin) {
        this.ruteOrigin = ruteOrigin;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public DaysOfWeek getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(DaysOfWeek daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public String getRuteDestiny() {
        return ruteDestiny;
    }

    public void setRuteDestiny(String ruteDestiny) {
        this.ruteDestiny = ruteDestiny;
    }
}
