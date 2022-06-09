import big.data.DataSource;
import org.apache.commons.lang3.SerializationUtils;

import java.util.*;

/**
 * This class called IslandNetwork that holds the graph,
 *
 * load a graph from a file,
 * calculate the DFS from any node in the graph
 * maximal network flow from any node to any other node (printing "no flow" when applicable).
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class IslandNetwork {

    private HashMap<String, City> graph;// this stores the cities in the graph
    /**
     * Constructor for island network
     */
    public IslandNetwork(){graph= new HashMap<>();}
    /**
     * Getter for graph
     * @return graph
     */
    public HashMap<String, City> getGraph() {
        return graph;
    }
    /**
     * This method used for loading a file
     * @param url name for the link
     * @return the graph
     */
    //static method has to pass the local variable because it can not access to it: so I delete it ;)
    public IslandNetwork loadFromFile(String url){
        DataSource ds = DataSource.connectXML(url);
        ds.load();
        String cityNamesStr=ds.fetchString("cities");
        String[] cityNames=cityNamesStr.substring(1,cityNamesStr.length()-1).replace("\"","").split(",");
        for(String city: cityNames){
            City a = new City(city);
            graph.put(city, a);
        }
        // Cities print://Alphabetical Order
        ArrayList<String> cities = new ArrayList<>(graph.keySet());
        Collections.sort(cities);
        String print ="Cities:     \n" + "---------------------\n";
        for(String list: cities){
            print+=list+"\n";
        }
        System.out.println(print);

        //construct road & cost
        String roadNamesStr=ds.fetchString("roads");
        //delete " " mark
        roadNamesStr=roadNamesStr.substring(1,roadNamesStr.length()-1);
        String[] roadNames=roadNamesStr.substring(1,roadNamesStr.length()-1).split("\",\"");
        ArrayList<String> roadPrint = new ArrayList<>();
        for(String road: roadNames){
            String[] neighbors= road.split(",");
            graph.get(neighbors[0]).setNeighbors(neighbors[1],Integer.parseInt(neighbors[2]));
            String A=String.format("%s to %s",neighbors[0],neighbors[1]);
            roadPrint.add(String.format("%-42s %s",A, neighbors[2]));
        }
        //deep clone for temp neighbor
        for(String city: cities){graph.get(city).setTempNeighbors(SerializationUtils.clone(graph.get(city).getNeighbors())); }
        //Road print
        print=""; print+=String.format("%-38sCapacity\n", "Road")+"----------------------------------------------\n";
        //Collections.sort(roadPrint);
        for(String road: roadPrint){print+=road+"\n";}System.out.println(print);
        return this;
    }

    /**
     * This is the depth-first traversal
     * @param from start point
     */
     public void dfs(String from){
         if(graph.containsKey(from)){
       City city = graph.get(from);
       city.setVisited(true);// start node set visited
         ArrayList<String> neighborName = new ArrayList<>(city.getNeighbors().keySet());
         Collections.sort(neighborName);// sort by alphabetical
         for(String neighbors:neighborName){ // look at neighbors
             if(!graph.get(neighbors).isVisited()){ // if neighbors are not visited
                 System.out.print(neighbors+", ");
                 dfs(neighbors); // recursive
             }
         }
     }}


    private ArrayList<ArrayList<String>> paths = new ArrayList<>(); // possible paths
    private ArrayList<Integer> capacity = new ArrayList<>(); // capacity for each path
//public void count()
    /**
     * This is the max flow
     * @param from starting point
     * @param to end point
     */
    boolean find = false;//each path that can arrive
    //boolean havePath = true;//does not work...
    public void maxFlow(String from, String to){
        if(graph.containsKey(from)&&graph.containsKey(to)) {
            // use dfs to find every possible path
            ArrayList<String> path= new ArrayList<>();
            path.add(from); // add start location
            ArrayList<String> neighbors = new ArrayList<>(graph.get(from).getNeighbors().keySet());
             for(int i=0; i<neighbors.size() ;i++){
                findPath(from,to,Integer.MAX_VALUE,path);find=false;graph.get(from).setVisited(false);graph.get(to).setVisited(false);
                path.clear(); path.add(from); resetVisitedPreviousCityDistance();}
            // capacity not full
             for(int j=0; j<6; j++){
                for(int i=0; i<neighbors.size() ;i++){
                    findPath(from,to,Integer.MAX_VALUE,path);find=false;graph.get(from).setVisited(false);graph.get(to).setVisited(false);
                    path.clear(); path.add(from);resetVisitedPreviousCityDistance();}
           }
            //renew capacity
            ArrayList<String> cities = new ArrayList<>(graph.keySet());
            for(String city: cities){graph.get(city).setTempNeighbors(SerializationUtils.clone(graph.get(city).getNeighbors()));}
            //print
            if(paths.size()!=0){int i; int max_flow=0;
            System.out.println("Routing:");
            for(int j=0; j< paths.size(); j++) {
                for (i = 0; i < paths.get(j).size() - 1; i++) {
                    System.out.print(paths.get(j).get(i) + "->");
                }max_flow+=capacity.get(j); System.out.print(to);System.out.print(": " + capacity.get(j)+"\n");
            }System.out.println("Maximum Flow: "+max_flow);}
            else System.out.println("No route available!");
        }
        else System.out.println("Sorry the cities you input doesn't exist.");
        paths.clear(); capacity.clear();
    }
    /**
     * Helper method to find every possible path
     * @param from start city
     * @param to destination
     * @param maxFlow max flow for every path
     * @param path pass by city
     */
    public void findPath(String from, String to, int maxFlow, ArrayList<String> path) {
        if(from.equals(to)){ // base find destination
            paths.add((ArrayList<String>)path.clone()); //shallow clone. but string so this can print
            capacity.add(maxFlow);;graph.get(to).setVisited(false); find=true;
            renewTempCapacity(maxFlow, path);//temp flow reset-> pass by this road, tempC= tempC-capacity;
            return;
        }
        City nowInCity =graph.get(from);
        ArrayList<String> neighbors = new ArrayList<>(nowInCity.getNeighbors().keySet());
        sortLargerCapacity(nowInCity, neighbors);
        for(String neighbor: neighbors){int temp=maxFlow;
            if(!graph.get(neighbor).isVisited() && !find && nowInCity.getTempNeighbors().get(neighbor)>0){ // visit: capacity >0; not been visited; not find
                graph.get(neighbor).setVisited(true);
                maxFlow=Math.min(maxFlow, nowInCity.getTempNeighbors().get(neighbor));
                path.add(neighbor);
                findPath(neighbor, to, maxFlow, path);
                path.remove(neighbor); maxFlow=temp;}
            }
        nowInCity.setVisited(false); // renew visited ??is that all node are set to unvisited???
        }

    /**
     * Helper method to reset temp capacity
     * @param maxFlow max flow
     * @param path list of city
     */
    public void renewTempCapacity(int maxFlow, ArrayList<String> path){
        for(int i=0; i<path.size()-1; i++ ) {
            int remainFlow = graph.get(path.get(i)).getTempNeighbors().get(path.get(i + 1)) - maxFlow;
            graph.get(path.get(i)).getTempNeighbors().replace(path.get(i + 1), remainFlow);
        }
    }

    /***
     * Helper method using bubble sort to make larger capacity came first
     * @param from now in city
     * @param neighbors neighbors of this city
     */
    public void sortLargerCapacity(City from,ArrayList<String> neighbors ){
        for (int i=0; i< neighbors.size(); i++){
            for (int j=neighbors.size()-1; j>i; j--){
                if(from.getNeighbors().get(neighbors.get(j)) > from.getNeighbors().get(neighbors.get(j-1))){
                    String temp = neighbors.get(j);
                    neighbors.set(j,neighbors.get(j-1));
                    neighbors.set(j-1, temp);
                }
            }
        }
    }

    /**
     * Method finds the shortest path( the least weigh capacity )
     * implement Djikstra's algorithm for shortest path, using the edge capacities as weights.
     * @param from  source
     * @param to destination
     */
    public void djikstra(String from, String to){
        City nowInCity ;
        graph.get(from).setDistance(0);// start point's distance is 0
        PriorityQueue<City> minHeap = new PriorityQueue<City>((city1, city2) -> Integer.compare(city1.getDistance(), city2.getDistance())); //Use min_Heap to keep every node ( dequeue when it is the minimum top->visited)
        minHeap.addAll(graph.values());
        while(!minHeap.isEmpty()){
            nowInCity = minHeap.poll();
            ArrayList<String> neighbors = new ArrayList<>(nowInCity.getNeighbors().keySet());
            for(String neighbor: neighbors){
                // if distance < now Distance
               if(nowInCity.getDistance()+nowInCity.getNeighbors().get(neighbor)<graph.get(neighbor).getDistance()){
                   graph.get(neighbor).setDistance(nowInCity.getDistance()+nowInCity.getNeighbors().get(neighbor));
                   graph.get(neighbor).setPreviousCity(nowInCity);
                   ArrayList<City> temp = new ArrayList<>();
                   while(!minHeap.isEmpty()){
                       temp.add(minHeap.poll());
                   }
                   while(!temp.isEmpty()){
                       minHeap.add(temp.remove(0));
                   }

               }
            }
        }
        //print
        nowInCity=graph.get(to); ArrayList<City> path = new ArrayList<>();
        if(nowInCity.getPreviousCity()!=null){
        while(nowInCity.getPreviousCity()!=null){
            path.add(nowInCity);
            nowInCity=nowInCity.getPreviousCity();
        }path.add(nowInCity);// now city -> start position
        System.out.print("Path: " );
        for(int i=path.size()-1; i>=1; i--){
            System.out.print(path.get(i)+"->");
        }System.out.print(path.get(0)+"\n");
        System.out.println("Cost: "+ graph.get(to).getDistance());}
        else System.out.println("No route available!");
    }

    /**
     *  resets visited PreviousCity and Distance
     */
    public void resetVisitedPreviousCityDistance(){
        ArrayList<City> cities = new ArrayList<City>(graph.values());
        for(City city: cities ){
            city.setVisited(false);
            city.setPreviousCity(null);
            city.setDistance(1000000);
        }
    }
}

