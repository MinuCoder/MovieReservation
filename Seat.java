package MovieReservation;

/**
 * Created by 민우 on 2017-07-27.
 */
public class Seat {
    private String name;

    public String getName(){
        return name;
    }
    public void reserve(String name){
        this.name = name;
    }
    public void cancel(){
        this.name=null;
    }
    public boolean isOccupied(){
        return name != null;
    }
    public boolean match(String checkName) {
        return name.equals(checkName);
    }
}