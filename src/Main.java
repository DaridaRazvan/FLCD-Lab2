class Main{
    public static void main(String[] args)  {
        //create a BST object
        SymbolTable bst = new SymbolTable();
        /* BST tree example
              45
           /     \
          10      90
         /  \    /
        7   12  50   */
        //insert data into BST
        bst.insert("45");
        bst.insert("10");
        bst.insert("7");
        bst.insert("12");
        bst.insert("90");
        bst.insert("50");
        //print the BST
        System.out.println("The BST Created with input data(Left-root-right):");
        bst.inorder();

        bst.inorder();
        //search a key in the BST
        boolean ret_val = bst.search ("50");
        System.out.println("\nKey 50 found in BST:" + ret_val );
        ret_val = bst.search ("12");
        System.out.println("\nKey 12 found in BST:" + ret_val );
    }
}
