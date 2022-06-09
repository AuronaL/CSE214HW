
/**
 * This is a class simulates trains
 * contain an array of the stations the train has to visit (at time%5==0)
 * simulate station arrival, where passengers are dequeued.
 *
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Train {
    private final int firstCapacity;
    private final int secondCapacity;
    private int timeUntilNextArrival;
    private int firstClassNumber;
    private int secondClassNumber;
    private int firstCin2ndC;
    static String[] station={"Huntington", "Syosset" ,"Hicksville","Mineola"};
    // !! index 0~3 -> 4 is out of boundary

    //Station list ={"Mineola" <- "Hicksville"<- "Syosset" <- "Huntington"};
    // Defined in the method StationList()
    private int index =-1;
    public String trainScreen ="";

    /**
     * get amount of 1 class passengers
     * @return 1number
     */
    public int getFirstClassNumber() {
        return firstClassNumber;
    }
    /**
     * get amount of 2 class passengers
     * @return 2number
     */
    public int getSecondClassNumber() {
        return secondClassNumber;
    }
    /**
     * get amount of 1 class passengers in 2 class
     * @return 1number in 2
     */
    public int getFirstCin2ndC() {
        return firstCin2ndC;
    }

    /**
     * This is a constructor that construct a train
     * @param firstCapacity firstCapacity
     * @param secondCapacity secondCapacity
     * @param timeUntilNextArrival timeUntilNextArrival
     *                             if time>0 wait;
     *                             if time=0, station dequeue, train enqueue
     *                             after =0; set it to 5. loop
     *
     */
    public Train(int firstCapacity, int secondCapacity, int timeUntilNextArrival) {
        this.firstCapacity = firstCapacity;
        this.secondCapacity = secondCapacity;
        this.timeUntilNextArrival = timeUntilNextArrival;
        firstClassNumber=0;
        secondClassNumber=0;
        firstCin2ndC=0;
    }

    /**
     * This method simulates the passing of one time step
     *
     * 1. passenger queue from stations crate
     * 2. dequeue passenger from stations when timeUntilNextArrival =0 && not full
     * if the first class is full, get first class passenger into 2nd class, still count first class
     * 3. number of people on train +1
     */
    public void simulateTimeStep() {
        // before final station
        if( index<3) { // index start from -1
            //Train arrives -> index +1
            if (timeUntilNextArrival ==0 ) { // time=0 set off/ arrives i % 5 == 0
                index++; //station update
                timeUntilNextArrival += 4;// time update (a+=b -> a=a+b; a=+b-> a=b)
                // Arrival screen string before (come first because we need original number of people on the train
                if (firstCin2ndC == 0) {
                    trainScreen = "arrives at " + station[index] + ", There are " +
                            firstClassNumber + " passengers in first class and " +
                            secondClassNumber + " in second class.\n";
                } else {
                    trainScreen = "arrives at " + station[index] + ", There are " +
                            firstClassNumber + " passengers in first class and " +
                            (secondClassNumber + firstCin2ndC) + " in second class " +
                            "where " + firstCin2ndC + " of them are first class passengers in the second class seat\n";}


                //dequeue passengers
                    PassengerQueue dequeue1 = new PassengerQueue();
                    PassengerQueue dequeue2 = new PassengerQueue();

                    while (firstClassNumber < firstCapacity && stationLists(index).getFirstClass().size() != 0) {
                        dequeue1.enqueue(stationLists(index).getFirstClass().dequeue());
                        firstClassNumber += 1;
                        int t = stationLists(index).getFirstServed();
                        stationLists(index).setFirstServed(t+1);
                        int m= stationLists(index).getFirstWaitTime();
                        stationLists(index).setFirstWaitTime(m+LIRRSimulator.time);
                    }
                    while (stationLists(index).getFirstClass().size()!=0 && (secondClassNumber + firstCin2ndC) < secondCapacity) {
                        dequeue2.enqueue(stationLists(index).getFirstClass().dequeue());
                        firstCin2ndC++;
                        int t = stationLists(index).getFirstServed();
                        stationLists(index).setFirstServed(t+1);
                        int m= stationLists(index).getFirstWaitTime();
                        stationLists(index).setFirstWaitTime(m+LIRRSimulator.time);
                    }
                    while (stationLists(index).getSecondClass().size() != 0 && (secondClassNumber + firstCin2ndC) < secondCapacity) {
                        dequeue2.enqueue(stationLists(index).getSecondClass().dequeue());
                        secondClassNumber++;
                        int t = stationLists(index).getSecondServed();
                        stationLists(index).setSecondServed(t+1);
                        int m= stationLists(index).getSecondWaitTime();
                        stationLists(index).setSecondWaitTime(m+LIRRSimulator.time);
                    }
                    // Arrival screen string after
                    trainScreen += "Passengers embarking in first class: " + dequeue1.dequeueString() + "\n" +
                            "Passengers embarking in second class: " + dequeue2.dequeueString() + "\n";
                }
                //Train is on the way: screen string
                else {
                    trainScreen = "will arrive in " + station[index + 1] + " in " +
                        timeUntilNextArrival + " minutes.\n";
                    timeUntilNextArrival-=1;//0=5->4-1;
                    }


        }
        else trainScreen="has stopped picking up passengers.\n";
    }

    /**
     * This method makes stations in order
     * @return station
     * @param index list index
     */
    public static Station stationLists(int index) {
        if (index == 0)
            return LIRRSimulator.Huntington;

        else if (index == 1)
            return LIRRSimulator.Syosset;

        else if (index == 2)
            return LIRRSimulator.Hicksville;

        else //if (index == 3)
            return LIRRSimulator.Mineola;
    }

    /**
     * This method returns train display
     * @return train screen
     */
    public String toString(){
        return trainScreen;
    }
}
