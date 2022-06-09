/**
 * This is a class used for empty bookshelf exception
 * If we catch an exception, we throw out a sentence
 */


public class EmptyShelfException extends Exception{
    public EmptyShelfException(String error){
        super(error);
    }
}
