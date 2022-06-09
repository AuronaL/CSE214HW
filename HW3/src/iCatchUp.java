import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * This class contains main method runs a menu
 * that drives application which allows the user to create two instances of the Application class
 * and then prompts the user for input based on which screen it is currently in (Home, Application.Maps, or Application.Safari).
 * The required information for each command is then requested from the user based on the selected operation.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 * */
public class iCatchUp {
    private static Maps maps = new Maps();
    private static Safari safari = new Safari();
    public static int screen=0;// home=0; maps=1; safari=2;
    public static boolean firstTime = true;
    public static boolean switchScreen = false;

    public static void main(String[] args) {
        System.out.println("Welcome to the iPhony pocket telegraph simulator. You are on the home screen.\n"
                );
        Scanner in = new Scanner(System.in);
        homeMenu();
        String option = in.nextLine().toLowerCase();
        String[] home = {"s", "m", "q"};
        while (!option.equals("q")) {
            try {
                if (!Arrays.asList(home).contains(option)) throw new InvalidCommandException();

                    if ( screen == 0 ) {
                        if(!firstTime){
                            System.out.println();
                            homeMenu();
                            option=in.nextLine().toLowerCase(Locale.ROOT);
                        }
                        if (option.equals("m")){
                            screen=1;
                            if(maps.getMapStack().isEmpty()){
                                System.out.println(maps.getStackDisplay() + "\n"  +maps.getScreenDisplay() + "\n");
                            }else System.out.println(maps.getStackDisplay() + "\n"  + "Current Screen: "+ maps.getScreenDisplay() + "\n");
                            maps.readCommand(in);

                        }
                        else if(option.equals("s")){
                            screen=2;
                            if(safari.getSafariStack().isEmpty()){
                                System.out.println(safari.getStackDisplay() + "\n"  +safari.getScreenDisplay() + "\n");
                            }else System.out.println(safari.getStackDisplay() + "\n"  + "Current Screen: "+ safari.getScreenDisplay() + "\n");
                            safari.readCommand(in);
                        }
                        firstTime=false;
                    }

                    while (screen == 1 ) {
                        if(switchScreen){
                            switchScreen=false;
                            if(maps.getMapStack().isEmpty()){
                                System.out.println(maps.getStackDisplay() + "\n"  +maps.getScreenDisplay() + "\n");
                            }else System.out.println(maps.getStackDisplay() + "\n"  + "Current Screen: "+ maps.getScreenDisplay() + "\n");
                        }
                        maps.readCommand(in);

                    }

                    while (screen == 2) {
                        if(switchScreen){
                            switchScreen=false;
                            if(safari.getSafariStack().isEmpty()){
                                System.out.println(safari.getStackDisplay() + "\n"  +safari.getScreenDisplay() + "\n");
                            }else System.out.println(safari.getStackDisplay() + "\n"  + "Current Screen: "+ safari.getScreenDisplay() + "\n");
                        }
                        safari.readCommand(in);
                    }

                } catch (EmptyStackException e) {
                    screen = 0; // go Home screen
                } catch (InvalidCommandException e) {
                System.out.println("Oops, it is an invalid command, please try again.\n");}
            }
        System.out.println("Sorry to see you go, tell the iPod I said hi!\n");
    }

    /**
     * This method is the menu for home screen
     */
    public static void homeMenu() {
         String out= "Home Options:\n" +
                 "    S) Safari\n" +
                 "    M) Maps\n" +
                 "    Q) Quit\n" +
                 "\n" +
                 "Please select an option:";
         System.out.println(out);
    }

    /**
     * This is a class tells that the command is invalid.
     * @ author: Aurona Liu
     * Recitation: R01
     * student id: 114138778
     * email: jiyun.liu@stonybrook.edu
     */
    public static class InvalidCommandException extends Exception{
        public InvalidCommandException(){
            super();
        }
    }

    /**
     * This class is a exception that tells the stack was empty.
     *
     * @ author: Aurona Liu
     * Recitation: R01
     * student id: 114138778
     * email: jiyun.liu@stonybrook.edu
     */
    public static class EmptyStackException extends Exception{
        public EmptyStackException(){
            super();
        }
    }
}

