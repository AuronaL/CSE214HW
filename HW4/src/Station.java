/**
 * This is a class simulates a station
 * contain two queues, two boolean source instances - one to generate arrivals for first class, and one to generate arrivals for second class.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Station {
    private PassengerQueue firstClass;
    private PassengerQueue secondClass;
    private final BooleanSource firstArrival;
    private final BooleanSource secondArrival;
    private static Boolean firstOccur;
    private static Boolean secondOccur;
    private int firstServed=0;
    private int secondServed=0;
    private int firstWaitTime=0;
    private int secondWaitTime=0;

    /**
     * get 2nd wait time
     * @return 2nd wait time
     */
    public int getSecondWaitTime() {
        return secondWaitTime;
    }

    /**
     * set 2md wait time
     * @param secondWaitTime 2nd wait time
     */
    public void setSecondWaitTime(int secondWaitTime) {
        this.secondWaitTime = secondWaitTime;
    }

    /**
     * get 1st wait time
     * @return 1st wait time
     */
    public int getFirstWaitTime() {
        return firstWaitTime;
    }

    /**
     * set 1st wait time
     * @param firstWaitTime 1st time
     */
    public void setFirstWaitTime(int firstWaitTime) {
        this.firstWaitTime = firstWaitTime;
    }

    /**
     * get 1st class served passengers
     * @return 1st served
     */
    public int getFirstServed() {
        return firstServed;
    }

    /**
     * get 2nd class served passengers
     * @return 2nd class served passengers
     */
    public int getSecondServed() {
        return secondServed;
    }

    /**
     * set 2nd class served passengers
     * @param secondServed 2nd served
     */
    public void setSecondServed(int secondServed) {
        this.secondServed = secondServed;
    }

    /**
     * set 1st class served passengers
     * @param firstServed 1st served
     */
    public void setFirstServed(int firstServed) {
        this.firstServed = firstServed;
    }

    /**
     * Get second class length
     * @return second class length
     */
    public int getSecondClassLength() {
        return secondClass.size();
    }

    /**
     * Get first class length
     * @return first class length
     */
    public int getFirstClassLength() {
        return firstClass.size();
    }
    /**
     * Get first class queue
     * @return first class queue
     */
    public PassengerQueue getFirstClass() {
        return firstClass;
    }

    /**
     * Get second class queue
     * @return second class queue
     */
    public PassengerQueue getSecondClass() {
        return secondClass;
    }


    /**
     * Construct a new station
     * @param in1 given probability
     * @param in2 given probability
     */
    Station(double in1, double in2) {
       firstArrival=new BooleanSource(in1);
       secondArrival=new BooleanSource(in2);
       firstClass=new PassengerQueue();
       secondClass=new PassengerQueue();
       firstOccur= false;
       secondOccur= false;
    }
    /**
     * This method handles arrival of passengers
     *
     * Passenger arrives randomly
     * dequeue passenger if there is a train at this station
     */
    public void simulateTimeStep() {
        if(firstArrival.occurs()) {
            firstOccur = true;
            LIRRSimulator.id += 1;//++ is the same as +=
            Passenger p = new Passenger(LIRRSimulator.id, LIRRSimulator.time, true);
            firstClass.enqueue(p);
        }

        if(secondArrival.occurs()) {
            secondOccur = true;
            LIRRSimulator.id++;
            Passenger p1 = new Passenger(LIRRSimulator.id, LIRRSimulator.time, false);
            secondClass.enqueue(p1);
        }
    }

    public String toString(){
        String A = "";
        if (firstOccur){
            A+="First Class Passenger ID "+firstClass.peekRear().getId()+" arrives\n";
            firstOccur=false;
        }
        else A+="No first class passenger arrives\n";

        if (secondOccur){
            secondOccur=false;
            A+="Second Class Passenger ID "+secondClass.peekRear().getId()+" arrives\n";
        }
        else A+="No second class passenger arrives\n";

        A+="Queues:\n" +
                "First "+firstClass.toString()+"\n" +
                "Second "+secondClass.toString()+"\n";
        return A;
    }

}
