import java.util.Objects;

/**
 * This class(Book) includes the name of textbook, the author, the name of the borrower, and the condition(number) of the book.
 *
 *  @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Book {
    private String title;
    private String author;
    private String borrower;
    private int condition;

    // Setter method
    /**
     * Setter method used for setting the tile of the book
     * @param title
     * the title  of the book
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * setter method used for setting the author of the book
     * @param author
     * the author of the book
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * setter method used for setting the borrower of the book
     * @param borrower
     * the borrower of the book
     */
    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    /**
     * setter method used for setting the condition of the book
     * @param condition
     * the condition of the book
     */
    public void setCondition(int condition) {
        this.condition = condition;
    }

    // Getter method

    /**
     * getter method used for getting the title of the book
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter method used for getting the author of the book
     * @return author
     */
    public String getAuthor() {
        return author;
    }

    /**
     * getter method used for getting the borrower of the book
     * @return borrower
     */
    public String getBorrower() {
        return borrower;
    }

    /**
     * getter method used for getting the condition of the book
     * @return condition
     */
    public int getCondition() {
        return condition;
    }

    /**
     * This is a Constructor used to create a new Book object
     *
     *  @param title
     *         the title of the Book
     *  @param author
     *         the author of the Book
     *  @param condition
     *         the condition of the Book
     *  the borrower of the Book is set to empty string, as the book isn't loaned out yet
     */
    public Book(String title, String author, int condition){
        this.title=title;
        this.author=author;
        this.condition=condition;
        this.borrower=null;
    }

    /**
     * This is clone() method that clone all the data members of a book
     * not clone the borrower field
     * it is a deep clone
     */
    public Book clone(){
        Book newBook = new Book(getTitle(),getAuthor(),getCondition());
        newBook.borrower=null;
        return newBook;
    }

    /**
     * This is the equals() method used for check whether the content of two books are the same
     * decide whether object is a book
     * if it is a book, check the values of these two books
     */
    public boolean equals(Object obj){
        if(!(obj instanceof Book))
            return false;
        return (Objects.equals(((Book) obj).title, getTitle()) && Objects.equals(((Book) obj).author, getAuthor()) && ((Book) obj).condition==this.getCondition());
    }

    /** This is the toString method print out the representation of the book and its data members
     * (title, author, condition, and borrower).
     * data members + the number of spaces.
     * spaces is 25- (the length of data members).
     */
    public String toString() {
        if (borrower != null) {
            return String.format("%-25s%-25s%-25d%-25s\n", getTitle(), getAuthor(), getCondition(), getBorrower());
        } else return String.format("%-25s%-25s%-25d", title, author, condition) + "<none>\n";
    }
}
