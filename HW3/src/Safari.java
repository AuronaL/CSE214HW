import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is the application for Safari
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Safari extends Application{
    private CommandStack safariStack = new CommandStack();
    private int nullInput =0;
    private String stackDisplay="Stack Debug:\n" +
            "[Home->SafariHome";
    private String screenDisplay="Current Screen: Safari Home";
    //initialization is called constructor
    private final String[] mapCommands = new String[]{"g", "f", "l", "h","s","b"};

    /**
     * Getter for safari stack
     * @return safari stack
     */
    public CommandStack getSafariStack() {
        return safariStack;
    }

    /**
     * Getter for stack display
     * @return stack display
     */
    public String getStackDisplay() {
        return stackDisplay;
    }

    /**
     * Setter for screen display
     * @param screenDisplay screen display
     */
    public void setScreenDisplay(String screenDisplay) {
        this.screenDisplay = screenDisplay;
    }

    /**
     * Getter to get screen display
     * @return screen display
     */
    public String getScreenDisplay() {
        return screenDisplay;
    }
    /**
     * This method reads in input from the scanner to construct a Command and add it to the CommandStack.
     * @param scanner users input
     * @throws iCatchUp.InvalidCommandException when this command
     */
    public void readCommand(Scanner scanner) throws iCatchUp.InvalidCommandException, iCatchUp.EmptyStackException {

        safariMenu();

        String command = scanner.nextLine().toLowerCase();
        if (!Arrays.asList(mapCommands).contains(command)) throw new iCatchUp.InvalidCommandException();

        switch (command) {
            //command Google Something
            case "g":
                System.out.println("Please enter a query:");
                GoogleSomething G = new GoogleSomething(scanner);
                if(G.getQuery()!=null){
                    safariStack.pusCommand(G);
                }
                else nullInput=1;
                break;

            //command Follow a link
            case "l":
                System.out.println("Please enter a link:");
                GoogleSomething.FollowLink F = new GoogleSomething.FollowLink(scanner);
                if (F.getLink() != null)
                    safariStack.pusCommand(F);
                else nullInput = 1;
                break;

            //Go to a favorite (bookmark)
            case "f":
                System.out.println("Please enter bookmark name:");
                GoogleSomething.GoToBookmark K = new GoogleSomething.GoToBookmark(scanner);
                safariStack.pusCommand(K);
                break;

            //switch
            case "s":
                iCatchUp.screen = 1;
                iCatchUp.switchScreen=true;
                break;

            //home
            case "h":
                iCatchUp.screen = 0;
                break;

                //back
            case "b":
                goBack();
                break;
        }
        if (nullInput == 0) {
            if(!command.equals("b") && iCatchUp.screen==2){
                screenDisplay = safariStack.toString();
                stackDisplay += "->" + safariStack.getScreenCommand();}
        }
        nullInput = 0;
        if(iCatchUp.screen==2)
            if(safariStack.isEmpty()){
                System.out.println(stackDisplay + "\n"  +screenDisplay + "\n");
            }else System.out.println(stackDisplay + "\n"  + "Current Screen: "+ screenDisplay + "\n");

    }


    /**
     * This method returns the application to state it was before the most recent command
     * @throws iCatchUp.EmptyStackException stack is empty before the recent command
     */
    public void goBack() throws iCatchUp.EmptyStackException {
        if(safariStack.isEmpty()) throw new iCatchUp.EmptyStackException();// go Home screen
        int y = stackDisplay.indexOf(safariStack.getScreenCommand());
        stackDisplay=stackDisplay.substring(0,y-2);
        safariStack.pop();
        if(safariStack.isEmpty()){
            screenDisplay="Current Screen: Safari Home"; // empty->peek is null -> needs initialize
        } else screenDisplay=safariStack.peek().toString();
    }

    /**
     * This method returns maps menu
     * @return maps menu
     */
    public void safariMenu(){
        System.out.println(
                "Safari Options:\n" +
                        "     G) Google Something\n" +
                        "     F) Go to a favorite (bookmark)\n" +
                        "     L) Follow a link\n" +
                        "     H) Home Screen\n" +
                        "     S) Switch to Maps\n" +
                        "     B) Back" +
                        "\n" +
                        "Please select an option: ");
    }
}
