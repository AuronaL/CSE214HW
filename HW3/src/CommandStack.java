import java.util.Stack;

/**
 * This is a class used for building a stack
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
import java.util.EmptyStackException;
public class CommandStack extends Stack<Command> {
    //private ArrayList<Command> stack = new ArrayList<>();

    /**
     * This is a method that push a command in the command stack
     * @param command the command we want to push in
     * @return the command we push
     * @throws iCatchUp.InvalidCommandException when command does not belong to this stack
     */
    public Command pusCommand(Command command) throws iCatchUp.InvalidCommandException {
        if(!command.validCommand(this)) throw new iCatchUp.InvalidCommandException();
      return super.push(command);
    }
    public Command pop() throws EmptyStackException{
        if (isEmpty()) throw new EmptyStackException();
        return super.pop();
    }
    public Command peek(){
        return super.peek();
    }
    public boolean isEmpty(){
        return super.isEmpty();
    }
    public String getScreenCommand(){
        return peek().toShortString();
    }

    public String toString(){
        return peek().toString();
    }
}
