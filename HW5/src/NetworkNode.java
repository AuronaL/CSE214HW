import java.util.EmptyStackException;

/**
 * holds the type of component being represented,
 *   an array of children (null if this will be a Nintendo)
 *   string for the text
 *   getters and setters
 *   helper methods to check input before adding a child to the tree
 *   custom exceptions for actions like trying to
 *      add too many children to a node (<=9)
 *      adding a child in an invalid position
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class NetworkNode {
    private String name;
    private boolean isNintendo;
    private boolean isBroken; //default is false
    private NetworkNode parent;
    private NetworkNode[] children;  //Just like in hw 1, there should be no holes in the array.
    final int maxChildren=9;   //A full node exception may be desirable.
    private int numberOfChildren =0;
    private int depth =0;

    /**
     * Getter for the number of children
     * @return number of children
     */
    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    /**
     * This is a constructor that creates a new node
     * @param name name
     */
    public NetworkNode(String name){
        this.name=name;
        this.parent=null;
        this.children= new NetworkNode[maxChildren];
        this.isNintendo=false; //default is false-> set from input;
        this.isBroken=false;//default is false
    }

    /**
     * This method remove specific child
     * @param child the index of this child
     */
    public void removeChild(int child){
        if(numberOfChildren==0) throw new EmptyStackException();
        for(int i=child; i<numberOfChildren; i++){
            this.children[i]=this.children[i+1];
        }
        children[numberOfChildren-1]=null;
        numberOfChildren=numberOfChildren-1;
    }

    /**
     * This method add a children in the children list
     * @param child new child
     * @param index array index
     */
    public void add(NetworkNode child, int index){
        children[index-1]=child;
        numberOfChildren++;
    }

// print
    /**
     * This method help print the node
     * @param depth depth
     * @param tree tree
     * @return node print
     */
    public String print(int depth, NetworkTree tree){
        String str ="";
        if(depth!=0) return str+"     "+print(depth-1, tree);
        else {
            if(this==tree.getCursor()) return str +"->"+this; //"\n"?
            else str+= (isNintendo)?"-":"+";} return str + this;
    }
    /**
     * This method give name to every node
     * @return name of node
     */
    public String toString(){
        String str="";
        str+=name;
        if(isBroken) str+=" ~Fault~";
        return str;
    }
//Helper method
    /**
     * This method create string for a node
     * @param digit digit part
     * @return node string
     */
    public String nodeWriteToFile(String digit){
        if(isNintendo) digit+="-";
        digit+=name +"\n";
        return digit;
    }
//Getter and Setter

    /**
     * Get children list
     * @return children list
     */
    public NetworkNode[] getChildren() {
        return children;
    }

    /**
     * Shift children list
     * @param i stat position
     */
    public void setChildToNextNode(int i){
        children[i+1]=children[i];
    }

    /**
     * Setter for parent
     * @param parent parent
     */
    public void setParent(NetworkNode parent) {
        this.parent = parent;
    }

    /**
     * Getter for parent
     * @return parent
     */
    public NetworkNode getParent() {
        return parent;
    }

    /**
     * Getter for depth
     * @return depth
     */
    public int getDepth() {
        return depth;
    }

    /**
     * Setter for depth
     * @param depth depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Getter for flag isBroken
     * @return whether it is broken
     */
    public boolean isBroken() {
        return isBroken;
    }

    /**
     * set the flag isBroken
     * @param broken isBroken
     */
    public void setBroken(boolean broken) {
        isBroken = broken;
    }

    /**
     * Getter for the flag isNintendo
     * @return isNintendo
     */
    public boolean isNintendo() {
        return isNintendo;
    }

    /**
     * Setter for flag isNintendo
     * @param nintendo isNintendo
     */
    public void setNintendo(boolean nintendo) {
        isNintendo = nintendo;
    }


    /**
     * Getter for name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

}
