package Domain;

import java.util.ArrayList;
import java.util.List;

public class SymbolTable {
    //node class that defines BST node
    class Node {
        String key;
        Node left, right;

        public Node(String data){
            key = data;
            left = right = null;
        }
    }
    // BST root node 
    Node root;

    // Constructor for BST =>initial empty tree
    public SymbolTable(){
        root = null;
    }

    // insert a node in BST
    public void insert(String key)  {
        root = insert_Recursive(root, key);
    }

    //recursive insert function
    Node insert_Recursive(Node root, String key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        //if (Integer.parseInt(key) < Integer.parseInt(root.key))     //insert in the left subtree
          if(key.compareTo(root.key) < 0)
            root.left = insert_Recursive(root.left, key);
        //else if (Integer.parseInt(key) > Integer.parseInt(root.key))    //insert in the right subtree
          if(key.compareTo(root.key) > 0)
            root.right = insert_Recursive(root.right, key);
        // return pointer
        return root;
    }

    // method for inorder traversal of BST
    public List<String> inorder() {
        List<String> inorder = new ArrayList<>();
        inorder_Recursive(root,inorder);
        return inorder;
    }

    // recursively traverse the BST  
    void inorder_Recursive(Node root,List<String> inorder) {
        if (root != null) {
            inorder_Recursive(root.left,inorder);
            //System.out.print(root.key + " ");
            inorder.add(root.key);
            inorder_Recursive(root.right,inorder);
        }
    }

    boolean search(String key)  {
        root = search_Recursive(root, key);
        return root != null;
    }

    //recursive insert function
    Node search_Recursive(Node root, String key)  {
        // Base Cases: root is null or key is present at root 
        if (root==null || root.key.equals(key))
            return root;
        // val is greater than root's key 
        //if (Integer.parseInt(key) <   Integer.parseInt(root.key) )
        if(key.compareTo(root.key) < 0)
            return search_Recursive(root.left, key);
        // val is less than root's key 
        return search_Recursive(root.right, key);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Symbol Table(Binary search Tree) = { ");
        List<String> inorder = inorder();
        for(String node : inorder){
            stringBuilder.append(node);
            stringBuilder.append(" ");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
