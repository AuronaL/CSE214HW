import java.util.Arrays;
import java.util.Scanner;

/**
 * This is a class used for application
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public abstract class Application {
    private CommandStack stack;

    /**
     * Reads in input from the scanner to construct a Command and add it to the CommandStack.
     *
     * @param scanner users input
     * @throws iCatchUp.InvalidCommandException if the Command is invalid given the current state of the stack
     */
    public void readCommand(Scanner scanner) throws iCatchUp.InvalidCommandException, iCatchUp.EmptyStackException {
    }

    ;

    /**
     * This method returns the application to state it was before the most recent command
     *
     * @throws iCatchUp.EmptyStackException stack is empty before the recent command
     */
    public void goBack() throws iCatchUp.EmptyStackException {
    }


}