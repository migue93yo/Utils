package es.android.utils.domain;

/**
 * Created by miguelconde on 11/01/17.
 */

public class DaysOfWeek {

    private int id;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
    private Boolean sunday;

    public DaysOfWeek(){
        monday = false;
        tuesday = false;
        wednesday = false;
        thursday = false;
        friday = false;
        saturday = false;
        sunday = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public void setTuesday(boolean tuesday) {
        this.tuesday = tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public void setWednesday(boolean wednesday) {
        this.wednesday = wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public void setThursday(boolean thursday) {
        this.thursday = thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public void setSaturday(boolean saturday) {
        this.saturday = saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }

    public Boolean[] toArray(){
        Boolean[] result = new Boolean[7];
        result[0] = monday;
        result[1] = tuesday;
        result[2] = wednesday;
        result[3] = thursday;
        result[4] = friday;
        result[5] = saturday;
        result[6] = sunday;
        return result;
    }

    public Boolean getDayOfWeek(int num){
        Boolean result = null;
        switch(num){
            case 0:
                result = monday;
                break;
            case 1:
                result = tuesday;
                break;
            case 2:
                result = wednesday;
                break;
            case 3:
                result = thursday;
                break;
            case 4:
                result = friday;
                break;
            case 5:
                result = saturday;
                break;
            case 6:
                result = sunday;

        }
        return result;
    }

    public void setDayOfWeek(int position, boolean value){
        switch(position){
            case 0:
                monday = value;
                break;
            case 1:
                tuesday = value;
                break;
            case 2:
                wednesday = value;
                break;
            case 3:
                thursday = value;
                break;
            case 4:
                friday = value;
                break;
            case 5:
                saturday = value;
                break;
            case 6:
                sunday = value;

        }
    }

}
