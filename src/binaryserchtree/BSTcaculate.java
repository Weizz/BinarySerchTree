package binaryserchtree;

public class BSTcaculate{
    public treeNode treeRoot = null;
    
    BSTcaculate(treeNode root){
        treeRoot = root;
    }
    
    public treeNode newTree(int[] allData){
        treeRoot = new treeNode(null, null, null, allData[0]);
        for(int i=1; i<allData.length; i++){
            insertion(treeRoot, allData[i]);
        }
        return treeRoot;
    }
    
    public treeNode newTree(int data){
        treeRoot = new treeNode(null, null, null, data);
        return treeRoot;
    }
    
    public void insertion(int[] allData){
        for(int i=0; i<allData.length;i++){
            insertion(treeRoot, allData[i]);
        }
    }
    
    public void insertion(treeNode node, int data){
        if(data < node.data)
            if(node.leftChild != null)
                insertion(node.leftChild, data);
            else
                node.leftChild = new treeNode(node, null, null, data);
        else if(data > node.data)
            if(node.rightChild != null)
                insertion(node.rightChild, data);
            else
                node.rightChild = new treeNode(node, null, null, data);
    }
    
    public void inOrder(treeNode node){
        if(node != null){
            inOrder(node.leftChild);
            inOrder(node.rightChild);
        }
    }
    
    public treeNode minimum(treeNode node){
        while(node.leftChild != null)
            node = node.leftChild;
        return node;
    }
    
    public treeNode successor(treeNode node){
        if(node.rightChild != null)
            return minimum(node.rightChild);
        treeNode y = node.parent;
        while(y!=null && node == y.rightChild){
            node = y;
            y = y.parent;
        }
        return y;
    }
    
    public treeNode treeSerch(treeNode node, int value){
        if(value < node.data)
            return treeSerch(node.leftChild, value);
        if(value > node.data)
            return treeSerch(node.rightChild, value);
        return node;
    }
    
    public treeNode delection(int value){
        treeNode target = null, x, y;
        try{
            target = treeSerch(treeRoot, value);
        }catch(Exception ex){}
        if(target == null)
            System.out.println("404 - file not found");
        else{   //================delection algorithms from text book===============
            if(target.leftChild==null || target.rightChild==null)
                y = target;
            else
                y = successor(target);
            
            if(y.leftChild != null)
                x = y.leftChild;
            else
                x = y.rightChild;
            
            if(x != null)
                x.parent = y.parent;
            
            if(y.parent == null)
                treeRoot = x;
            else if(y == y.parent.leftChild)
                y.parent.leftChild = x;
            else
                y.parent.rightChild = x;
            
            if(y != target){
                target.data = y.data;
            }
        }   //======================================================================
        return treeRoot;
    }
    
    public int depth(treeNode node, int dep){
        int left = dep, right = dep, largest;
        if(node.leftChild != null)
            left = depth(node.leftChild, dep+1);
        if(node.rightChild != null)
            right = depth(node.rightChild, dep+1);
        
        if(left > right)        //largest ? left:right;
            largest = left;
        else
            largest = right;
        
        return largest;
    }
    
    public int row(){
        int width = 0;
        for(int i=depth(treeRoot, 1); i>1; i--){
            width += Math.pow(2, i-2);
        }
        return width * 2 + 1;
    }
}
