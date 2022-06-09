
import java.util.Objects;
import java.util.Scanner;

/**
 * This class contains 3 instances and provides interface for users.
 * use a list of menu options, run eve if it is a exception
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class RipoffRental {
    private static Bookshelf shelfA = new Bookshelf();
    private static Bookshelf shelfB = new Bookshelf();
    private static Bookshelf shelfC = new Bookshelf();

    private static Bookshelf current;

    /**
     * This is a method where scan what users input to create a new book
     *
     * @return the information of the book.
     */
    public static Book inputBook(){
            Scanner in = new Scanner(System.in);
            System.out.println("Please enter a title:");
            String title = in.nextLine();
            System.out.println("Please enter an author:");
            String author = in.nextLine();
            System.out.println("Please enter condition (1-5):");
            int condition = in.nextInt();
            if(!checkCondition(condition)) throw new IllegalArgumentException();
        return new Book(title, author, condition);
    }

    /**
     * This is the method that makes sure the condition is between 1~5
     *
     * @param condition the users input the condition
     * @return True if it is 1~5; otherwise, false.
     */
    public static boolean checkCondition(int condition) {
        return condition <= 5 && condition >= 1;
    }


    /**
     * This is the method that uses for using different functions.
     *
     * @param option This is the user's option.
     */

    public static void menu(String option) {
        Scanner in = new Scanner(System.in);

        label:
        switch (option) {
            // add a book at a specific place
            case "a":
                try {
                    Book newBook = inputBook();
                    System.out.println("Please enter position on shelf:");
                    int position = in.nextInt();
                    current.addBook((position-1), newBook);
                } catch (FullShelfException e) {
                    e.printStackTrace();
                    System.out.println("\nThis shelf is full.\n");
                } catch (IllegalArgumentException E){
                    System.out.println("\nThis is an invalid condition.\n");
                } break;

                //switch the place of two books
            case "s":
                try{
                System.out.println("Please enter an index:");
                int index1 = in.nextInt()-1;
                if (index1 > (current.numBooks() - 1)||index1<0) throw new ArrayIndexOutOfBoundsException();
                System.out.println("Please enter another index:");
                int index2 = in.nextInt()-1;
                current.swapBooks(index1, index2);
                if (index2 > (current.numBooks() - 1)||index2<0||index1==index2) throw new ArrayIndexOutOfBoundsException();
                String title1 = current.getBook(index1).getTitle();
                String title2 = current.getBook(index2).getTitle();
                System.out.printf("\n%s has swapped with %s\n\n", title2, title1);}
                catch (ArrayIndexOutOfBoundsException E){ System.out.println("The index of the array is out of bound."); }
                break;

            //loan a book
            case "l":
                System.out.println("Please enter an index:");
                int index = in.nextInt()-1;
                in.nextLine();// nextInt()=nextLine()+"\n"(new line character)

                try{
                    if (index >=(current.numBooks())||index<0) throw new IllegalArgumentException();
                    System.out.println("Please enter a recipient:");
                    String recipient = in.nextLine();
                    current.getBook(index).setBorrower(recipient);
                    System.out.println("Please enter condition (1-5):");
                    int inputCondition = in.nextInt();
                    if (checkCondition(inputCondition)) {
                        current.getBook(index).setCondition(inputCondition);
                        System.out.printf("\n%s has been loaned to %s\n\n", current.getBook(index).getTitle(), recipient);
                    }
                    else System.out.println("This condition is not valid.");
                } catch(ArrayIndexOutOfBoundsException e){ System.out.println(e.toString());}
                catch (IllegalArgumentException E){System.out.println("This index is invalid. ");}
                break;

            // remove a book.
            case "r":
                try {
                    System.out.println("Please enter an index:");
                    int removeIndex = in.nextInt();
                    in.nextLine();
                    if (current.numBooks() == 0 || current.getBook(removeIndex-1)==null) throw new EmptyShelfException(" ");
                    current.removeBook(removeIndex-1);
                }  catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("\n\nThe index of the array is out of bound.\n\n");
                }
                catch (EmptyShelfException e){
                    System.out.println("\n\nThis shelf is empty.\n\n");
                }break;

                //Duplicate a Book in current shelf
            case "d":
                try {
                    System.out.println("Please enter a source index:");
                    int sourceIndex = in.nextInt();
                    if(sourceIndex-1 > current.numBooks()||sourceIndex-1<0) throw new IllegalArgumentException();
                    System.out.println("Please enter a destination index:");
                    int destinationIndex = in.nextInt();
                    //when you use addBook(), we will find the book first and then find the index
                    if(destinationIndex-1 >current.numBooks()||destinationIndex-1<0) throw new IllegalArgumentException();
                    current.addBook((destinationIndex-1), current.getBook((sourceIndex-1)).clone());
                    System.out.printf("\nA new cope of %s is in index %d\n\n", current.getBook((sourceIndex)).getTitle(), (destinationIndex));
                    break;
                } catch (FullShelfException E) {
                    System.out.println("\nOops, this shelf is already full.\n\n");
                    break;
                }catch (IllegalArgumentException E){
                    System.out.println("\nOops, this position is empty.\n");
                    break;
                }

                //change current shelf
            case "c":
                System.out.println("Please select shelf to look at:");
                String newShelf = in.nextLine();
                switch (newShelf) {
                    case "A":
                    case "a":
                        current = shelfA;
                        System.out.print("\nShelf A Selected.\n\n");
                        break;
                    case "C":
                    case "c":
                        current = shelfC;
                        System.out.print("\nShelf C Selected.\n\n");
                        break;
                    case "B":
                    case "b":
                        current = shelfB;
                        System.out.print("\nShelf B Selected.\n\n");
                        break;
                }
                System.out.println("Oops, this shelf doesn't exist.");
                break;

                //Overwrite other bookshelf with clone of the current
            case "o":
                System.out.println("Please select shelf to overwrite with the :");
                String overwriteShelf = in.nextLine();
                overwriteShelf = overwriteShelf.toUpperCase();
                String currentShelf = "0";

                if (current == shelfA) {
                    currentShelf = "A";
                }
                if (current == shelfB) {
                    currentShelf = "B";
                }
                if (current == shelfC) {
                    currentShelf = "C";
                }

                if (overwriteShelf.equals("A")) {
                    shelfA = (Bookshelf) current.clone();
                    System.out.printf("Shelf A overwritten with a copy of Shelf %s.\n\n", currentShelf);
                    break;
                }

                if (overwriteShelf.equals("B")) {
                    shelfB = (Bookshelf) current.clone();
                    System.out.printf("Shelf B overwritten with a copy of Shelf %s.\n\n", currentShelf);
                    break;
                }

                if (overwriteShelf.equals("C")) {
                    shelfC = (Bookshelf) current.clone();
                    System.out.printf("Shelf C overwritten with a copy of Shelf %s.\n\n", currentShelf);
                    break;
                }
                else {
                    System.out.println("This shelf doesn't exist.");
                }
                break;

                //Check for bookshelf equality
            case "e":
                System.out.println("Please select a shelf:");
                String a = in.nextLine();
                if(decideCurrentShelf(a)==null){System.out.println("This shelf doesn't exist.");break;}
                System.out.println("Please select another shelf:");
                String b = in.nextLine();
                if(decideCurrentShelf(b)==null){System.out.println("This shelf doesn't exist.");break;}
                if (decideCurrentShelf(a).equals(decideCurrentShelf(b))) {
                    System.out.println("\nThese shelves are equal.\n");
                    break;
                } else System.out.println("\nThese shelves are not equal.\n");
                break;

                //Print current bookshelf
            case "p":
                System.out.println();
                String printShelf = "0";

                if (current == shelfA) {
                    printShelf = "A";
                }
                if (current == shelfB) {
                    printShelf = "B";
                }
                if (current == shelfC) {
                    printShelf = "C";
                }
                System.out.printf("Bookshelf %s:\n",printShelf);
                System.out.println(current.toString());

        }
    }

    /**
     * This is a method that tells which shelf it is when user input a/b/c
     *
     * @param a is what users input
     * @return the right bookshelf it related to
     */
    public static Bookshelf decideCurrentShelf(String a) {
        a = a.toUpperCase();
        if (a.equals("A")) {
            return shelfA;
        }
        if (a.equals("B")) {
            return shelfB;
        }
        if (a.equals("C")) {
            return shelfC;
        }
        return null;
    }

    /**
     * Main method, show the menu and interact with users
     *
     * @param args simple parameter
     */
    public static void main(String[] args) {
        current = shelfA;
        System.out.println("Welcome to Jack's aMAzin' Textbook Rentals, highest price guaranteed!");
        System.out.println();
        System.out.println("Menu:\n" +
                "     A) Add Book\n" +
                "     S) Swap Books\n" +
                "     L) Loan Book\n" +
                "     R) Remove Book\n" +
                "     D) Duplicate Book\n" +
                "     C) Change Shelf\n" +
                "     O) Overwrite shelf with clone of current shelf\n" +
                "     E) Check if two shelves are equal\n" +
                "     P) Print current bookshelf\n" +
                "     Q) Quit");

        //Scanner what users input and change the books and bookshelf

        Scanner in = new Scanner(System.in);
        System.out.println("Please select an option:");
        String input = in.nextLine();
        input = input.toLowerCase();
        while (!input.equals("q")) {
            if (input.equals("a") || input.equals("s") || input.equals("l") || input.equals("r")
                    || input.equals("d") || input.equals("c") || input.equals("o") || input.equals("e")
                    || input.equals("p")) {
                menu(input);
            } else {
                System.out.println("Please input valid option.");
                System.out.println();
            }
            System.out.println();
            System.out.println("Please select an option:");
            input = in.nextLine();
            input = input.toLowerCase();
        }
        System.out.println("Goodbye! Have a nice day!");
    }
}
