import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * This class called NetworkTree which will serve as the tree manager for your NetworkTree.
 * This must hold references into a tree (the root and cursor),
 * generate and save the tree to and from a file.
 *
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class NetworkTree {
    private NetworkNode root;
    private NetworkNode cursor;

    public NetworkTree() {
        root=null;
        cursor=null;
    }
    /**
     * Setter for root
     * @param root root
     */
    public void setRoot(NetworkNode root) {
        this.root = root;
    }
    /**
     * Setter for cursor
     * @param cursor cursor
     */
    public void setCursor(NetworkNode cursor) {
        this.cursor = cursor;
    }
    /**
     * Getter for root
     * @return root
     */
    public NetworkNode getRoot() {
        return root;
    }

    /**
     * Getter for cursor
     * @return cursor
     */
    public NetworkNode getCursor() {
        return cursor;
    }

    /**
     * This method move the cursor to root
     */
    public void cursorToRoot() {
        cursor=root;
    }

    /**
     * Removes the child at the specified index of the NetworkTree,
     * as well as all of its children.
     *
     * @return node that need to be deleted
     */
    public NetworkNode cutCursor() {
        //change the depth of other children
        if (cursor == null) throw new EmptyStackException();
        NetworkNode temp = cursor;
        if (cursor == root) {
            root = null;
            cursor = null;
        } else {
            cursor = cursor.getParent();
            for (int i = 0; i < cursor.getNumberOfChildren(); i++) {
                if (cursor.getChildren()[i] == temp) {
                    cursor.removeChild(i);
                }
            }
        }
        return temp;
    }

    /**
     * This method add a new child
     * @param index index
     * @param node child
     */
    public void addChild(int index, NetworkNode node){
        // make a hole
        if(index<0||index> cursor.getNumberOfChildren()+1||cursor.getNumberOfChildren() + 1 == 9 ) throw new ArrayIndexOutOfBoundsException();
        if(cursor.getChildren()[index-1]!=null){
            for(int i=cursor.getNumberOfChildren()-1;i>=index-1; i--){
                cursor.setChildToNextNode(i);
            }
        }
        if(validChild()){
            //add a child
            cursor.add(node,index);
            //set broken
            if(node.getName().contains(" ~Fault~")) node.setBroken(true);
            //link to parent
            node.setParent(cursor);
            //set depth()=parent depth +1
            node.setDepth(cursor.getDepth()+1);
            // move cursor
            cursor=node;
        }else System.out.println("Sorry this position is invalid.");
    }

    /**
     * This method check input before adding a child to the tree
     *
     * @return true if it is valid.
     */
    public boolean validChild() {
        return cursor.getNumberOfChildren() + 1 != 9 && !cursor.isNintendo();
    }

    /**
     * Moves the cursor to the child node of the cursor
     * corresponding to the specified index.
     * @param index index
     */
    public void cursorToChild(int index){
        if(index<0||index>cursor.getNumberOfChildren()) throw new IllegalArgumentException();
        cursor=cursor.getChildren()[index-1];
    }

    /**
     * Moves the cursor to the parent of the current node.
     */
    public void cursorToParent(){
        if(cursor==root) throw new IllegalStateException();
        //System.out.println("Cursor is already at th root!");
        cursor=cursor.getParent();
    }

    /**
     * This method read file and create a tree
     * @param filename file name
     * @return tree
     * @throws FileNotFoundException file not found
     */
    public static NetworkTree readFromFile(String filename) throws FileNotFoundException {
        try {
            File file = new File(filename);
            Scanner sc = new Scanner(file);
            NetworkTree tree = new NetworkTree();
            String str;
            int lastDepth =0;
            boolean firstTime = true;
            do{
                str = sc.nextLine();
                int digitNumber;
                if(firstTime){
                    NetworkNode node = new NetworkNode(str);
                    //cursor = root!!!
                    tree.root=node; tree.cursor=node;   firstTime=false;
                    }
                else{
                    //dig + ?"-" +name
                    digitNumber=digit(str);
                    //last number position= digitNumber-1
                    String name;
                    NetworkNode node;

                    //after last digit number -> ("-"+name)
                    if(String.valueOf(str.charAt(digitNumber)).equals("-")){
                        // char -> String
                        //including start point
                        name=str.substring(digitNumber+1);
                        node = new NetworkNode(name);
                        node.setNintendo(true);
                    }else {
                        name=str.substring(digitNumber);
                        node = new NetworkNode(name);
                    }

                    //before last digit number
                    int index = Character.getNumericValue(str.charAt(digitNumber - 1) ) ;
                    int level = digitNumber-lastDepth;
                    tree.cursor = changeCursor(level,tree);
                    tree.addChild(index,node);
                    lastDepth=digitNumber;
                }
                //test for file -> file should be put in the largest module
                }while(sc.hasNextLine());
            tree.cursorToRoot();
            return tree;
        }catch(FileNotFoundException fe){
            throw new FileNotFoundException();
        }
    }

    //helper method
    /**
     * Remove cursor to place I need
     * @param level the parent's level
     * @param tree tree
     * @return parent node
     */
    public static NetworkNode changeCursor(int level, NetworkTree tree){
        if (level==1){return tree.cursor;}
        // level < 1   e.g. 3-3 =0
        else {
            tree.cursorToParent();
            return changeCursor(level+1 ,tree);
        }
    }

    /**
     * This method tells me the number of digit
     * @param str string
     * @return number of digit
     */
    public static int digit(String str){
        int count =0; boolean stop= false;
        for (int i = 0; i< str.length()&& !stop; i++){
            if(Character.isDigit(str.charAt(i))){count++;}
            else stop=true;
        }
        return count;
    }

    /**
     * This method write current tree to a new file
     * @param tree tree
     * @param filename given file name
     */
    public static void writeToFile(NetworkTree tree, String filename){
        try{
            if(tree==null) throw new EmptyStackException();
            FileWriter file = new FileWriter(filename);
            file.write(tree.writeTFile("", tree.root));
            file.close();
        } catch (EmptyStackException e){System.out.println("This tree is empty!"); }
        catch (IOException e) {e.printStackTrace();}
    }

    //helper method
    /**
     * This method write the tree into a form of digit+name
     * @param digit position
     * @param node node
     * @return tree string
     */
    public String writeTFile(String digit, NetworkNode node){
        String str="";
       // preorder
        if(node==null) return "";
        str+=node.nodeWriteToFile(digit);
        if(node.getNumberOfChildren()!=0){
            for(int i=0; i<node.getNumberOfChildren(); i++){
                // position=index+1
                digit+=String.valueOf(i+1);
                str+=writeTFile(digit,node.getChildren()[i]);
                digit=digit.substring(0,digit.length()-1);
            }
        }
        return str;
    }
    /**
     * This method used for print
     * @param str string
     * @param node node
     * @return tree print string
     */
    public String traversePrint(String str, NetworkNode node){
        if(node==null) return str;
        str += node.print(node.getDepth(),this) + "\n";
        for(int i=0; i<node.getNumberOfChildren();i++) {
            str = traversePrint(str, node.getChildren()[i]);
        }
        return str;
    }

    /**
     * This method find the minimal broken subtree
     */
    public void cursorToMinimalBrokenSubtree() {
        //preorder traversal
        //null-> at the end ->return;
        //if it is broken -> save the node; return;
        // another broken -> check the depth -> renew the temp node to the least depth
        // cursor go back to parent of the temp node
        if(preorder(root)==null) System.out.println("Nothing is broken.");
        else {cursor= preorder(root).getParent();
            System.out.println("Cursor moved to "+cursor.getName());}
    }
    //helper method

    /**
     * This method find the minimal broken node
     * @param node root
     * @return minimal broken node's parent
     */
    public NetworkNode preorder(NetworkNode node){
        if(node==null) return null;//base
        NetworkNode minBroken=null; //just reference
        int tempChildPosition=0;
        int minDepth = 10000;
        //for each loop : (Node a: array name)
        for(int i=0;i<node.getNumberOfChildren();i++){
            if(node.getChildren()[i]!= null){
                NetworkNode child = node.getChildren()[i];
                if (child.isBroken() && child.getDepth()<minDepth) {
                    minBroken=child;  minDepth=child.getDepth();
                }
                if (child.isBroken() && child.getDepth()==minDepth &&child.getParent()!=minBroken.getParent()) {
                    minBroken=child.getParent(); minDepth=minBroken.getDepth();
                }
                else {
                    child= preorder(child);
                    if (child!=null&&child.isBroken() && child.getDepth()<minDepth) {
                        minBroken=child; minDepth=child.getDepth();}
                    if (child!=null&&minBroken!=null&& child.isBroken() && child.getDepth()==minDepth &&child.getParent()!=minBroken.getParent()) {
                        minBroken=child.getParent(); minDepth=minBroken.getDepth();
                    }
                }
            }
        }return minBroken;
    }

    /**
     * Accompanying method that change cursor to broken
     */
    public void changeCursorBrokenStatus(){
        cursor.setBroken(true);
    }
}



