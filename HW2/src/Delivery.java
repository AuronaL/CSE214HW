/**
 * This class contains the source, destination, and instruction for a delivery.
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class Delivery {
    private String source;
    private String dest;
    private String instruction;

    /**
     * Getter method used for getting the instruction of this delivery.
     * @return instruction
     */
    public String getInstruction() {
        return instruction;
    }

    /**
     * Setter method used for setting the instruction of the delivery
     * @param instruction
     * the instruction about the delivery
     */
    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }


    /**
     * Getter method used for getting the source of this delivery.
     * @return source
     */
    public String getSource() {
        return source;
    }

    /**
     * Setter method used for setting the source of the delivery
     * @param source
     * the source of the delivery
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Getter method used for getting the destination of this delivery.
     * @return source
     */
    public String getDest() {
        return dest;
    }
    /**
     * Setter method used for setting the destination of the delivery
     * @param dest
     * the destination of the delivery
     */
    public void setDest(String dest) {
        this.dest = dest;
    }

    /**
     * This is a constructor that initializes the information of a delivery.
     */
    public Delivery(){
        this.dest="";
        this.instruction="";
        this.source="";
    }

    /**
     * This is a constructor that initializes with the given information of a delivery.
     *
     * @param source
     * the source of the delivery
     * @param dest
     * the destination of the delivery
     * @param instruction
     * the instruction of the delivery
     *
     */
    public Delivery(String source, String dest, String instruction){
        this.dest=dest;
        this.instruction=instruction;
        this.source=source;
    }

    /**
     * This is a toString method print delivery information.
     * @return string
     */
    public String toString(){
        return String.format("To: %s | From: %s\nInstruction: %s\n",dest,source,instruction);
    }
}

