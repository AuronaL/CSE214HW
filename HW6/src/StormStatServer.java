import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * This class named StormStatServer that will
 * allow the user to interact with a database of Storms.
 * Provide the user with a menu-based interface that allows them to add, remove, and edit storms.
 * You will need to be able to serialize (save) the database at the end of the program
 * and deserialize (load) the database if a file containing the database already exists.
 * The database will have storm name as the key and the associated Storm object as the value.
 * Names should be converted to uppercase.
 *
 *
 * On startup, the StormStatServer should
 * check to see if the file hurricane.ser exists in the current directory.
 * If it does, then the file should be loaded
 * and deserialized into the database instance.
 * If the file does not exist,
 * a new HashMap should be created and used instead.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class StormStatServer {
    private static HashMap<String, Storm> database = new HashMap<>();
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the StormStatServer, we may not be able to make it rain, but we sure can tell you when it happened!\n");
        //How to find if a file exist??????!
        try {
            FileInputStream file = new FileInputStream("hurricane.ser");
            ObjectInputStream inStream = new ObjectInputStream(file);
            database = (HashMap<String, Storm>) inStream.readObject();
            inStream.close();
            System.out.println("hurricane.ser was found and loaded.\n");
        }
        catch (IOException | ClassNotFoundException E){
            System.out.println("No previous data found.\n");
        }
        System.out.println("Menu:\n" +
                "\n" +
                "    A) Add A Storm\n" +
                "\n" +
                "    L) Look Up A Storm\n" +
                "\n" +
                "    D) Delete A Storm\n" +
                "\n" +
                "    E) Edit Storm Data\n" +
                "\n" +
                "    R) Print Storms Sorted By Rainfall\n" +
                "\n" +
                "    W) W-Print Storms by WindSpeed\n" +
                "\n" +
                "    X) Save and quit\n" +
                "\n" +
                "    Q) Quit and delete saved data"+" \n\n"
                +"Please select an option:");
        Scanner in = new Scanner(System.in);
        String option = in.nextLine().toLowerCase();
        // or use option.equalsIgnoreCase()
        while (!option.equals("q")&&!option.equals("x")){
            switch (option){
                case "a":
                    try{
                    System.out.println("Please enter name:");
                    String name = in.nextLine().toUpperCase();
                    System.out.println("Please enter date:(yyyy-mm-dd):");
                    String date = in.nextLine();
                    while(invalidDate(date)){
                        System.out.println("Sorry, this date is not valid. Please try again!\n" +
                                "Please enter date:(yyyy-mm-dd):");
                        date = in.nextLine();
                    }
                    System.out.println("Please enter precipitation (cm):");
                    double precipitation = Double.parseDouble(in.nextLine());
                    System.out.println("Please enter windSpeed (km/h):");
                    double windSpeed = Double.parseDouble(in.nextLine());
                    Storm storm = new Storm(name,precipitation,windSpeed,date);
                    database.put(name,storm);
                    System.out.println(name+ " added." );}
                    catch (NumberFormatException E ){
                        System.out.println("Invalid format! Please input number only.");
                    }
                    break;

                case "w":
                    ArrayList<Storm> stormWindSpeed = new ArrayList<>(database.values());
                    Collections.sort(stormWindSpeed, new WindSpeedComparator());
                    System.out.println("Name            WindSpeed       Rainfall        Date\n"+
                     "--------------------------------------------------------------");
                    for (Storm newStorm:stormWindSpeed) {
                        System.out.printf("%-15s %-15.1f %-15.1f %s\n", newStorm.getName(), newStorm.getWindSpeed(),newStorm.getPrecipitation(), newStorm.getDate());
                    }
                    System.out.println();

                    break;

                case "l":
                    //look up a storm
                    System.out.println("Please enter name:");
                    String key = in.nextLine().toUpperCase();
                    // database doesn't have this name
                    if (!database.containsKey(key)){
                        System.out.println("This storm does not exist.");
                        break;
                    }
                    Storm thisStorm = database.get(key);
                    System.out.println("\nStorm "+thisStorm.getName()+": Date "+thisStorm.getDate()+", "+thisStorm.getWindSpeed()+" km/h winds, "+thisStorm.getPrecipitation()+" cm of rain");
                    break;

                case "d":
                    //Delete A Storm
                    System.out.println("Please enter name:");
                    String key1 = in.nextLine().toUpperCase();
                    if(!database.containsKey(key1)){
                        System.out.println("Storm "+key1+" does not exist.");
                        break;
                    }
                    database.remove(key1);
                    System.out.println("Storm "+key1+" has been deleted.");
                    break;

                case "e":
                    System.out.println("Please enter name:");
                    String key2 = in.nextLine().toUpperCase();
                    try{
                    if(database.containsKey(key2)){
                        System.out.println("In Edit Mode, press enter without any input to leave data unchanged.\n" +
                                "\n" +
                                "Please enter date(yyyy-mm-dd): ");
                        String a =in.nextLine();
                        if(invalidDate(a)&&!a.equals("")){
                            System.out.println("Invalid date format."); break;
                        }else if(!a.equals("")){database.get(key2).setDate(a);}
                        System.out.println("Please enter precipitation (cm):");
                        a=in.nextLine();
                        if (!a.equals("")){database.get(key2).setPrecipitation(Double.parseDouble(a));}
                        System.out.println("Please enter windSpeed (km/h):");
                        a=in.nextLine();
                        if (!a.equals("")){database.get(key2).setWindSpeed(Double.parseDouble(a));}System.out.println(key2+" added.");
                    }else System.out.println("Key not found.");
                    }catch (NumberFormatException e){
                        System.out.println("Invalid input. put numbers only!");
                    }
                    break;

                case "r":
                    ArrayList<Storm> rainfall = new ArrayList<>(database.values());
                    Collections.sort(rainfall, new PrecipitationComparator());
                    System.out.println("Name            WindSpeed       Rainfall        Date\n"+
                            "--------------------------------------------------------------");
                    for (Storm newStorm:rainfall) {
                        System.out.printf("%-15s %-15.1f %-15.1f %s\n", newStorm.getName(), newStorm.getWindSpeed(),newStorm.getPrecipitation(), newStorm.getDate());
                    }
                    System.out.println();
                    break;
            }
            System.out.println();
            System.out.println("Please select an option:");
            option=in.nextLine().toLowerCase();}

        if(option.equals("x")){
            // missing code here adds Storage objects to the table.
            FileOutputStream file = new FileOutputStream("hurricane.ser");
            ObjectOutputStream outStream = new ObjectOutputStream(file);
            // the following line will save the object in the file
            outStream.writeObject(database);
            outStream.close();
            file.close();
            System.out.println("File saved to hurricane.ser; feel free to use the weather channel in the meantime.");
        }

        if(option.equals("q")){
            try{
            Files.deleteIfExists(Paths.get("hurricane.ser"));} catch (IOException ignored){}
            System.out.println("Goodbye, it's hard to hold an (electric) candle in the cold November (and April!) rain!");

        }
    }

    /**
     * method check if the date is valid
     * @param date date
     */
    public static boolean invalidDate(String date){
        int format=0; int firstDash = 0; int secondDash=0;
        boolean firstFlag =true;
        for(int i=0; i<date.length(); i++){
            if(String.valueOf(date.charAt(i)).equals("-")){
                if (firstFlag){firstDash=i; firstFlag=false;}
                else {secondDash=i; format=2;}
            }
        }
        if(format!=2) return true; if (date.substring(0,4).contains("-")) return true;
        int j = Integer.parseInt(date.substring(firstDash + 1, secondDash));
        //String substring(int beginIndex)
        int k = Integer.parseInt(date.substring(secondDash+1));
        return j > 12 || j <= 0 || k <= 0 || k > 31;// NEXT TIME MATCH THE MONTH FIRST!
    }
    // split()-> public static boolean invalidDate(String date){
    //    ArrayList<String> List = new ArrayList<>();
    //    String [] splits = date.split("-");
    //    for(String s: splits)}{list.add(s);}}
}
