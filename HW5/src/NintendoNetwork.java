import java.io.FileNotFoundException;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * This class named NintendoNetwork takes in a text file,
 *                                  generates a NetworkTree
 * and provides an interface for a user to manipulate the tree.
 * Please see the UI required functions for the required functionality
 *
 * @ author: Aurona Liu
 * Recitation: R01
 * student id: 114138778
 * email: jiyun.liu@stonybrook.edu
 */
public class NintendoNetwork {
    public static void main(String[] args) {
        System.out.println("Welcome to the Nintendo Network Manager.\n" +
                "\n" +
                "Menu:\n" +
                "    L) Load from file\n" +
                "    P) Print tree\n" +
                "    C) Move cursor to a child node\n" +
                "    R) Move cursor to root\n" +
                "    U) Move cursor up to parent\n" +
                "    A) Add a child //if tree is empty, do not prompt for child index number\n" +
                "    X) Remove/Cut Cursor and its subtree\n" +
                "    V) Paste Cursor and its subtree\n" +
                "    S) Save to file\n" +
                "    M) Cursor to root of minimal subtree containing all faults\n" +
                "    B) Mark cursor as broken/fixed\n" +
                "    Q) Quit");
        Scanner in = new Scanner(System.in);
        String option;
        NetworkTree tree = new NetworkTree();
        NetworkNode temp = null;
        do {
            System.out.println("\nPlease select an option:");
            option = in.nextLine().toLowerCase();
            switch (option) {
                case "l":
                    System.out.println("Please enter filename: ");
                    String name = in.nextLine();
                    try {
                        tree = NetworkTree.readFromFile(name);
                        System.out.printf("%s loaded.\n", name);
                        //System.out.println(tree.writeTFile("",tree.getRoot()));
                    } catch (FileNotFoundException fe) {
                        System.out.printf("%s Not Found.\n", name);
                    }
                    break;

                case "p":
                    if (tree.getRoot()!=null) {
                        System.out.println(tree.traversePrint("", tree.getRoot()));
                    } else System.out.println("This tree is empty.");
                    break;

                case "c":
                    try{
                        System.out.println("Please enter an index: ");
                        int index = Integer.parseInt(in.nextLine());
                        //in.nextLine();// use Scanner.nextLine after Scanner.next()/any Scanner.nextFoo method
                        if (tree.getCursor() != null) {
                            tree.cursorToChild(index);
                        }
                        System.out.println("Cursor moved to " + tree.getCursor().getName() + ".");}
                    catch (IllegalArgumentException E){
                        System.out.println("This index is invalid because there is no child or position out of 0~9");
                    }catch (NullPointerException E){
                        System.out.println("There is no tree.");
                    }
                    break;

                case "a":
                    try {
                        // do not ask if the tree is empty.
                        if(tree.getRoot()==null){
                            System.out.println("Please enter device name:");
                            String cName = in.nextLine();
                            NetworkNode root = new NetworkNode(cName);
                            tree.setRoot(root);
                            tree.setCursor(root);
                        }
                        else{System.out.println("Please enter an index:");
                        int position = Integer.parseInt(in.nextLine());
                        if(position<=0||position> tree.getCursor().getNumberOfChildren()+1) throw new ArrayIndexOutOfBoundsException();
                        if(tree.getCursor().getChildren()[position-1]!=null) throw new IllegalArgumentException();
                        System.out.println("Please enter device name:");
                        String cName = in.nextLine();
                        System.out.println("Is this Nintendo (y/n):");
                        String isNintendo = in.nextLine().toLowerCase();
                        NetworkNode child = new NetworkNode(cName);
                        if (isNintendo.equals("y")&&!tree.getCursor().isNintendo()) {
                            child.setNintendo(true);
                            System.out.println("Nintendo added.");}
                        else System.out.println(cName+" added.");
                        tree.addChild(position, child);}
                    } catch (IllegalArgumentException e) {
                        System.out.println("The position is not valid because there is already a child there.");
                    } catch (ArrayIndexOutOfBoundsException E) {
                        System.out.println("This position is invalid because it should be 0~9 or we already reached the maximum.");
                    }
                    break;

                case "u":
                    try{
                    if(tree.getCursor()==tree.getRoot()) throw new IllegalStateException();
                    tree.cursorToParent();
                    System.out.println("Cursor moved to "+tree.getCursor().getName());}
                    catch (IllegalArgumentException e){System.out.println("Cursor is already at th root!");}
                    break;

                case "x":
                    temp = tree.cutCursor();
                    System.out.println(temp+ " cut, cursor is at "+tree.getCursor());
                    break;

                case "r":
                    tree.cursorToRoot();
                    System.out.println("Cursor moved to "+tree.getRoot());
                    break;

                case "v":
                    try{ if (temp == null) throw new EmptyStackException();
                    System.out.println("Please enter an index: ");
                    int here = Integer.parseInt(in.nextLine());
                    tree.addChild(here,temp);
                    resetDepth(temp);
                    System.out.println(temp+ " pasted as child of "+tree.getCursor().getParent());
                    }
                    catch (EmptyStackException e){System.out.println("There is nothing to paste.");}
                    break;

                case "s":
                    System.out.println("Please enter a filename: ");
                    String fileName = in.nextLine();
                    NetworkTree.writeToFile(tree, fileName);
                    System.out.println("File saved.");
                    break;

                case "b":
                    tree.changeCursorBrokenStatus();
                    System.out.println(tree.getCursor().getName()+" marked as broken.");
                    break;

                case "m":
                    tree.cursorToMinimalBrokenSubtree();
                    break;
            }
        }
        while (!option.equals("q"));

//            NetworkTree tree= NetworkTree.readFromFile("HW5sbutopology.txt");

        System.out.println("Make like a tree and leave!");
    }

    //helper method

    /**
     * This method help us reset the depth of children
     * @param node start node
     */
    public static void resetDepth(NetworkNode node){
        if(node == null) return;
        for(NetworkNode child : node.getChildren()){
            if(child==null) return;
            child.setDepth(node.getDepth()+1);
            resetDepth(child);
        }
    }
}