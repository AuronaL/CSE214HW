import java.util.Scanner;

/**
 *This is a class represent the “G: Google something” command for the Application.Safari app.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class GoogleSomething implements Command{
    private String query;

    /**
     * Getter for query
     * @return query
     */
    public String getQuery() {
        return query;
    }

    /**
     * Constructs this GoogleSomething instance accordingly after reading input from the scanner.
     * @param scanner input from the scanner
     */
    public GoogleSomething (Scanner scanner){
        query=scanner.nextLine();
    }

    /**
     * whether pushing this GoogleSomething command will be valid for the given stack.
     * @param stack current stack
     * @return true
     */
    @Override
    public boolean validCommand(CommandStack stack) {
        return iCatchUp.screen == 2;
    }

    /**
     * Returns the String representation of this Command in long form (for current screen display)
     * @return current screen display
     */
    public String toString(){
        return "Google: "+ query;
    }

    /**
     *Returns the String representation of this Command in short form (for stack display)
     * @return stack display
     */
    @Override
    public String toShortString() {
        return "G:"+query;
    }

    /**
     * a class named GoogleSomething.FollowLink to represent the “L: GoogleSomething.FollowLink” command for the Application.Safari app.
     *
     * @ author: Aurona Liu
     * Recitation: R01
     * student id: 114138778
     * email: jiyun.liu@stonybrook.edu
     */
    public static class FollowLink implements Command{
        /**
         * Getter link
         * @return input link
         */
        public String getLink() {
            return link;
        }

        private String link;

        /**
         * Constructs this GoogleSomething.FollowLink instance accordingly after reading input from the scanner.
         * @param scanner input from the scanner
         */
        public FollowLink (Scanner scanner){
            link=scanner.nextLine();
        }

        /**
         * Returns whether pushing this GoogleSomething.FollowLink command will be valid for the given stack.
         * @param stack current stack
         * @return true
         */
        @Override
        public boolean validCommand(CommandStack stack) {
            return iCatchUp.screen == 2;
        }

        /**
         * Returns the String representation of this Command in long form (for current screen display)
         * @return screen display
         */
        public String toString(){
            return link;
        }

        /**
         * Returns the String representation of this Command in short form (for stack display)
         * @return stack display
         */
        @Override
        public String toShortString() {
            return "L:"+ link;
        }
    }

    /**
     *This is a class represent the “F: Go to favorite/bookmark” command for the Application.Safari app
     *
     * @ author: Aurona Liu
     * Recitation: R01
     * student id: 114138778
     * email: jiyun.liu@stonybrook.edu
     */
    public static class GoToBookmark implements Command{
        /**
         * Getter bookmark
         * @return bookmark
         */
        public String getBookmark() {
            return bookmark;
        }

        private String bookmark;

        /**
         * Constructs this GoogleSomething.GoToBookmark instance
         * @param scanner input from the scanner
         */
        public GoToBookmark (Scanner scanner){
            bookmark=scanner.nextLine();
        }

        /**
         * whether pushing this GoogleSomething.GoToBookmark command will be valid for the given stack.
         * @param stack current stack
         * @return true
         */
        @Override
        public boolean validCommand(CommandStack stack){
            return iCatchUp.screen == 2;
        }

        /**
         * Returns the String representation of this Command in long form (for current screen display)
         * @return current screen display
         */
        public String toString(){
            return "Showing results for "+ bookmark;
        }

        /**
         * Returns the String representation of this Command in short form (for stack display)
         * @return stack display
         */
        @Override
        public String toShortString() {
            return "F:"+bookmark;
        }

    }
}
