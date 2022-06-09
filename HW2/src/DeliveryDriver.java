import java.util.Scanner;

import static javafx.application.Platform.exit;

/**
 * This class creates two instances of the DeliveryList class
 * and provides an interface for a user to manipulate the list by adding, and removing Deliveries.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class DeliveryDriver {
    private static Delivery temp = null;
    private static DeliveryList one = new DeliveryList();
    private static DeliveryList two =new DeliveryList();
    static DeliveryList thisDeliveryList =one;


    // Next time please test one method each time
    public static void main(String[] args) {
        System.out.println("Welcome to the Delinquent Dollar Delivery Scheduler.\n" +
                "\n");
        System.out.println("Menu:\n" +
                "\n" +
                "     A) Add a Delivery After Cursor\n" +
                "\n" +
                "     R) Remove Delivery At Cursor\n" +
                "\n" +
                "     X) Cut Cursor\n" +
                "\n" +
                "     V) Paste After Cursor\n" +
                "\n" +
                "     H) Cursor to Head\n" +
                "\n" +
                "     T) Cursor to Tail\n" +
                "\n" +
                "     F) Cursor Forward\n" +
                "\n" +
                "     B) Cursor Backward\n" +
                "\n" +
                "     S) Switch Delivery Lists\n" +
                "\n" +
                "     P) Print current list\n" +
                "\n" +
                "     Q) Quit\n" +
                "\n" +
                "\n" +
                "Please select an option:");

        Scanner in= new Scanner(System.in);//in is what you are scanning but give it a data type!
        String option = in.nextLine().toLowerCase(); // in.next() = in.nextLine()+"\n"

        while(!option.equals("q")) {

            switch (option) { // switch: 's' must be lowercase!!!

                //Add Delivery After Cursor
                case "a":
                    System.out.println("Please enter a source:");
                    String source = in.nextLine();
                    if (validInput(source)) {
                        System.out.println("This source is invalid, please try again~\n");
                        continue;//can not get out the case so start with the first sentence of theis case
                    }
                    System.out.println("Please enter a destination:");
                    String dest =in.nextLine();
                    if (validInput(dest)) {
                        System.out.println("This destination is invalid, please try again~\n");
                        continue;
                    }
                    System.out.println("Please enter any special instructions:");
                    String instruction =in.nextLine();

                    if (validInput(instruction)) {
                        System.out.println("This instruction is invalid, please try again~\n");
                        continue;
                    }

                    Delivery order = new Delivery(source,dest,instruction); // return an Object then cast the right data type!!
                    thisDeliveryList.insertAfterCursor(order);
                    System.out.println("\nOrder inserted." +
                            "\n");
                    break;

                //Cursor to Head
                case "h":
                    thisDeliveryList.resetCursorToHead();
                    System.out.println("\nCursor is at head." +
                            "\n");
                    break;

                //Cursor to Tail
                case "t":
                    thisDeliveryList.resetCursorToTail();
                    System.out.println("\nCursor is at tail." +
                            "\n");
                    break;

                //Cursor Forward
                case "f":
                    try{
                    thisDeliveryList.cursorForward();
                    System.out.println("Cursor moved forward.\n");}
                    catch (EndOfListException e){
                        System.out.println("\nCursor is already the end of the list.\n");
                    }
                    break;

                //Cursor Backward
                case "b":
                    try{
                        thisDeliveryList.cursorBackward();
                        System.out.println("Cursor moved backward.\n");
                    }
                    catch (EndOfListException e){
                        System.out.println("\nCursor is already the head of the list.\n");
                    }
                    break;

                //Remove Cursor
                case "r":
                    try {
                        if(thisDeliveryList.getCursor()==null) throw new EndOfListException();
                        // cursor changed after removed !! Save the name first!
                        String removeCursor = thisDeliveryList.getCursor().getDest();
                        thisDeliveryList.removeCursor();
                        System.out.printf("Delivery to %s removed.\n",removeCursor);

                    } catch (EndOfListException e) {
                        System.out.println("\nThis cursor is null.\n");
                    }
                    break;

                //Cut Cursor
                case "x":
                    try{
                        temp = thisDeliveryList.removeCursor();
                        System.out.println("Cursor is cut.\n");
                    }catch (EndOfListException E){
                        System.out.println(" Cursor is null.\n");
                    }
                    break;

                //Paste After Cursor
                case "v":
                    try{
                        thisDeliveryList.insertAfterCursor(temp);
                        StringBuilder outPrint = new StringBuilder(name());
                        for(int i=0; i<25; i++){ //25
                            outPrint.append("-");
                        }
                        outPrint.append("\n->\n");
                        outPrint.append(temp.toString());
                        for(int i=0; i<2; i++){ //25
                            outPrint.append("-");
                        }
                        outPrint.append("\n");
                        System.out.println(outPrint);
                        break;

                    } catch (IllegalArgumentException e) {
                        System.out.println("Temp is null. Please cut cursor first.\n");
                    }
                    break;

                //Switch Delivery Route
                case "s":
                   if (thisDeliveryList==one){
                       thisDeliveryList=two;
                       System.out.println("Money Mike's list is selected.\n");
                   }
                   else {
                       thisDeliveryList=one;
                       System.out.println("Biz Billy's list is selected.\n");
                   }
                   break;

                //Print current list
                case "p":
                    StringBuilder outPrint = new StringBuilder("\n"+name());
                    if(thisDeliveryList.getHead()==null){
                        if(thisDeliveryList==two){
                        System.out.println("Oops, Money Mike's Deliveries is null.\n");}
                        else System.out.println("Oops, Biz Billy's Deliveries is null.\n");
                        break;
                    }
                    for(int i=0; i<25; i++){ //25
                        outPrint.append("-");
                    }
                    outPrint.append("\n");

                    DeliveryListNode node = thisDeliveryList.getHead();
                    if (thisDeliveryList.getHead()!=null){
                    while (node!=thisDeliveryList.getTail()&&thisDeliveryList.numDeliveries()!=1){ // add tail at last
                    //for(int i=0; i<thisDeliveryList.numDeliveries();i++){ //this still split tail as tail.grtNext=null
                        outPrint.append(thisDeliveryList.toString(node));
                        node=node.getNext();
                    }
                    outPrint.append(thisDeliveryList.toString(thisDeliveryList.getTail())+"\n");
                    }

                    for(int i=0; i<25; i++){ //25
                        outPrint.append("-");
                    }
                    outPrint.append("\n");
                    System.out.println(outPrint);
                    break;

            }

            if(!option.equals("a")&&!option.equals("h")&&!option.equals("t")&&
                    !option.equals("f")&&!option.equals("b")&&!option.equals("r")&&
                    !option.equals("x")&&!option.equals("v")&&!option.equals("s")&&!option.equals("p")){
                System.out.println("This option is invalid, please try again~\n");
            }

            System.out.println("Please select an option:");
            option = in.nextLine().toLowerCase();

        }
        System.out.println("Goodbye! Have a nice day!");
        exit();// Method after the main method will not run
    }

    /**
     * This method is used for scanning space input
     *
     * @param a string that users input
     * @return true only when it is not space
     */
    public static boolean validInput(String a){
        return a.trim().length() == 0;
        // string: .trim() ->Removes whitespace from both ends of a string
        //it returns false if the input is space
    }

    /**This is a method tells me whose delivery list it is
     *
     * @return name of the owner
     */
    public static String name(){
        if(thisDeliveryList==two){
            return "Money Mike's Deliveries:\n";
        }
        else return "Biz Billy's Deliveries:\n";
    }


}
