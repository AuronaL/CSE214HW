/**
 *This class wraps a Delivery object
 * contain the reference of data, two DeliveryNode references serving as 'links' to the previous and next DeliveryListNodes.
 * getter and setter methods
 *
 */
public class DeliveryListNode {
    private Delivery data;
    private DeliveryListNode next;
    private DeliveryListNode prev;

    /**
     * Getter method used for getting the address of previous node.
     * @return previous node address
     */
    public DeliveryListNode getPrev() {
        return prev;
    }

    /**
     * setter method used for setting the address of previous node
     * @param prev
     * previous node address
     */
    public void setPrev(DeliveryListNode prev) {
        this.prev = prev;
    }

    /**
     * Getter method used for getting the data.
     * @return data of this delivery
     */
    public Delivery getData() {
        return data;
    }


    /**
     * setter method used for setting the data
     * @param data
     * data of this delivery
     */
    public void setData(Delivery data) {
        this.data = data;
    }


    /**
     * Getter method used for getting the address of next node.
     * @return next node address
     */
    public DeliveryListNode getNext() {
        return next;
    }

    /**
     * setter method used for setting the address of next node.
     * @param next
     * the address of next node.
     */
    public void setNext(DeliveryListNode next) {
        this.next = next;
    }

    /**
     * This is a constructor that initializes data to input data; next node and previous node is null.
     * @param initData
     * the information of this delivery
     *
     * Default constructor.
     * Preconditions:
     * initData is not null.
     * PostConditions:
     * This DeliveryListNode has been initialized to wrap the indicated Delivery, and prev and next have been set to null.
     * @throws IllegalArgumentException
     * IllegalArgumentException if initData is null.
     */
    public DeliveryListNode(Delivery initData) throws IllegalArgumentException{
        if(initData==null)
            throw new IllegalArgumentException();
        data=initData;
        next=null;
        prev=null;

        //catch (IllegalArgumentException E){System.out.println("Oops, the initial data is null, please try again. ");}
    }
}
