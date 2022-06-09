import big.data.DataSource;

import java.util.Scanner;

/**
 * This class called island designer that
 * allows the user to run Depth First Searches
 * allows the user to find the maximum network
 *
 * For extra credit,
 * also implement the shortest path algorithm
 * that shows the shortest path.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class IslandDesigner {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("\n" +
                "Welcome to the Island Designer, because, when you're trying to stay above water, Seas get degrees!\n" +
                "\n" +
                "please enter an url:");
        String url = in.nextLine();
        IslandNetwork isLand = new IslandNetwork();
        isLand.loadFromFile(url);
        System.out.println("\n" + "Map loaded.\n");
        // menu
        System.out.println("Menu:\n" +
                "    D) Destinations reachable (Depth First Search)\n" +
                "    F) Maximum Flow\n" +
                "    S) Shortest Path (Extra Credit)\n"+
                "    Q) Quit\n" +
                "Please select an option:" );

        String option = in.nextLine().toLowerCase();
        while (!option.equals("q")){
            switch (option){
                case "d":
                    System.out.println("Please enter a starting city:");
                    String cityName = in.nextLine();
                    if(!isLand.getGraph().containsKey(cityName)){
                        System.out.println("Sorry, this is an invalid city.");
                    }else if (isLand.getGraph().get(cityName).getNeighbors().isEmpty()){System.out.println("No reachable destinations.");}
                    else {isLand.dfs(cityName);System.out.println(); isLand.resetVisitedPreviousCityDistance();}
                    break;
                case "f":
                    System.out.println("Please enter a starting city: ");
                    String source =in.nextLine();
                    System.out.println("Please enter a destination: ");
                    String target = in.nextLine();
                    if(!isLand.getGraph().containsKey(source) ||!isLand.getGraph().containsKey(target) ) {
                        System.out.println("Sorry the cities you input doesn't exist.");
                    }else isLand.maxFlow(source,target);
                    break;
                case "s":
                    System.out.println("Please enter a starting city: ");
                    String from =in.nextLine();
                    System.out.println("Please enter a destination: ");
                    String to = in.nextLine();
                    if(!isLand.getGraph().containsKey(from) ||!isLand.getGraph().containsKey(to))
                        System.out.println("Sorry the cities you input doesn't exist.");
                    else{isLand.djikstra(from,to );
                        isLand.resetVisitedPreviousCityDistance();}
                    break;
            }
            System.out.println("Please select an option: ");
            option=in.nextLine().toLowerCase();
        }
        System.out.println("You can go your own way! Goodbye!");
    }
}
