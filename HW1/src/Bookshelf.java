/**
 * This class contains all the books on a shelf.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Bookshelf {
    private Book[] books; // An array holding all the books in a shelf.

    /**
     * This mehtod used for set the count.
     * @param count the number of the books
     */
    public void setCount(int count) {
        this.count = count;
    }

    private int count; // The number of books in the shelf.
    final int CAPACITY = 20; // The Maximum size of the shelf is 20.

    /**
     * This is a Constructor used to create a new bookshelf.
     * initialize its book array and number of the whole book array
     */

    public Bookshelf() {
        this.books = new Book[CAPACITY];
        this.count = 0;
    }

    /**
     * Method to get the count.
     * @return the total number of books on the shelf.
     */
    public int numBooks() {
        return count;
    }

    /**
     * This is a getter method used for get a specific book according to the index
     *
     * @param index the index related to the specific book
     * @return the values of the book
     * @throws ArrayIndexOutOfBoundsException indicates that this index is out of bounds,
     * and I throw it and catch it in the menu method.
     *
     */
    public Book getBook(int index)  {
          // largest index should be count-1
        if (index >=(count + 1)||index<0);
        return books[index];
    }

    /**
     * This removeBook() method will remove a book from the bookshelf according to its index.
     *
     * @param index the index of the book we want to remove in the bookshelf
     * @return that book we want to remove
     * @throws ArrayIndexOutOfBoundsException
     *  when the index is out of the bound.
     * @throws EmptyShelfException
     *  when the shelf is empty
     *
     */
    public Book removeBook(int index) throws ArrayIndexOutOfBoundsException, EmptyShelfException {
        if (index+1 > count|| index<0) throw new ArrayIndexOutOfBoundsException();
        if (books[index] == null) {
            throw new EmptyShelfException("This shelf is empty.");
        }
        for (int n = index; n < count; n++) {
            books[index] = books[index + 1];
        }
        count -= 1;
        return books[index];
    }

    /**
     * this is a method used for adding a new book
     *
     * @param index the index of the book we want to add in the shelf
     * @param book  the information about the book we want to add
     */
    public void addBook(int index, Book book) throws FullShelfException, IllegalArgumentException {
        //Throws an IllegalArgumentException if the index is too high and would create a hole in the array, so it must be larger than the existing number+1
        //Throws a FullShelfException if more than 20 books are added to the shelf, index is 0~19
        //when the count-1 is larger or equal to the index, we insert the book in the index place
        //when the index is equal to count, put it into the array directly
            if (index >=(count + 1)||index<0) throw new IllegalArgumentException();
            if (index > (CAPACITY - 1)) throw new FullShelfException();
            if (count - 1 >= index) {
                for (int n = count; n > index; n--) {
                    books[n] = books[n - 1];
                }
                books[index] = book;
                count += 1;
            }
            if (count == index) {
                books[index] = book;
                count += 1;
            }
            System.out.println("Book added!");
    }

    /**
     * This method is used for swap the position of two books.
     * If index is valid, swap them.
     * If index is invalid, throw ArrayIndexOutOfBoundsException;
     *
     * @param index1 one of the book we want to exchange
     * @param index2 the other of the book we want to change
     */
    public void swapBooks(int index1, int index2) throws ArrayIndexOutOfBoundsException {
        try{
        if (index1 > (numBooks() - 1)||index1<0||index1==index2) throw new ArrayIndexOutOfBoundsException();
        Book newBook = getBook(index1).clone();
        books[index1] = getBook(index2);
        books[index2] = newBook;
        }
        catch (ArrayIndexOutOfBoundsException e){ System.out.println("The index of the array is out of bound.");}
    }

    /**
     * This clone method used for clone bookshelf object.
     * a deep cope which just copied the content.
     */
    public Object clone() {
        Bookshelf newBookshelf = new Bookshelf();
        for (int i = 0; i < numBooks(); i++) {
            newBookshelf.books[i]=getBook(i).clone();
            newBookshelf.books[i].setBorrower(null);
        }
        newBookshelf.setCount(this.count);
        return newBookshelf;
    }

    /**
     * This is equal method that decide whether the content of the two bookshelfS are the same
     * @param o decide whether it is a bookshelf,
     * @return true if they equal and false if they don't.
     */
    public boolean equals(Object o) {
        if (!(o instanceof Bookshelf)) {
            return false;
        }
        Bookshelf thisO = (Bookshelf) o;
        if((thisO).numBooks()!=count){return false;}
        for (int i = 0; i < numBooks(); i++) {
            if (!(thisO).books[i].equals(getBook(i))){
                return false; };
        }
        return true;
    }

    /**
     * This is the toString method for bookshelf
     * It lists all the spot, the title of the book, the author, the condition and the borrower
     * @return string
     */
    public String toString() {
        String str = String.format("Spot Title");
        for(int i =0; i<20; i++){ str+=" ";}
        str+="Author";
        for(int i =0; i<20; i++){ str+=" ";}
        str+="Cond.";
        for(int i =0; i<20; i++){ str+=" ";}
        str+="Borrower\n";
        for(int i =0; i<90; i++){ str+="-";}
        str+="\n";
        for (int i = 0; i < count; i++) {
            str+=String.format("%d.   ", (i+1));
            str+= books[i].toString();
        }
        return str;
    }
}


