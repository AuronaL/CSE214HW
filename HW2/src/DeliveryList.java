/**
 * This class implements a double linked-list data structure.
 * maintain a list of Deliveries by chaining a series of DeliveryListNodes between a head and a tail reference.
 * traverse the list, selecting individual DeliveryListNodes to allow for insertion, deletion, and manipulation of the Deliveries they contain.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class DeliveryList {

    /**
     * This method used for getting head of the list
     * @return head
     */
    public DeliveryListNode getHead() {
        return head;
    }

    private DeliveryListNode head;
    private DeliveryListNode tail;
    private DeliveryListNode cursor;
    private int sum;

    /**
     * This method used for getting tail of the list
     * @return tail
     */
    public DeliveryListNode getTail() {
        return tail;
    }

    /**
     * This constructor initializes this object to an empty list of Deliveries.
     * initializes with head, tail, cursor and sum all set to null.
     */
    public DeliveryList(){
        head=null;
        tail=null;
        cursor=null;
        sum=0;
    }

    /**
     * Get the total number of deliveries in the list
     * @return total number of deliveries
     *
     */
    public int numDeliveries(){
        return sum;
    }

    /**
     * This method used for getting the reference of delivery by the cursor
     * reference is wrapped by the DeliveryListNode currently
     * If the cursor is null, return null  (i.e. the cursor does not reference a Delivery).
     *
     * @return reference of the delivery
     */
    public Delivery getCursor(){
        if(cursor==null)
            return null;
        return cursor.getData();
    }

    /**
     * This method returns the cursor to the start of the list
     */
    public void resetCursorToHead(){
            cursor=head;//? null
        //if head==null, there are no Deliveries in this list

    }

    /**
     * This method returns the cursor to the end of the list
     */
    public void resetCursorToTail(){
        cursor=tail;
        //if tail==null, there are no Deliveries in this list

    }

    /**
     * This method moves cursor to select the next DeliveryListNode in the list.
     * ?
     * ??what if cursor is null??? should it be different? like this current is null
     *
     * @throws EndOfListException
     * the cursor is the tail of the list
     */
    public void cursorForward() throws EndOfListException{
        if (cursor==tail) throw new EndOfListException(); // cursor is at the tail of the list.
        cursor=cursor.getNext();
    }

    /**
     * This method moves the cursor to select the previous
     * @throws EndOfListException
     * the cursor is at the beginning of the list
     */
    public void cursorBackward() throws EndOfListException {
        if (cursor==head) throw new EndOfListException(); // cursor is at the head of the list.
        cursor=cursor.getPrev();
    }

    /**
     * This is a method create a new head
     * initialize everything except for sum as its default value is 0 so no need to do that.
     * head = this node;
     * tail = this node;
     * cursor = this node;
     *
     * @param afterCursor
     * this is the data of this new head
     */
    public void createNewHead(DeliveryListNode afterCursor){
        head=afterCursor;
        tail=afterCursor;
        cursor=afterCursor;
    }

    /**
     * This method is insert a node contains given delivery data after the cursor
     *
     * doubly linked list: prev.Next =after ; after.prev=before
     * newDelivery has been wrapped in a new DeliveryListNode object.
     * cursor was not null -> insert it after the node
     * cursor was null -> new head
     *
     * @param newDelivery
     * the data we want to insert
     * @throws IllegalArgumentException
     * the data of new delivery is null that means it does not exist.
     */
    public void  insertAfterCursor(Delivery newDelivery) throws IllegalArgumentException{
        if(newDelivery==null) throw new IllegalArgumentException();
        DeliveryListNode afterCursor = new DeliveryListNode(newDelivery);
        if(cursor==tail&&cursor!=null){
            appendToTail(newDelivery);// endpoint cursor = head or tail, they all update tail
            // You can use method appendTail(), but remember delete extra sum+1
            sum-=1;
        }
        else if(cursor==null){ // always think about it first
            createNewHead(afterCursor);
        }
        else{
            //!reminder: doubly linked list-> two nodes; 4 connections
            cursor.getNext().setPrev(afterCursor);
            afterCursor.setNext(cursor.getNext());

            cursor.setNext(afterCursor);// prev.next=after ; after.prev=before
            afterCursor.setPrev(cursor);
            //These two only can use set() method because data is private.

        }
        sum+=1;
    }

    /**
     * This method inserts the indicated Delivery after the tail of the list.
     * If tail is null, create new head.
     * If tail is not null, add new node after tail.
     *
     * @param newDelivery
     * given data for new delivery.
     * @throws IllegalArgumentException
     * The new delivery is null that means it doesn't exist.
     */
    public void appendToTail(Delivery newDelivery) throws IllegalArgumentException{
        if(newDelivery==null) throw new IllegalArgumentException();
        DeliveryListNode afterTail= new DeliveryListNode(newDelivery);
        if(tail==null){
           createNewHead(afterTail);
        }
        else if (tail==head){
            head.setNext(afterTail);
            afterTail.setPrev(head);
            tail=afterTail;
        }
        else{
            tail.setNext(afterTail);
            afterTail.setPrev(tail);
            tail=afterTail;// when it becomes tail, update tail!!!
        }
        sum+=1;
    }

    /**
     * This method removes the DeliveryListNode referenced by cursor and returns the Delivery inside.
     * the beforeCursorNode.Next =afterCursorNode //except head & tail
     * the afterCursorNode.prev =beforeCursorNode
     * sum-1
     * cursor=cursor.Next
     *
     * @return Delivery
     * the data of this delivery
     * @throws EndOfListException
     * this cursor is null
     */
    public Delivery removeCursor() throws EndOfListException {
        if(cursor==null) throw new EndOfListException();
        Delivery theOne = cursor.getData();
        if(numDeliveries()==1){
            cursor=null;
            head=null;
            tail=null;
        }
        else {
            if (cursor == head) {
                head = cursor.getNext();
                cursor = cursor.getNext();
                head.setPrev(null); //when the next become head, we must set head.prev=null!
            }
            else if (cursor == tail) {
                tail = cursor.getPrev();
                cursor = tail;
                tail.setNext(null); //when the prev become tail, we must set tail.Next = null!
                }
            else {
                //doubly linked
                cursor.getPrev().setNext(cursor.getNext());
                cursor.getNext().setPrev(cursor.getPrev());
                cursor = cursor.getNext();
            }
        }
        sum-=1;
        return theOne;
    }

    public String toString(DeliveryListNode node){
        if (node==cursor){
            return String.format("->\n%s",cursor.getData().toString());
        }
        else if(node==head){
            return String.format("%s",head.getData().toString());
            //head.toString() printout:  DeliveryListNode@27716f4 --> cursor/head/tail is the address of Node!!
            //head.getData() --> delivery
        }
        else return String.format("~\n%s", node.getData().toString());
    }
}

