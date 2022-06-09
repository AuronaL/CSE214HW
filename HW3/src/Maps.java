import java.util.Arrays;
import java.util.Scanner;

/**
 * This class is the application for maps
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Maps extends Application {
    private CommandStack mapStack = new CommandStack();
    private int nullInput = 0;
    private String stackDisplay = "Stack Debug:\n" +
            "[Home->MapsHome";
    private String screenDisplay = "Current Screen: Maps Home";
    //initialization is called constructor
    private final String[] mapCommands = new String[]{"f", "p", "n", "s", "h", "b"};

    /**
     * Getter to get map stack
     *
     * @return map stack
     */
    public CommandStack getMapStack() {
        return mapStack;
    }

    /**
     * Getter for stack display
     *
     * @return stack display
     */
    public String getStackDisplay() {
        return stackDisplay;
    }

    /**
     * Setter for screen display
     *
     * @param screenDisplay screen display
     */
    public void setScreenDisplay(String screenDisplay) {
        this.screenDisplay = screenDisplay;
    }

    /**
     * Getter to get screen display
     *
     * @return screen display
     */
    public String getScreenDisplay() {
        return screenDisplay;
    }

    /**
     * This method reads in input from the scanner to construct a Command and add it to the CommandStack.
     *
     * @param scanner users input
     * @throws iCatchUp.InvalidCommandException when this command
     */
    public void readCommand(Scanner scanner) throws iCatchUp.InvalidCommandException, iCatchUp.EmptyStackException {
        mapsMenu();
        String command = scanner.nextLine().toLowerCase();
        if (!Arrays.asList(mapCommands).contains(command)) throw new iCatchUp.InvalidCommandException();

        switch (command) {
            //command FindPlace
            case "f":
                System.out.println("Please enter a location:");
                FindPlace F = new FindPlace(scanner);
                if (F.getDestination() != null)
                    mapStack.pusCommand(F);
                else nullInput = 1;
                break;

            //command FindPlace.PlanRoute
            case "p":
                FindPlace.PlanRoute P = new FindPlace.PlanRoute(scanner);
                if (P.getDestination() != null && P.getSource() != null)
                    mapStack.pusCommand(P);
                else nullInput = 1;
                break;

            //command FindPlace.StartNavigation
            case "n":
                if (mapStack.isEmpty()) {
                    System.out.println("No route or destination!\n");
                    nullInput = 1;
                    break;
                }
                FindPlace.StartNavigation N = new FindPlace.StartNavigation(mapStack);
                mapStack.pusCommand(N);
                break;

            //switch
            case "s":
                iCatchUp.screen = 2;
                iCatchUp.switchScreen = true;
                break;

            //home
            case "h":
                iCatchUp.screen = 0;
                break;// must add break!!!!

                //back
            case "b":
                goBack();
                break;
        }
        if (nullInput == 0) {
            if (!command.equals("b") && iCatchUp.screen == 1) {
                stackDisplay += "->" + mapStack.getScreenCommand(); //go back when the stack is empty, the Screen is null so just print former screenDisplay, no add
                screenDisplay = mapStack.toString();
            }
        }
        nullInput = 0;
        if (iCatchUp.screen == 1)
            if (mapStack.isEmpty()) {
                System.out.println(stackDisplay + "\n" + screenDisplay + "\n");
            } else System.out.println(stackDisplay + "\n" + "Current Screen: " + screenDisplay + "\n");
    }


    /**
     * This method returns the application to state it was before the most recent command
     *
     * @throws iCatchUp.EmptyStackException stack is empty before the recent command
     */
    public void goBack() throws iCatchUp.EmptyStackException {
        if (mapStack.isEmpty()) throw new iCatchUp.EmptyStackException();// go Home screen
        int y = stackDisplay.indexOf(mapStack.getScreenCommand());
        stackDisplay = stackDisplay.substring(0, y - 2); //return the first index of his string
        mapStack.pop();
        if (mapStack.isEmpty())// after pop stack is empty, screen still be 1
            screenDisplay = "Current Screen: Maps Home";// empty->peek is null -> needs initialize
        else screenDisplay = mapStack.peek().toString();
    }

    /**
     * This method returns maps menu
     *
     * @return maps menu
     */
    public void mapsMenu() {
        System.out.println("Maps Options:\n" +
                "     F) Find a place\n" +
                "     P) Plan a route\n" +
                "     N) Start Navigation\n" +
                "     H) Home Screen\n" +
                "     S) Switch to Safari\n" +
                "     B) Back\n" +
                "\n" +
                "Please select an option: ");
    }
}

