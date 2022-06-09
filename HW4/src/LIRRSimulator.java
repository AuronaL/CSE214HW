import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.sym.error;

/**
 * This is a class called LIRR simulator. It should have some sort of list of stations, some list of trains currently on the line, and some global variables for number of passengers served, and other useful statistics
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class LIRRSimulator {
    static int id;
    static int lastArrivalTime=0;
    static int time=0;
    static ArrayList<Train> trains =new ArrayList<>();
    static Station Huntington;
    static Station Syosset;
    static Station Hicksville;
    static Station Mineola;
    static String[] stations={"Mineola", "Hicksville", "Syosset", "Huntington"};
    // !! index 0~3 -> 4 is out of boundary

    public static void main(String[] args){

        System.out.println("Welcome to the LIRR Simulator, Leaving Irate Riders Regularly!\n");
        Scanner scanner = new Scanner(System.in);
        try {
            //setting
            // station probability
            double in1;
            double in2;

            for (int k = 0; k < 4; k = k + 1) {
                System.out.println(stations[k] + ": ");
                System.out.println("Please enter first class arrival probability:");
                in1 = scanner.nextDouble();
                while(in1<0.0|| in1>1.0){
                    System.out.println(" Please enter only integer numbers that between 0 and 1. ");
                    in1 = scanner.nextDouble();
                }
                System.out.println("Please enter second class arrival probability:");
                in2 = scanner.nextDouble();
                while(in2<0.0|| in2>1.0){
                    System.out.println(" Please enter only integer numbers that between 0 and 1. ");
                    in2 = scanner.nextDouble();
                }
                switch (k) {
                    case 0:
                        Mineola = new Station(in1, in2);
                        break;
                    case 1:
                        Hicksville = new Station(in1, in2);
                        break;
                    case 2:
                        Syosset = new Station(in1, in2);
                        break;
                    case 3:
                        Huntington = new Station(in1, in2);
                        break;
                }
                System.out.println();
            }

//            System.out.println("Please enter first class arrival probability:");
//            double in1 = scanner.nextDouble();
//            System.out.println("Please enter second class arrival probability:");
//            double in2 = scanner.nextDouble();
//            stationList(k)=new Station(in1,in2);

            //train setting
            System.out.println("Please enter first class capacity: ");
            int cap1 = scanner.nextInt();
            System.out.println("Please enter second class capacity: ");
            int cap2 = scanner.nextInt();
            System.out.println("Please enter number of trains: ");
            int trainNumber = scanner.nextInt();
            for (int r = 0; r < trainNumber; r++) {
                Train a = new Train(cap1, cap2, 5 * r);
                trains.add(r,a);
            }

            //last arrival time
            System.out.println("Please enter last arrival time of passengers:");
            lastArrivalTime = scanner.nextInt();
            while (lastArrivalTime>5*(trainNumber-1)+15){
                System.out.println("Time is after last station arrival of last train. \n" +
                        "Please try again(last arrival time of passengers:): ");
                lastArrivalTime = scanner.nextInt();
            }
            if(lastArrivalTime>5*(trainNumber-1)+15) throw new IllegalArgumentException(String.valueOf(error));
                // exception!! when the time is after last station arrival of last train


            //simulation
            System.out.println("\nBegin Simulation:\n" +
                                "---------------------------------------------");
            //time loop
            while (time<=15+5*(trainNumber-1)){
            System.out.println("Time " + time + ":\n" + "\nStation overview:\n");

            //every single time display

            // station overview
            for (int i = 0; i < 4; i++) {
                if(LIRRSimulator.time <= LIRRSimulator.lastArrivalTime){
                    stationList(i).simulateTimeStep();
                }//stop renew when time>last arrival time
                else System.out.println("No more passenger arrivals at this time.");
                System.out.println(stations[i] + ": " + "\n" + stationList(i).toString());
            }
            // Trains overview
            System.out.println("\nTrains:" + "\n");
            for (int i=0; i<trainNumber;i++){
                trains.get(i).simulateTimeStep();
                System.out.println("Train "+(i+1)+" "+trains.get(i).trainScreen);
            }

            //time renew
            time++;
            // dividing line
                System.out.println("-------");}

            //Summery
            int served =0;
            int firstLeft =0;
            int secondLeft =0;
            //train passenger queue -> served
            for(Train train: trains){
            served+= (train.getFirstClassNumber()+train.getSecondClassNumber()+train.getFirstCin2ndC());
            }
            //station passenger queue-> left
            for (int i=0; i<4; i++){
                firstLeft+=stationList(i).getFirstClassLength();
                secondLeft+=stationList(i).getSecondClassLength();
            }
            // total overview
            System.out.println("\nAt the end of the simulation:\n" +
                    "\n" +
                    "A total of "+served+" passengers were served, "+firstLeft+" first class passengers were left without a seat, "
                    +secondLeft+" second class passengers were left without a seat.\n" );

            // station overview
            for(int i=0; i<4; i++){
                int m=0;
                if(stationList(i).getFirstServed()!=0)
                    m=stationList(i).getFirstWaitTime()/stationList(i).getFirstServed();

                int n=0;
                if(stationList(i).getSecondServed()!=0)
                    n=stationList(i).getSecondWaitTime()/stationList(i).getSecondServed();
                System.out.println("At "+stations[i] +
                        " "+stationList(i).getFirstServed()+" first class passengers were served with an average wait time of "
                        +m+" min, \n"
                        +stationList(i).getSecondServed()+" second class passengers were served with an average wait time of "
                        +n+" min. \n"
                        +stationList(i).getFirstClassLength() +" first class passengers and "+
                        stationList(i).getSecondClassLength() +" second class passengers were left without a seat.\n");
            }
    } catch (InputMismatchException e){
        System.out.println("please enter only integer numbers.");}
        catch (IllegalArgumentException E){System.out.println("Time is after last station arrival of last train. ");}
    }
    /**
     * This method makes stations in order
     * @return station
     * @param index list index
     */
    public static Station stationList(int index) {
        switch (index) {
            case 0:
                return Mineola;
            case 1:
                return Hicksville;
            case 2:
                return Syosset;
            //case 3:
        }
        return Huntington;
    }
}
