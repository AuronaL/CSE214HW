/**
 * This class represents each command entered on the phone app
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public interface Command {
    /**
     * This method tells whether this command belongs to map commands or safari commands
     * @param stack current stack
     * @return true if it is map/safari commands stack
     */
    public boolean validCommand(CommandStack stack);

    /**
     * This method is the String representation of this Command in long form (for current screen display)
     * @return the String representation of this Command for current screen display
     */
    public String toString();

    /**
     * This method is the String representation of this Command in short form (for stack display)
     * @return the String representation of this Command (for stack display)
     */
    public String toShortString();
}
