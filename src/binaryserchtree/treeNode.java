package binaryserchtree;

public class treeNode {
    public treeNode parent = null,
                    leftChild = null,
                    rightChild = null;
    public int data;
    
    treeNode(treeNode parent, treeNode left, treeNode right, int data){
        this.parent = parent;
        leftChild = left;
        rightChild = right;
        this.data = data;
    }
}
