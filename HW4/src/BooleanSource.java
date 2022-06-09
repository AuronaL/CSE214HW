/**
 * This is a class construct an object with a probability p between 0 and 1 and then the occurs() method returns true p*100% of the time.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class BooleanSource {
    private double probability;

    /**
     * Construct probability
     * @param p probability
     */
    public BooleanSource(double p){
        if(p<0.0||p>1.0) throw new IllegalArgumentException();
        probability=p;
    }

    /**
     * This method decide whether it will occur
     * @return true if occur
     */
    public boolean occurs(){
      return (Math.random() < probability);
      // java.lang.Math.random() method returns
        // a pseudorandom double type number  greater than or equal to 0.0 and less than 1.0.
    }
}


