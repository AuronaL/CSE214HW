import java.util.Scanner;

/**
 *This is a class represent the “F: Find a place” command for the Application.Maps app.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class FindPlace implements Command {
    private String destination;

    /**
     * Getter method to get destination
     * @return destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * This is a constructor initialize destination with scanner
     * @param scanner what user input
     */
    public FindPlace(Scanner scanner){
        destination=scanner.nextLine();
    }


    /**
     * This method tells whether is command is belongs to map commands
     * @param stack current stack
     * @return true if it is map commands stack
     */
    public boolean validCommand(CommandStack stack) {
        return iCatchUp.screen == 1;
    }

    /**
     * This method is the String representation of this Command in long form (for current screen display)
     * @return the String representation of this Command for current screen display
     */
    public String toString(){
        return "Showing results for " + destination;
    }

    /**
     * This method is the String representation of this Command in short form (for stack display)
     * @return the String representation of this Command (for stack display)
     */
    @Override
    public String toShortString() {
        return "F:" + destination;
    }

    /**
     * represent the “P: Plan a route” command for the Application.Maps app
     *
     * @ author: Aurona Liu
     * Recitation: R01
     * student id: 114138778
     * email: jiyun.liu@stonybrook.edu
     */

    public static class PlanRoute implements Command{
        private String source;
        private String destination;

        /**
         * Getter method for getting source
         * @return source
         */
        public String getSource() {
            return source;
        }

        /**
         * Getter method for getting destination
         * @return destination
         */
        public String getDestination() {
            return destination;
        }

        /**
         * This is a constructor that initialize source and destination
         * @param scanner input form the user
         */
        public PlanRoute(Scanner scanner){
            System.out.println("Please enter a source:");
            String enterSource = scanner.nextLine();
            if(enterSource.length()!=0)
                source = enterSource;

            System.out.println("Please enter a destination:");
            String enterDest=scanner.nextLine();
            if(enterDest.length()!=0)
                destination = enterDest;
        }

        /**
         * This method tells if it belongs to Map stack
         * @param stack current stack
         * @return true if is belongs to map commands stack
         */
        @Override
        public boolean validCommand(CommandStack stack) {
            return iCatchUp.screen == 1;
        }

        /**
         *This method used for String representation of this Command in long form (for current screen display)
         * @return this FindPlace.PlanRoute (for current screen display)
         */
        public String toString() {
            return "Planning route from "+source+" to "+destination;
        }


         /**
         * This method is the String representation of this Command in short form (for stack display)
         * @return the String representation of FindPlace.PlanRoute (for stack display)
         */
        @Override
        public String toShortString(){
            return "P:"+source+"-"+destination;
        }
    }

    /**
     * This class represents the “N: Start Navigation” command for the Application.Maps app.
     * @ author: Aurona Liu
     * Recitation: R01
     * student id: 114138778
     * email: jiyun.liu@stonybrook.edu
     */
    public static class StartNavigation implements Command {
        private String source;
        private String destination;

        /**
         * Constructor for initialize source and destination
         * @param commandStack current command stack
         */
        public StartNavigation (CommandStack commandStack){
            Command command = commandStack.peek();
            if (command instanceof PlanRoute){
                source =((PlanRoute) command).getSource();
                destination=((PlanRoute) command).getDestination();
            }
            if (command instanceof FindPlace){
                destination = ((FindPlace) command).getDestination();
            }
        }

        /**
         * This method tells whether this command belongs to map commands or safari commands
         * @param stack current stack
         * @return true if it is map/safari commands stack
         */
        @Override
        public boolean validCommand(CommandStack stack) {
            return iCatchUp.screen == 1;
        }


        /**
         * String representation of this Command in long form (for current screen display)
         * @return this command for current screen display
         */
        public String toString(){
            if(source==null&&destination==null){
                return "No route or destination!";
            }
            if(source==null){
                return "Navigating to "+ destination ;
            }

            return "Navigating from "+ source+" to "+destination;
        }

        /**
         * This method represents of this Command in short form (for stack display)
         * @return this command for stack display
         */
        @Override
        public String toShortString() {
            if (source==null) {
                return "N:" + destination;
            }
                return "N:"+ source +"-"+destination;
        }
    }
}
