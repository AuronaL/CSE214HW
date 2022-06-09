/**
 * This is a class used for Passenger which contains an integer id, the arrival time, and a boolean for isFirstClass.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Passenger {
    private int id;
    private int arrivalTime;
    private boolean isFirstClass;

    /**
     * Construct a new passenger
     * @param id passenger's id
     * @param arrivalTime passenger's arrival time
     * @param isFirstClass first class
     */
    public Passenger(int id, int arrivalTime, boolean isFirstClass){
        this.id=id;
        this.arrivalTime=arrivalTime;
        this.isFirstClass=isFirstClass;
    }
    /**
     * Getter method for id
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Setter method for id
     * @param id id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter arrival time
     * @return arrival time
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Set arrival time
     * @param arrivalTime
     */
    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Get first class
     * @return first class
     */
    public boolean getFirstClass() {
        return isFirstClass;
    }

    /**
     * Set first class
     * @param firstClass
     */
    public void setFirstClass(boolean firstClass) {
        isFirstClass = firstClass;
    }

    /**
     * Method for string the name
     * @return passenger's information
     */
    public String toString(){
        return String.format("P"+getId()+"@T"+getArrivalTime());
    }

}
