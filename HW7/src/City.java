import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.HashMap;

/**
 * This class named City that will represent each vertex/node of the graph.
 *
 * Each node needs to know what roads lead out of it + what the capacity of the roads is
 * implement comparable(compare method should look at the name of the city only)
 * print a list of cities alphabetically.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class City implements Comparable, Serializable {
    private HashMap<String,Integer> neighbors;//the key is the name of the city, the Integer is the cost of the road;
    private HashMap<String,Integer> tempNeighbors;
    private String name; // the name of the city
    private boolean visited;
    private City previousCity;
    private int distance;
    /**
     * Constructor for city
     */
    public City(String name){neighbors=new HashMap<>(); this.name=name; visited=false; previousCity=null; distance=1000000;
        tempNeighbors= SerializationUtils.clone(neighbors);//deep clone
    }
    /**
     * Getter for distance
     * @return distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Setter for distance
     * @param distance distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Getter for previous city
     * @return previousCity
     */
    public City getPreviousCity() {
        return previousCity;
    }

    /**
     * Setter for previous city
     * @param previousCity previousCity
     */
    public void setPreviousCity(City previousCity) {
        this.previousCity = previousCity;
    }

    /**
     * Getter for temp neighbors
     * @return temp neighbors
     */
    public HashMap<String, Integer> getTempNeighbors() {
        return tempNeighbors;
    }

    /**
     * Setter for temp neighbors
     * @param tempNeighbors deep clone of neighbors
     */
    public void setTempNeighbors(HashMap<String, Integer> tempNeighbors) {
        this.tempNeighbors = tempNeighbors;
    }


    /**
     * This method return whether this city is visited
     * @return true if visited
     */
    public boolean isVisited() {
        return visited;
    }

    /**
     * This method set true if it is visited
     * @param visited true
     */
    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    /**
     * Getter for neighbors
     * @return neighbors
     */
    public HashMap<String, Integer> getNeighbors() {
        return neighbors;
    }

    /**
     * Setter for neighbors
     * @param name neighbors name
     * @param cost cost
     */
    public void setNeighbors(String name, Integer cost) {
        neighbors.put(name, cost);
    }

    /**
     * Getter for name
     * @return city name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name city name
     */
    public void setName(String name) {
        this.name = name;
    }
    public String toString(){
        return this.name;
    }
    /**
     * compare method should look at the name of the city only, so we can print a list of cities alphabetically.
     * @param o another city
     * @return order of these two city
     */
    @Override
    public int compareTo(Object o) {
        City target = (City) o;
        return name.compareToIgnoreCase(target.getName());
    }

}
