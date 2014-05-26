package binaryserchtree;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.*;

public class treePanel  extends JPanel{
    treeNode treeRoot = null;
    boolean isTreeNull = true;
    Dimension panelSize;
    ArrayList<nodeBox> boxs = new ArrayList<nodeBox>();
    
    treePanel(treeNode root){
        treeRoot = root;
        BSTcaculate calculater = null;
        if(treeRoot != null)
            isTreeNull = false;
        
        if(isTreeNull){
            Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
            Dimension size = new Dimension((int)(screen.width*0.5), (int)(screen.height*0.7));
            this.setPreferredSize(size);
        }
        else{
            calculater = new BSTcaculate(treeRoot);
            int dep = calculater.depth(treeRoot, 1);
            int hight = (dep*2+1)*50,
                width = calculater.row()*70 + 2*70;
            this.setPreferredSize(new Dimension(width, hight));
            panelSize = this.getPreferredSize();
            placeBox();
        }
        
        this.setLayout(null);
    }
    
    void placeBox(){
        Point place = new Point(panelSize.width/2-35, 50);
        nodeBox box = new nodeBox(treeRoot.data, place);
        boxs.add(box);
        treePanel.this.add(box);
        if(treeRoot.leftChild!=null)
            placeBox(treeRoot.leftChild, place, box);
        if(treeRoot.rightChild!=null)
            placeBox(treeRoot.rightChild, place, box);
    }
    
    void placeBox(treeNode node, Point parentLoc, nodeBox parentBox){
        Point place;
        if(node.leftChild!=null || node.rightChild!=null){
            if(node == node.parent.leftChild)
                place = new Point(parentLoc.x-(int)(Math.pow(2, new BSTcaculate(treeRoot).depth(node, 1)-1))*70, parentLoc.y+100);
            else
                place = new Point(parentLoc.x+(int)(Math.pow(2, new BSTcaculate(treeRoot).depth(node, 1)-1))*70, parentLoc.y+100);
        }
        else{
            if(node == node.parent.leftChild)
                place = new Point(parentLoc.x-70, parentLoc.y+100);
            else
                place = new Point(parentLoc.x+70, parentLoc.y+100);
        }
        nodeBox box = new nodeBox(node.data, place);
        boxs.add(box);
        if(node == node.parent.leftChild)
                parentBox.left = box;
            else
                parentBox.right = box;
        treePanel.this.add(box);
        if(node.leftChild!=null)
            placeBox(node.leftChild, place, box);
        if(node.rightChild!=null)
            placeBox(node.rightChild, place, box);
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        Dimension size = treePanel.this.getSize();
        if(isTreeNull){
            g2.setColor(Color.GRAY);
            g2.drawString("Welcome to BinarySerchTree Graphic", size.width/2-80, size.height/2-20);
        }
        else{
            for(nodeBox b:boxs){
                if(b.left != null)
                    g.drawLine(b.getLocation().x, b.getLocation().y+50, b.left.getLocation().x+35, b.left.getLocation().y);
                if(b.right != null)
                    g.drawLine(b.getLocation().x+70, b.getLocation().y+50, b.right.getLocation().x+35, b.right.getLocation().y);
            }
        }
    }
}
