/**
 * This is a class called PassengerQueue that has all the regular queue methods.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
import java.util.ArrayList;
public class PassengerQueue extends ArrayList<Passenger> {
    // arraylist remove and shift to left automatically
private int rear;
private int front;

    /**
     * Constructor for creating a new passenger queue;
     *
     */
    public PassengerQueue() {
       // ArrayList<Passenger> passengerQueue = new ArrayList<>();
        // when you extend you already have an arraylist
        front=-1;
        rear=-1;
    }

    /**
     * This is method called enqueue that enter the message of a passenger
     * @param p passenger
     *
     * don't know the capacity, so check it in the train
     */
    public void enqueue(Passenger p) {
        //if ((rear+1)%capacity==front)  (rear+1)%capacity the next item throw new FullQueueException();
        if(isEmpty()){
            rear=0;
            front=0;
        }
        else rear = rear+1;

        this.add(p);
        //if arraylist is null, you can never add a new item!!!???
        // difference between this/ passengerQueue

    }

    /**
     * This is the method that remove a passenger
     * @return passenger that leave
     */
    public Passenger dequeue() {
        // if (isEmpty()) throw new EmptyQueueException();
        Passenger delete = peek();
        this.remove(0);//front

        if(rear==front){
            front=-1;
            rear=-1;
        }
        rear=rear-1;
        //else front=front+1; front is always the same 0
        // (front+1) % capacity is next one
        return delete;
    }

    /**
     * Method for toString
     * @return string
     */
    public String toString(){
        StringBuilder A = new StringBuilder("[");
        if(isEmpty()){
            A.append("empty]");
        }
        else {
            for (Passenger p : this) {
                A.append(p.toString()).append(", ");
            }
            A.deleteCharAt(A.length()-1);
            A.deleteCharAt(A.length()-1);// These two equal -> A.replace(A.length()-2, A.length()-1,"")
        }
        return A.toString();
    }

    /**
     * This method return a list of embarking passengers
     * @return passengers
     */
    public String dequeueString(){
        StringBuilder A = new StringBuilder();
        if(this.isEmpty())
            return "none";
        for (Passenger passenger : this) {
            A.append("P").append(passenger.getId()).append(", ");
        }
        A.replace(A.length()-2, A.length()-1,"");
        return A.toString();
    }
    /**
     * This method returns the front passenger of the queue
      * @return front passenger
     */
    public Passenger peek(){
        return this.get(0);
    }

    /**
     * This method returns the rear passenger of the queue
     * @return rear passenger
     */
    public Passenger peekRear(){
        return this.get(size()-1);
    }

    /**
     * This is a method tells whether the queue is empty
      * @return true if is empty
     */
    public boolean isEmpty(){
        return front==-1;
    }

}
