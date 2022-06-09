import java.util.Comparator;

/**
 * This class named WindSpeedComparator that
 * allows us to compare two storms on the basis of the wind speed.
 * The class should implement the Comparator interface
 * and override the compare method.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class WindSpeedComparator implements Comparator {
    /**
     * Compare method for compared 2 object
     * @param o1 object 1
     * @param o2 object 2
     * @return : -1 if the left wind speed is “less” than the right wind speed,
     *           0 if they are equal, and 1 otherwise.
     */
    @Override
    public int compare(Object o1, Object o2) {
        Storm left = (Storm) o1;
        Storm right =(Storm) o2;
        return compare(left,right);

    }

    /**
     * Compare method for compared 2 storm
     * @param left left storm
     * @param right right storm
     * @return  -1 if the left wind speed is “less” than the right wind speed,
     *      *           0 if they are equal, and 1 otherwise.
     */
    public int compare(Storm left, Storm right){
        return Double.compare(left.getWindSpeed(), right.getWindSpeed());
//        if(left.getWindSpeed()<right.getWindSpeed()) return -1;
//        else if(left.getWindSpeed()==right.getWindSpeed()) return 0;
//        else return 1;
    }
}
